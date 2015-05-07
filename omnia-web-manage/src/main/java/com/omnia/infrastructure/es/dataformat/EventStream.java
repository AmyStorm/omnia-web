package com.omnia.infrastructure.es.dataformat;

import org.axonframework.domain.DomainEventStream;


/**
 * Created by khaerothe on 2015/4/28.
 */
public interface EventStream<V> extends DomainEventStream {
    V version();
}
