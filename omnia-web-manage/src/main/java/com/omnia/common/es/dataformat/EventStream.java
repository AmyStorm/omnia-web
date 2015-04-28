package com.omnia.common.es.dataformat;

import com.omnia.common.event.Event;

/**
 * Created by Administrator on 2015/4/28.
 */
public interface EventStream<V> extends Iterable<Event> {
    V version();
}
