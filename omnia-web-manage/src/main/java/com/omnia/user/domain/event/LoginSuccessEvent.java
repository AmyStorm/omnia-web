package com.omnia.user.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;

/**
 * Created by khaerothe on 2015/4/29.
 */
public final class LoginSuccessEvent implements Event {

    private final String userId;
    private final Date loginTime;

    public LoginSuccessEvent(String userId){
        this.userId = userId;
        this.loginTime = new Date();
    }

    public String getUserId() {
        return userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }
}
