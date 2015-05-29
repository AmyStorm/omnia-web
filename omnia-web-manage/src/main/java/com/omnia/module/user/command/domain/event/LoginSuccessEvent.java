package com.omnia.module.user.command.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;

/**
 * Created by khaerothe on 2015/4/29.
 */
public final class LoginSuccessEvent implements Event {

    private final String identifier;
    private final Date loginTime;

    public LoginSuccessEvent(String identifier){
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
