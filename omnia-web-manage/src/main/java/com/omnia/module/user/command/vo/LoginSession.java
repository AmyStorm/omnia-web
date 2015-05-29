package com.omnia.module.user.command.vo;



/**
 * Created by khaerothe on 2015/4/17.
 */
public final class LoginSession {

    private final String id;

    private final String userName;


    public LoginSession(String id, String userName){
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
