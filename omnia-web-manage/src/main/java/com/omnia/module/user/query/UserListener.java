package com.omnia.module.user.query;

import akka.actor.ActorSystem;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.omnia.module.user.command.domain.User;
import com.omnia.module.user.command.domain.event.LoginSuccessEvent;
import com.omnia.module.user.command.domain.event.UserCreateEvent;
import com.omnia.module.user.query.repository.impl.UserQueryRepositoryImpl;
import com.thoughtworks.xstream.XStream;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventstore.mongo.MongoTemplate;
import org.axonframework.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by khaerothe on 2015/5/15.
 */
@Component
public class UserListener {

    private static final Logger LOG = LoggerFactory.getLogger(UserListener.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    @Qualifier("userRepository")
    private Repository<User> repository;

    @Autowired
    private ActorSystem system;
    @EventHandler
    private void handlerUserCreated(UserCreateEvent event){
        User user = repository.load(event.getIdentifier());
        UserQueryRepositoryImpl.inMemoryUser.put(user.getIdentifier(), user);

    }

    public void handleUserTracing(){

        DBCursor cursor = mongoTemplate.domainEventCollection().find();
        while(cursor.hasNext()){
            DBObject object = cursor.next();
            Map dbMap = object.toMap();
//            dbMap.forEach((k,v) -> System.out.println(k.toString() + v.toString()));

            String payload = dbMap.get("serializedPayload").toString();
            String identifier = dbMap.get("aggregateIdentifier").toString();
            XStream xstream = new XStream();
            Object event = xstream.fromXML(payload);
            User user = repository.load(identifier);
            if(UserQueryRepositoryImpl.inMemoryUser.get(identifier) != null){
                UserQueryRepositoryImpl.inMemoryUser.get(identifier).applyEvent(event);
            }else{
                user.applyEvent(event);
                UserQueryRepositoryImpl.inMemoryUser.put(dbMap.get("aggregateIdentifier").toString(), user);
            }

//            if(event instanceof UserCreateEvent){
//                UserCreateEvent e  = (UserCreateEvent) event;
//                UserQueryRepositoryImpl.inMemoryUser.put(dbMap.get("aggregateIdentifier").toString(), new User(dbMap.get("aggregateIdentifier").toString(), e.getUserName() ,e.getPassword()));
//            }else if(event instanceof LoginSuccessEvent){
//                LoginSuccessEvent e  = (LoginSuccessEvent) event;
////                UserQueryRepositoryImpl.inMemoryUser.get(e.getIdentifier()).;
//            }

            System.out.println(UserQueryRepositoryImpl.inMemoryUser);
        }
    }
}
