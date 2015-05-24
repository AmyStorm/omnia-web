package com.omnia.infrastructure.eventstore.geteventstore;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.omnia.common.util.JsonUtil;
import com.omnia.infrastructure.eventstore.geteventstore.actor.ReadResult;
import com.omnia.infrastructure.eventstore.geteventstore.actor.WriteResult;
import com.omnia.module.query.user.repository.actor.WriteQueryRepository;
import eventstore.EventData;
import eventstore.ReadStreamEventsCompleted;
import eventstore.Settings;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import eventstore.j.EventDataBuilder;
import eventstore.j.SettingsBuilder;
import eventstore.j.WriteEventsBuilder;
import eventstore.tcp.ConnectionActor;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.eventstore.SnapshotEventStore;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.json.JacksonSerializer;
import org.axonframework.upcasting.SimpleUpcasterChain;
import org.axonframework.upcasting.UpcasterAware;
import org.axonframework.upcasting.UpcasterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>Implementation of the <code>EventStore</code> based on a Event Store instance.
 * Created by Administrator on 2015/5/7.
 */
public class GetEventStoreEventStore implements SnapshotEventStore, UpcasterAware {

    private static final Logger LOG = LoggerFactory.getLogger(GetEventStoreEventStore.class);

    private static final String DEFAULT_DOMAINEVENTS_PREFIX = "domainevents";
    private static final String DEFAULT_SNAPSHOTEVENTS_PREFIX = "snapshotevents";
    private static final String DEFAULT_SEPARATOR = "_";

    private final ActorSystem system;

    private final Settings settings;

    private final Serializer eventSerializer;
    private final String streamPrefix;
    private ActorRef connectionActor;
    private final EsConnection connection;
    private final ActorRef writeQueryRepository;

    private final String domainEventsPrefix;
    private final String snapshotEventsPrefix;
    private final String separator;

    private UpcasterChain upcasterChain = SimpleUpcasterChain.EMPTY;

    public GetEventStoreEventStore(String streamPrefix, ActorSystem system) {

        this(streamPrefix, system, new JacksonSerializer(), DEFAULT_DOMAINEVENTS_PREFIX, DEFAULT_SNAPSHOTEVENTS_PREFIX, DEFAULT_SEPARATOR);
    }

    public GetEventStoreEventStore(String streamPrefix, ActorSystem system, Serializer serializer,
                                   String domainEventsPrefix, String snapshotEventsPrefix,
                                   String separator) {
        this.eventSerializer = serializer;
        this.streamPrefix = streamPrefix;
        this.system = system;
        this.settings = new SettingsBuilder()
                .address(new InetSocketAddress("127.0.0.1", 1113))
                .build();
        this.connectionActor = system.actorOf(ConnectionActor.getProps(settings));
        this.connection = EsConnectionFactory.create(system);
//        this.readResult = system.actorOf(Props.create(ReadResult.class), "readResult");
//        this.writeResult = system.actorOf(Props.create(WriteResult.class), "writeResult");
        this.writeQueryRepository = system.actorOf(Props.create(WriteQueryRepository.class), "WriteQueryRepository");
        this.domainEventsPrefix = domainEventsPrefix;
        this.snapshotEventsPrefix = snapshotEventsPrefix;
        this.separator = separator;
    }

    @Override
    public void appendEvents(String type, DomainEventStream events) {
        String identifier = streamPrefix + DEFAULT_SEPARATOR + events.peek().getAggregateIdentifier();
        WriteEventsBuilder builder = new WriteEventsBuilder(identifier);
        while (events.hasNext()) {
            DomainEventMessage message = events.next();
            GetEventStoreEventEntry entry = new GetEventStoreEventEntry(type, message, this.eventSerializer);
            builder = builder.addEvent(toEventData(entry));
            LOG.debug("events data append success: " + message.toString());
        }
//        if (version >= 0) {
//            builder = builder.expectVersion((int) version);
//        } else {
//            builder = builder.expectNoStream();
//        }
        builder = builder.expectNoStream();


        ActorRef writeResult = system.actorOf(WriteResult.mkProps(writeQueryRepository, identifier));

        connectionActor.tell(builder.build(), writeResult);
    }

    @Override
    public DomainEventStream readEvents(String type, Object identifier) {

//        final Future<Event> future = connection.readEvent(streamPrefix + ":" + type, new EventNumber.Exact(0), false, null);
//        try {
//            Event result = future.result(Duration.apply(10, TimeUnit.SECONDS), null);
//            result.
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        final Future<ReadStreamEventsCompleted> future = connection.readStreamEventsForward(streamPrefix + DEFAULT_SEPARATOR + identifier, null, 1000, false, null);
//        final Future<Event> future = connection.readEvent(streamPrefix + DEFAULT_SEPARATOR + identifier, new EventNumber.Exact(0), false, null);
        try {
            ReadStreamEventsCompleted result = future.result(Duration.apply(10, TimeUnit.SECONDS), null);
            return new GetEventStoreEventStream(-1, result, identifier);
        } catch (Exception e) {
            LOG.error("events data load error.", e);
            return null;
        }

    }

    @Override
    public void appendSnapshotEvent(String type, DomainEventMessage snapshotEvent) {
        LOG.debug("appendSnapshotEvent invoke...............................");
    }

    @Override
    public void setUpcasterChain(UpcasterChain upcasterChain) {
        LOG.debug("setUpcasterChain invoke...............................");
        this.upcasterChain = upcasterChain;
    }

    private EventData toEventData(GetEventStoreEventEntry event) {
        try {
            return new EventDataBuilder(event.getPayload().getType().getName())
                    .eventId(UUID.randomUUID())
                    .jsonData(JsonUtil.parseJsonString(event))
                    .build();
        } catch (JsonProcessingException e) {
            LOG.error("events toEventData error.", e);
            return null;
        }
    }

}
