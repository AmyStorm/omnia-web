package com.omnia.infrastructure.es;

import com.omnia.infrastructure.es.dataformat.EventStream;
import com.omnia.infrastructure.event.Event;
import rx.Observable;

import java.util.List;
import java.util.UUID;

/**
 * Created by khaerothe on 2015/4/28.
 */
public interface EventStore {
    EventStream<Long> loadEventStream(UUID aggregateId);
    void store(UUID aggregateId, long version, List<Event> events);
    Observable<Event> all();
}
