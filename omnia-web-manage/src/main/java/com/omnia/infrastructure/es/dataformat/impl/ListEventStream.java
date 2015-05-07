package com.omnia.infrastructure.es.dataformat.impl;

import com.omnia.infrastructure.es.dataformat.EventStream;
import org.axonframework.domain.DomainEventMessage;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/28.
 */
public class ListEventStream implements EventStream<Long> {
    private final long version;
    private final List<DomainEventMessage> events;
    private Iterator<DomainEventMessage> iterator = Collections.<DomainEventMessage>emptyList().iterator();
    private DomainEventMessage next;

    public ListEventStream() {
        this.version = 0;
        events = Collections.emptyList();
        this.iterator = events.iterator();
        this.next = null;
    }

    public ListEventStream(long version, List<DomainEventMessage> events){
        this.version = version;
        this.events = events;
        this.iterator = events.iterator();
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
