package com.omnia.user.domain.command;

/**
 * Created by khaerothe on 2015/5/4.
 */
public final class LogoutCommand {
    private final String userId;

    public LogoutCommand(String userId){
        this.userId = userId;
    }

}
