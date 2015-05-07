package com.omnia.infrastructure.es.dataformat;

import com.omnia.infrastructure.event.Event;

/**
 * Created by Administrator on 2015/4/28.
 */
public interface EventStream<V> extends Iterable<Event> {
    V version();
}
