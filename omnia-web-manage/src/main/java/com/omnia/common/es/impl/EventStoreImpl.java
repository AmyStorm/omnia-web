package com.omnia.common.es.impl;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.omnia.common.es.EventStore;
import com.omnia.common.es.dataformat.EventStream;
import com.omnia.common.es.dataformat.impl.ListEventStream;
import com.omnia.common.event.Event;
import com.omnia.common.util.JsonUtil;
import eventstore.*;
import eventstore.j.*;
import eventstore.j.EsConnection;
import eventstore.tcp.ConnectionActor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import rx.Observable;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Created by khaerothe on 2015/4/28.
 */
public class EventStoreImpl implements EventStore {

    private static final Log LOG = LogFactory.getLog(EventStoreImpl.class);

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private final ActorSystem system;
    final Settings settings = new SettingsBuilder()
            .address(new InetSocketAddress("127.0.0.1", 2113))
            .build();
    private final String streamPrefix;
    private final ActorRef connectionActor;
    private final ActorRef writeResult;
    private final EsConnection connection;

    public EventStoreImpl(String streamPrefix) {
        this.streamPrefix = streamPrefix;
        this.system = ActorSystem.create();
        this.connectionActor = system.actorOf(ConnectionActor.getProps(settings));
        this.writeResult = system.actorOf(Props.create(WriteResult.class));
        this.connection = EsConnectionFactory.create(system);
    }
    @Override
    public EventStream<Long> loadEventStream(UUID aggregateId) {
        final Future<ReadStreamEventsCompleted> future = connection.readStreamEventsForward(streamPrefix + aggregateId, null, 1000, false, null);
        try {
            ReadStreamEventsCompleted result = future.result(Duration.apply(10, TimeUnit.SECONDS), null);
            List<Event> events = new ArrayList<>();
            for (eventstore.Event event : result.eventsJava()) {
                Event domainEvent = parse(event);
                events.add(domainEvent);
            }
            return new ListEventStream(result.lastEventNumber().value(), events);
        } catch (StreamNotFoundException e) {
            return new ListEventStream(-1, Collections.emptyList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void store(UUID aggregateId, long version, List<Event> events) {
        WriteEventsBuilder builder = new WriteEventsBuilder(streamPrefix + aggregateId);
        for (Event event : events) {
            builder = builder.addEvent(toEventData(event));
        }
        if (version >= 0) {
            builder = builder.expectVersion((int) version);
        } else {
            builder = builder.expectNoStream();
        }
        connectionActor.tell(builder.build(), writeResult);
    }

    @Override
    public Observable<Event> all() {
        return Observable.create(subscriber -> connection.subscribeToAllFrom(new SubscriptionObserver<IndexedEvent>() {
            @Override
            public void onLiveProcessingStart(Closeable arg0) {
            }

            @Override
            public void onEvent(IndexedEvent event, Closeable arg1) {
                if (!event.event().streamId().isSystem() && event.event().streamId().streamId().startsWith("game")) {
                    try {
                        subscriber.onNext(parse(event.event()));
                    } catch (Exception e) {
                        LOG.warn("Error when handling event", e);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override
            public void onClose() {
                subscriber.onCompleted();
            }
        }, null, true, null));
    }


    private EventData toEventData(Event event) {
        return new EventDataBuilder(event.getClass().getName())
                .eventId(UUID.randomUUID())
                .jsonData(JsonUtil.parseJsonString(event))
                .build();
    }

    private Event parse(eventstore.Event event)
            throws ClassNotFoundException, IOException {
        Class<? extends Event> type = (Class<? extends Event>) Class.forName(event.data().eventType());
        String json = new String(event.data().data().value().toArray(), UTF8);
        return JsonUtil.parseObjectFromJson(json, type);
    }

    public static class WriteResult extends UntypedActor {
        final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof WriteEventsCompleted) {
                final WriteEventsCompleted completed = (WriteEventsCompleted) message;
                log.info("range: {}, position: {}", completed.numbersRange(), completed.position());
            } else if (message instanceof Status.Failure) {
                final Status.Failure failure = ((Status.Failure) message);
                final EsException exception = (EsException) failure.cause();
                log.error(exception, exception.toString());
            } else {
                unhandled(message);
            }
        }
    }
}
