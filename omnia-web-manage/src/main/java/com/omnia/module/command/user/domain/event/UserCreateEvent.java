package com.omnia.module.command.user.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;

/**
 * Created by khaerothe on 2015/5/11.
 */
public final class UserCreateEvent implements Event {

    private final String identifier;

    private final String userName;

    private final String password;

    private final Date createTime;

    public UserCreateEvent(String identifier, String userName, String password){
        this.identifier = identifier;
        this.userName = userName;
        this.password = password;
        this.createTime = new Date();
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
