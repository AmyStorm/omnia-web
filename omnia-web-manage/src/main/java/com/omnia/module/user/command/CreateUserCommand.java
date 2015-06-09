package com.omnia.module.user.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

/**
 * Created by khaerothe on 2015/5/13.
 */
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private String identifier;

    private String username;

    private String password;

    public CreateUserCommand(String username, String password){
        this.identifier = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentifier() {
        return identifier;
    }
}
