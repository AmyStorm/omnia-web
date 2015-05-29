package com.omnia.module.user.command;

/**
 * Created by Administrator on 2015/5/13.
 */
public class CreateUserCommand {

    private String username;

    private String password;

    public CreateUserCommand(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
