package com.omnia.user.domain.event;

import com.omnia.infrastructure.event.Event;

import java.util.Date;

/**
 * Created by khaerothe on 2015/5/11.
 */
public class UserCreateEvent implements Event {

    private final String username;

    private final String password;

    private final Date createDate;

    public UserCreateEvent(String username, String password){
        this.username = username;
        this.password = password;
        this.createDate = new Date();
    }
}
