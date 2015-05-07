package com.omnia.authentication.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;
import java.util.UUID;

/**
 * Created by khaerothe on 2015/4/29.
 */
public class LoginSuccessEvent implements Event {

    private UUID userId;

    private String userName;

    private Date loginTime;
    public LoginSuccessEvent(UUID userId, String userName, Date loginTime){
        this.userId = userId;
        this.userName = userName;
        this.loginTime = loginTime;
    }

    public String getUserName() {
        return userName;
    }

    public UUID getUserId() {
        return userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }
}
