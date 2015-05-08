package com.omnia.infrastructure.eventstore.geteventstore;

import com.omnia.infrastructure.es.dataformat.EventStream;
import eventstore.Event;
import org.axonframework.domain.DomainEventMessage;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/28.
 */
public class GetEventStoreEventStream implements EventStream<Long> {
    private final long version;
    private final List<DomainEventMessage> events;
    private Iterator<DomainEventMessage> iterator = Collections.<DomainEventMessage>emptyList().iterator();
    private DomainEventMessage next;
    private Object aggregateIdentifier;

    public GetEventStoreEventStream(Object aggregateIdentifier) {
        this.version = 0;
        events = Collections.emptyList();
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

    public GetEventStoreEventStream(long version, List<Event> events, Object aggregateIdentifier){
        this.aggregateIdentifier = aggregateIdentifier;
        this.version = version;
        List<DomainEventMessage> messages = new GetEventStoreEventEntry().getDomainEvents(aggregateIdentifier);
        this.events = messages;
        this.iterator = this.events.iterator();
        this.next = iterator.hasNext() ? iterator.next() : null;
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
}
