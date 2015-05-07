package com.omnia.infrastructure.es.actor;

import akka.actor.Status;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import eventstore.EsException;
import eventstore.WriteEventsCompleted;

/**
 * Created by khaerothe on 2015/4/30.
 */
public class WriteResult extends UntypedActor {
    final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof WriteEventsCompleted) {
            final WriteEventsCompleted completed = (WriteEventsCompleted) message;
            log.info("range: {}, position: {}", completed.numbersRange(), completed.position());
        } else if (message instanceof Status.Failure) {
            final Status.Failure failure = ((Status.Failure) message);
            final EsException exception = (EsException) failure.cause();
            log.error(exception, exception.toString());
        } else {
            unhandled(message);
        }
    }
}