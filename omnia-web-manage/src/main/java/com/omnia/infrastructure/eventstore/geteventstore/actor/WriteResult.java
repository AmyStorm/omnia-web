package com.omnia.infrastructure.eventstore.geteventstore.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Status;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import eventstore.EsException;
import eventstore.WriteEventsCompleted;

/**
 * Created by khaerothe on 2015/4/30.
 */
public final class WriteResult extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef callbackActor;
    private Object identifier;
//    private ActorRef callbackActor = getContext().actorOf(Props.create(WriteQueryRepository.class), "WriteQueryRepository");

    /**
     * default
     */
    public WriteResult(){

    }

    public WriteResult(ActorRef callbackActor, Object identifier){
        this.callbackActor = callbackActor;
        this.identifier = identifier;
    }

    public static Props mkProps(ActorRef callbackActor, Object identifier) {
        return Props.create(WriteResult.class, callbackActor, identifier);
    }
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof WriteEventsCompleted) {
            final WriteEventsCompleted completed = (WriteEventsCompleted) message;
            log.info("range: {}, position: {}", completed.numbersRange(), completed.position());
            if(callbackActor != null){
                callbackActor.tell(new WriteCallback(identifier), getSelf());
            }
        } else if (message instanceof Status.Failure) {
            final Status.Failure failure = ((Status.Failure) message);
            final EsException exception = (EsException) failure.cause();
            log.error(exception, exception.toString());
        } else {
            unhandled(message);
        }
        context().system().shutdown();
    }
}