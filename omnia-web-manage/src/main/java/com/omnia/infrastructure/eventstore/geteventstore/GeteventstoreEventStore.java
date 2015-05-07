package com.omnia.infrastructure.eventstore.geteventstore;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.omnia.common.util.JsonUtil;
import com.omnia.infrastructure.es.actor.WriteResult;
import com.omnia.infrastructure.es.dataformat.impl.ListEventStream;
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
import org.axonframework.eventsourcing.ConflictResolver;
import org.axonframework.eventstore.EventVisitor;
import org.axonframework.eventstore.PartialStreamSupport;
import org.axonframework.eventstore.SnapshotEventStore;
import org.axonframework.eventstore.management.Criteria;
import org.axonframework.eventstore.management.CriteriaBuilder;
import org.axonframework.eventstore.management.EventStoreManagement;
import org.axonframework.upcasting.UpcasterAware;
import org.axonframework.upcasting.UpcasterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>Implementation of the <code>EventStore</code> based on a Event Store instance.
 * Created by Administrator on 2015/5/7.
 */
public class GeteventstoreEventStore implements SnapshotEventStore, EventStoreManagement, UpcasterAware, PartialStreamSupport {

    private static final Logger LOG = LoggerFactory.getLogger(GeteventstoreEventStore.class);

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private final Settings settings = new SettingsBuilder()
            .address(new InetSocketAddress("127.0.0.1", 1113))
            .build();
    private final ActorSystem system;
    private final String streamPrefix;
    private final ActorRef connectionActor;
    private final ActorRef writeResult;
    private final EsConnection connection;


    public GeteventstoreEventStore(String streamPrefix, ActorSystem system) {
        this.streamPrefix = streamPrefix;
        this.system = system;
        this.connectionActor = system.actorOf(ConnectionActor.getProps(settings));
        this.writeResult = system.actorOf(Props.create(WriteResult.class));
        this.connection = EsConnectionFactory.create(system);
    }

    @Override
    public void appendEvents(String type, DomainEventStream events) {
        WriteEventsBuilder builder = new WriteEventsBuilder(streamPrefix + ":" + type);
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
        final Future<ReadStreamEventsCompleted> future = connection.readStreamEventsForward(streamPrefix + identifier, null, 1000, false, null);
        try {
            ReadStreamEventsCompleted result = future.result(Duration.apply(10, TimeUnit.SECONDS), null);
            List<Event> events = result.eventsJava();
            DomainEventStream eventStream = new ListEventStream(1, events);
        } catch (Exception e) {
            LOG.error("events data load error.", e);
        }
        return null;
    }

    @Override
    public void visitEvents(EventVisitor visitor) {

    }

    @Override
    public void visitEvents(Criteria criteria, EventVisitor visitor) {

    }

    @Override
    public CriteriaBuilder newCriteriaBuilder() {
        return null;
    }

    @Override
    public DomainEventStream readEvents(String type, Object identifier, long firstSequenceNumber) {
        return null;
    }

    @Override
    public DomainEventStream readEvents(String type, Object identifier, long firstSequenceNumber, long lastSequenceNumber) {
        return null;
    }

    @Override
    public void appendSnapshotEvent(String type, DomainEventMessage snapshotEvent) {

    }

    @Override
    public void setUpcasterChain(UpcasterChain upcasterChain) {

    }

    private EventData toEventData(DomainEventMessage event) {
        return new EventDataBuilder(event.getPayloadType().getName())
                .eventId(UUID.randomUUID())
                .jsonData(JsonUtil.parseJsonString(event))
                .build();
    }
}
