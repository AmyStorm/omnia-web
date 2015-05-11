package com.omnia.user.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;

/**
 * Created by khaerothe on 2015/4/29.
 */
public final class LoginSuccessEvent implements Event {

    private final String username;
    private final String password;
    private final Date loginTime;

    public LoginSuccessEvent(String username, String password){
        this.username = username;
        this.password = password;
        this.loginTime = new Date();
    }
}
