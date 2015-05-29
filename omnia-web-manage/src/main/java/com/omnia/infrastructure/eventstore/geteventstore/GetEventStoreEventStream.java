package com.omnia.infrastructure.eventstore.geteventstore;

import eventstore.Event;
import eventstore.ReadStreamEventsCompleted;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
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
public class GetEventStoreEventStream implements DomainEventStream {

    private static final Logger LOG = LoggerFactory.getLogger(GetEventStoreEventStream.class);
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final long version;
    private Iterator<DomainEventMessage> iterator = Collections.<DomainEventMessage>emptyList().iterator();
    private DomainEventMessage next;
    private Object aggregateIdentifier;
    private UpcasterChain upcasterChain = SimpleUpcasterChain.EMPTY;
    private boolean skipUnknownTypes = false;

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
        List<Event> events = result.eventsJava();
        List<DomainEventMessage> domainEventMessages = new ArrayList<>();

        try {
            for(Event event : events){
                GetEventStoreEventEntry entry = new GetEventStoreEventEntry(event);
                domainEventMessages.addAll(parse(entry));
            }
        } catch (ClassNotFoundException | IOException e) {
            LOG.error("init GetEventStoreEventStream error.", e);
        }
        this.iterator = domainEventMessages.iterator();
        this.next = domainEventMessages.isEmpty() ? null : domainEventMessages.get(0);
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

    private List<DomainEventMessage> parse(GetEventStoreEventEntry entry)
            throws ClassNotFoundException, IOException {
//        Class<? extends GetEventStoreEventEntry> type = (Class<? extends GetEventStoreEventEntry>) Class.forName(entry);
//        String json = new String(event.data().data().value().toArray(), UTF8);
//        return JsonUtil.parseObjectFromJson(json, type);
        return entry.getDomainEvents(aggregateIdentifier, upcasterChain, skipUnknownTypes);
    }
}
