package com.omnia.module.query.user.repository.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Status;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.omnia.infrastructure.eventstore.geteventstore.actor.ReadResult;
import com.omnia.infrastructure.eventstore.geteventstore.actor.WriteCallback;
import eventstore.ReadEvent;
import eventstore.Settings;
import eventstore.j.ReadEventBuilder;
import eventstore.j.SettingsBuilder;
import eventstore.tcp.ConnectionActor;

import java.net.InetSocketAddress;

/**
 * Created by khaerothe on 2015/5/20.
 */
public class WriteQueryRepository extends UntypedActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private final Settings settings = new SettingsBuilder()
    .address(new InetSocketAddress("127.0.0.1", 1113))
            .build();
    private final ActorRef connector = getContext().actorOf(ConnectionActor.getProps(settings));

    private final ActorRef readResult = getContext().actorOf(Props.create(ReadResult.class));
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof WriteCallback) {
            WriteCallback callback = (WriteCallback) message;
            final ReadEvent readEvent = new ReadEventBuilder(callback.getIdentifier().toString())
                    .first()
                    .resolveLinkTos(false)
                    .requireMaster(true)
                    .build();
            connector.tell(readEvent, readResult);
        } else if (message instanceof Status.Failure) {

        } else {
            unhandled(message);
        }
        context().system().shutdown();
    }
}
