package com.omnia.authentication.domain;

import com.omnia.authentication.domain.event.LoginFailedEvent;
import com.omnia.authentication.domain.event.LoginSuccessEvent;

import java.util.Date;
import java.util.UUID;

/**
 *
 * Created by khaerothe on 2015/4/29.
 */
public class AuthenticationToken {
    private UUID userId;

    private String userName;

    private String password;

    private Date lastLogin;
    private boolean isLogin = false;


    public AuthenticationToken(){

    }

    public AuthenticationToken build(UUID userId, String userName, String password, Date lastLogin){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.lastLogin = lastLogin;
        return this;
    }

    public boolean authentication(String username, String password){
        if(username.equals(this.userName) && password.equals(this.password)){
            handle(new LoginSuccessEvent(this.userId, username, new Date()));
            return true;
        }else{
            handle(new LoginFailedEvent(this.userId, username, new Date()));
            return false;
        }
    }
    public void handle(LoginSuccessEvent event){
        this.userId = event.getUserId();
        this.userName = event.getUserName();
        this.lastLogin = event.getLoginTime();
        this.isLogin = true;
    }

    public void handle(LoginFailedEvent event){
        this.userId = event.getUserId();
        this.userName = event.getUserName();
        this.lastLogin = event.getLoginTime();
        this.isLogin = false;
    }

    public String getId(){
        return this.userId.toString();
    }
}
