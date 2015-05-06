package com.omnia.common.listener;

import akka.actor.ActorSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import scala.concurrent.duration.Duration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by khaerothe on 2015/4/30.
 */
public class AkkaListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(AkkaListener.class);

    @Autowired
    private ActorSystem system;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //Get the actor system from the spring context
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (system != null) {
            LOG.info("Killing ActorSystem as a part of web application ctx destruction.");
            system.shutdown();
            system.awaitTermination(Duration.create(15, TimeUnit.SECONDS));
        } else {
            LOG.warn("No actor system loaded, yet trying to shut down. Check AppContext config and consider if you need this listener.");
        }
    }
}
