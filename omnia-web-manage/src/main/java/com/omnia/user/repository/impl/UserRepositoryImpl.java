package com.omnia.user.repository.impl;

import com.omnia.user.domain.User;
import com.omnia.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khaerothe on 2015/4/29.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    public static Map<String, User> inMemoryUser = new HashMap<>();
//
//    static{
//        inMemoryUser.put("bcf5e485-a81f-4b4b-ae12-3fd7c7c7ef73", new User("bcf5e485-a81f-4b4b-ae12-3fd7c7c7ef73", "zhangsan", "$2a$10$XjxLbHCP59ZImtMzZcGIbuER1Gtirnh90DqEKT5ZzlyKeg9FdGgGe"));
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
//    public Map<String, User> getAllUsers(){
//        userRepository.
//    }

}
