package com.omnia.user.repository.impl;

import com.omnia.user.domain.User;
import com.omnia.user.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khaerothe on 2015/4/29.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    public static Map<String, User> inMemoryUser = new HashMap<>();

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
