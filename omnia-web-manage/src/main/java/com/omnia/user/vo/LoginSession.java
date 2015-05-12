package com.omnia.user.vo;


import com.omnia.user.domain.User;

/**
 * Created by khaerothe on 2015/4/17.
 */
public final class LoginSession {

    private String id;
    private String name;

    public LoginSession(User user){
        this.id = user.getId();
        this.name = user.getUserName();
    }
    public LoginSession(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
