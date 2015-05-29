package com.omnia.module.user.command;


/**
 * Created by khaerothe on 2015/5/4.
 */
public final class LogoutCommand {
    private final String identifier;

    public LogoutCommand(String identifier){
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
