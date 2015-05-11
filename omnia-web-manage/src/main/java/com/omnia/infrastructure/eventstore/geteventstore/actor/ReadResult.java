package com.omnia.infrastructure.eventstore.geteventstore.actor;

import akka.actor.Status;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import eventstore.EsException;
import eventstore.Event;
import eventstore.ReadEventCompleted;

/**
 * Created by khaerothe on 2015/5/11.
 */
public final class ReadResult extends UntypedActor{

    final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object message) throws Exception {
        if (message instanceof ReadEventCompleted) {
            final ReadEventCompleted completed = (ReadEventCompleted) message;
            final Event event = completed.event();
            log.debug("event: {}", event);
        } else if (message instanceof Status.Failure) {
            final Status.Failure failure = ((Status.Failure) message);
            final EsException exception = (EsException) failure.cause();
            log.error(exception, exception.toString());
        } else
            unhandled(message);

        context().system().shutdown();
    }
}
