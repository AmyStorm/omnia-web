package com.omnia.infrastructure.eventstore.geteventstore;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.omnia.common.util.JsonUtil;
import com.omnia.infrastructure.eventstore.geteventstore.actor.ReadResult;
import com.omnia.infrastructure.eventstore.geteventstore.actor.WriteResult;
import eventstore.Event;
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
import org.axonframework.eventstore.EventVisitor;
import org.axonframework.eventstore.PartialStreamSupport;
import org.axonframework.eventstore.SnapshotEventStore;
import org.axonframework.eventstore.management.Criteria;
import org.axonframework.eventstore.management.CriteriaBuilder;
import org.axonframework.eventstore.management.EventStoreManagement;
import org.axonframework.upcasting.SimpleUpcasterChain;
import org.axonframework.upcasting.UpcasterAware;
import org.axonframework.upcasting.UpcasterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>Implementation of the <code>EventStore</code> based on a Event Store instance.
 * Created by Administrator on 2015/5/7.
 */
public class GetEventStoreEventStore implements SnapshotEventStore, EventStoreManagement, UpcasterAware, PartialStreamSupport {

    private static final Logger LOG = LoggerFactory.getLogger(GetEventStoreEventStore.class);


    private final ActorSystem system;

    private final Settings settings;

    private final String streamPrefix;
    private final ActorRef connectionActor;
    private final ActorRef readResult;
    private final ActorRef writeResult;
    private final EsConnection connection;
    private UpcasterChain upcasterChain = SimpleUpcasterChain.EMPTY;

    public GetEventStoreEventStore(String streamPrefix, ActorSystem system) {
        this.streamPrefix = streamPrefix;
        this.system = system;
        this.settings = new SettingsBuilder()
                .address(new InetSocketAddress("127.0.0.1", 1113))
                .build();
        this.connectionActor = system.actorOf(ConnectionActor.getProps(settings));
        this.connection = EsConnectionFactory.create(system);
        this.readResult = system.actorOf(Props.create(ReadResult.class));
        this.writeResult = system.actorOf(Props.create(WriteResult.class));

    }

    @Override
    public void appendEvents(String type, DomainEventStream events) {

        WriteEventsBuilder builder = new WriteEventsBuilder(streamPrefix + ":" + events.peek().getIdentifier());
        while(events.hasNext()){
            DomainEventMessage message = events.next();
            builder = builder.addEvent(toEventData(message));
            if (LOG.isDebugEnabled()) {
                LOG.debug("events data append success: " + message.toString());
            }
        }
//        if (version >= 0) {
//            builder = builder.expectVersion((int) version);
//        } else {
//            builder = builder.expectNoStream();
//        }
        builder = builder.expectNoStream();
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
        final Future<ReadStreamEventsCompleted> future = connection.readStreamEventsForward(streamPrefix + ":" + identifier, null, 1000, false, null);
        try {
            ReadStreamEventsCompleted result = future.result(Duration.apply(10, TimeUnit.SECONDS), null);

            DomainEventStream eventStream = new GetEventStoreEventStream(-1, result, identifier);
            return eventStream;
        } catch (Exception e) {
            LOG.error("events data load error.", e);
            return null;
        }
//        final ReadEvent readEvent = new ReadEventBuilder(streamPrefix + ":" + type)
//                .first()
//                .resolveLinkTos(false)
//                .requireMaster(true)
//                .build();
//
//        connectionActor.tell(readEvent, readResult);
    }

    @Override
    public void visitEvents(EventVisitor visitor) {
        LOG.debug("visitEvents invoke...............................");
        visitEvents(null, visitor);
    }

    @Override
    public void visitEvents(Criteria criteria, EventVisitor visitor) {
        LOG.debug("visitEvents invoke...............................");
//        final Future<ReadStreamEventsCompleted> future = connection.readStreamEventsForward(streamPrefix + ":" + identifier, null, 1000, false, null);
//        try {
//            ReadStreamEventsCompleted result = future.result(Duration.apply(10, TimeUnit.SECONDS), null);
//
//            DomainEventStream eventStream = new GetEventStoreEventStream(-1, result, identifier);
//            while (eventStream.hasNext()) {
//                visitor.doWithEvent(eventStream.next());
//            }
//        } catch (Exception e) {
//            LOG.error("events data load error.", e);
//        }
    }

    @Override
    public CriteriaBuilder newCriteriaBuilder() {
        LOG.debug("newCriteriaBuilder invoke...............................");
        return null;
    }

    @Override
    public DomainEventStream readEvents(String type, Object identifier, long firstSequenceNumber) {
        LOG.debug("readEvents invoke...............................");
        return null;
    }

    @Override
    public DomainEventStream readEvents(String type, Object identifier, long firstSequenceNumber, long lastSequenceNumber) {
        LOG.debug("readEvents invoke...............................");
        return null;
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

    private EventData toEventData(DomainEventMessage event) {
        return new EventDataBuilder(event.getPayloadType().getName())
                .eventId(UUID.randomUUID())
                .jsonData(JsonUtil.parseJsonString(event))
                .build();
    }
}
