package com.omnia.infrastructure.eventstore.geteventstore;

import com.omnia.common.util.JsonUtil;
import com.omnia.infrastructure.es.dataformat.EventStream;
import eventstore.Event;
import eventstore.ReadStreamEventsCompleted;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.json.JacksonSerializer;
import org.axonframework.upcasting.SimpleUpcasterChain;
import org.axonframework.upcasting.UpcasterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/28.
 */
public class GetEventStoreEventStream implements EventStream<Long> {

    private static final Logger LOG = LoggerFactory.getLogger(GetEventStoreEventStream.class);
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final long version;
    private Iterator<DomainEventMessage> iterator = Collections.<DomainEventMessage>emptyList().iterator();
    private DomainEventMessage next;
    private Object aggregateIdentifier;
    private ReadStreamEventsCompleted result;

    public GetEventStoreEventStream(Object aggregateIdentifier) {
        this.version = 0;
        List<DomainEventMessage> events = Collections.emptyList();
        this.iterator = events.iterator();
        this.next = null;
        this.aggregateIdentifier = aggregateIdentifier;
    }

//    public GetEventStoreEventStream(long version, List<DomainEventMessage> events){
//        this.version = version;
//        this.events = events;
//        this.iterator = events.iterator();
//        this.next = iterator.hasNext() ? iterator.next() : null;
//    }

    public GetEventStoreEventStream(long version, ReadStreamEventsCompleted result, Object aggregateIdentifier){
        this.aggregateIdentifier = aggregateIdentifier;
        this.version = version;
        UpcasterChain upcasterChain = SimpleUpcasterChain.EMPTY;
        Serializer serializer = new JacksonSerializer();
        this.result = result;
        List<Event> events = result.eventsJava();
        List<DomainEventMessage> domainEventMessages = new ArrayList<>();
        try {
            for(Event event : events){
                domainEventMessages.add(parse(event));
            }
        } catch (ClassNotFoundException | IOException e) {
            LOG.error("init GetEventStoreEventStream error.", e);
        }
        this.iterator = domainEventMessages.iterator();
        this.next = null;
//        new GetEventStoreEventEntry(aggregateIdentifier.toString(), events.get(0), serializer).getDomainEvents(aggregateIdentifier, upcasterChain, false);
    }

    @Override
    public Long version() {
        return version;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public DomainEventMessage next() {
        DomainEventMessage next = iterator.hasNext() ? iterator.next() : null;
        this.next = next;
        return next;
    }

    @Override
    public DomainEventMessage peek() {
        return next;
    }

    private DomainEventMessage parse(eventstore.Event event)
            throws ClassNotFoundException, IOException {
        Class<? extends DomainEventMessage> type = (Class<? extends DomainEventMessage>) Class.forName(event.data().eventType());
        String json = new String(event.data().data().value().toArray(), UTF8);
        return JsonUtil.parseObjectFromJson(json, type);
    }
}
