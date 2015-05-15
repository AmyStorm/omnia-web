package com.omnia.module.command.user.domain.event;

/**
 * Created by Administrator on 2015/5/14.
 */
public class LogoutSuccessEvent {
    private final String identifier;

    public LogoutSuccessEvent(String identifier){
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
