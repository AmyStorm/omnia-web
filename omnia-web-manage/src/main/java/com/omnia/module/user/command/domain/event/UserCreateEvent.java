package com.omnia.module.user.command.domain.event;

import com.omnia.infrastructure.event.Event;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Date;

/**
 * Created by khaerothe on 2015/5/11.
 */
public final class UserCreateEvent implements Event {

    @TargetAggregateIdentifier
    private String identifier;

    private String userName;

    private String password;

    private Date createTime;

    private UserCreateEvent(){

    }

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
