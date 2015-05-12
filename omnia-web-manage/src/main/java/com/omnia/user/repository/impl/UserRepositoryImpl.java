package com.omnia.user.repository.impl;

import com.omnia.user.domain.User;
import com.omnia.user.repository.UserRepository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by khaerothe on 2015/4/29.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Map<String, User> inMemoryUser = new HashMap<>();

    static{
        UUID id = UUID.randomUUID();
        User user1 = new User(id.toString(), "zhangsan", BCrypt.hashpw("zhangsan", BCrypt.gensalt()));
        inMemoryUser.put(id.toString(), user1);
    }

//    public UserRepositoryImpl(Class<User> aggregateType, EventStore eventStore) {
//        super(aggregateType, eventStore);
//    }

    public User getUserByName(String name){
        for(Map.Entry<String, User> entry : inMemoryUser.entrySet()){
            User user = entry.getValue();
            if(user.getUserName().equals(name)){
                return user;
            }
        }
        return null;
    }

}
