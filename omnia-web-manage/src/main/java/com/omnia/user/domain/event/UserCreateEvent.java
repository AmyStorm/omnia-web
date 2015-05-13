package com.omnia.user.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;

/**
 * Created by khaerothe on 2015/5/11.
 */
public final class UserCreateEvent implements Event {

    private final String id;

    private final String username;

    private final String password;

    private final Date createTime;

    public UserCreateEvent(String id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = new Date();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
