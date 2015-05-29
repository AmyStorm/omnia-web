package com.omnia.module.user.query.repository.actor;

import akka.actor.Status;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.omnia.infrastructure.eventstore.geteventstore.GetEventStoreEventStream;
import com.omnia.infrastructure.eventstore.geteventstore.actor.WriteCallback;
import com.omnia.module.user.command.domain.User;
import com.omnia.module.user.command.domain.event.UserCreateEvent;
import com.omnia.module.user.query.repository.impl.UserQueryRepositoryImpl;
import eventstore.ReadStreamEventsCompleted;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import org.axonframework.domain.DomainEventMessage;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by khaerothe on 2015/5/20.
 */
public class WriteQueryRepository extends UntypedActor {
    private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof WriteCallback) {
            WriteCallback callback = (WriteCallback) message;
            EsConnection connection = EsConnectionFactory.create(getContext().system());
            final Future<ReadStreamEventsCompleted> future = connection.readStreamEventsForward(callback.getIdentifier().toString(), null, 1000, false, null);
            try {
                ReadStreamEventsCompleted result = future.result(Duration.apply(10, TimeUnit.SECONDS), null);
                GetEventStoreEventStream stream = new GetEventStoreEventStream(-1, result, callback.getIdentifier());
                while(stream.hasNext()){
                    DomainEventMessage eventMessage = stream.next();
                    Object identifier = eventMessage.getAggregateIdentifier();
                    User user = UserQueryRepositoryImpl.inMemoryUser.get(identifier);
                    if(user != null){
                        user.applyEvent(eventMessage.getPayload());
                    }else{
                        UserCreateEvent userCreateEvent = (UserCreateEvent) eventMessage.getPayload();
                        UserQueryRepositoryImpl.inMemoryUser.put(userCreateEvent.getIdentifier(), new User(userCreateEvent));
                    }
                }
            } catch (Exception e) {
                LOG.error("events data load error.", e);
            }
        } else if (message instanceof Status.Failure) {

        } else {
            unhandled(message);
        }
//        context().system().shutdown();
    }
}
