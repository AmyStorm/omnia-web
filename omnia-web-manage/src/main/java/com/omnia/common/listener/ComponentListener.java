package com.omnia.common.listener;

import akka.actor.ActorSystem;
import com.omnia.module.user.command.CreateUserCommand;
import com.omnia.module.user.query.UserListener;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import scala.concurrent.duration.Duration;

import javax.servlet.ServletContextEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by khaerothe on 2015/4/30.
 */
public class ComponentListener extends ContextLoaderListener {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentListener.class);

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private ActorSystem system;

    @Autowired
    private UserListener userListener;

    public ComponentListener() {
    }

    public ComponentListener(WebApplicationContext context) {
        super(context);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);
        //Get the actor system from the spring context
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

//        User user = userRepository.getUserById("bcf5e485-a81f-4b4b-ae12-3fd7c7c7ef73");
//        UserQueryRepository.inMemoryUser.put(user.getIdentifier(), user);
        CreateUserCommand command = new CreateUserCommand("zhangsan", "zhangsan");
        commandBus.dispatch(new GenericCommandMessage<>(command));
        userListener.handleUserTracing();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        super.contextDestroyed(servletContextEvent);
        if (system != null) {
            LOG.info("Killing ActorSystem as a part of web application ctx destruction.");
            system.shutdown();
            system.awaitTermination(Duration.create(15, TimeUnit.SECONDS));
        } else {
            LOG.warn("No actor system loaded, yet trying to shut down. Check AppContext config and consider if you need this listener.");
        }
    }
}
