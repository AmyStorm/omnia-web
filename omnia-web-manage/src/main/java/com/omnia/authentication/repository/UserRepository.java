package com.omnia.authentication.repository;

import com.omnia.authentication.domain.AuthenticationToken;

import java.util.*;

/**
 * Created by khaerothe on 2015/4/29.
 */
public class UserRepository {

    private final Map<String, AuthenticationToken> inMemoryUser = new HashMap<>();

    public UserRepository(){
        AuthenticationToken user1 = new AuthenticationToken();
        Calendar c = Calendar.getInstance();
        c.set(1989, Calendar.MARCH, 25);
        UUID id = UUID.randomUUID();
        user1.build(id, "zhangsan", "zhangsan", c.getTime());
        this.inMemoryUser.put(id.toString(), user1);
    }
    public AuthenticationToken authentication(String name, String password){
        for(Map.Entry<String, AuthenticationToken> entry : inMemoryUser.entrySet()){
            AuthenticationToken token = entry.getValue();
            if(token.authentication(name, password)){
                return entry.getValue();
            }
        }
        return null;
    }
}
