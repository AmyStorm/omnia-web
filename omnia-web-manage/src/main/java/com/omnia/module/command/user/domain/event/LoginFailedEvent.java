package com.omnia.module.command.user.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;

/**
 * Created by khaerothe on 2015/4/29.
 */
public final class LoginFailedEvent implements Event {

    private final String identifier;
    private final Date loginTime;

    public LoginFailedEvent(String identifier){
        this.identifier = identifier;
        this.loginTime = new Date();
    }

    public String getIdentifier() {
        return identifier;
    }

    public Date getLoginTime() {
        return loginTime;
    }
}
