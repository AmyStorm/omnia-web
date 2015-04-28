package com.omnia.common.es.dataformat.impl;

import com.omnia.common.es.dataformat.EventStream;
import com.omnia.common.event.Event;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/28.
 */
public class ListEventStream implements EventStream<Long> {
    private final long version;
    private final List<Event> events;

    public ListEventStream() {
        this.version = 0;
        events = Collections.emptyList();
    }

    public ListEventStream(long version, List<Event> events){
        this.version = version;
        this.events = events;
    }

    @Override
    public Long version() {
        return version;
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
