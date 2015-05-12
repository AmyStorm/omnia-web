package com.omnia.user.repository;

import com.omnia.user.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by khaerothe on 2015/4/29.
 */
@Repository
public class UserRepository {

    private final Map<String, User> inMemoryUser = new HashMap<>();

    public UserRepository(){

        UUID id = UUID.randomUUID();
        User user1 = new User(id.toString(), "zhangsan", BCrypt.hashpw("zhangsan", BCrypt.gensalt()));
        this.inMemoryUser.put(id.toString(), user1);
    }
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
