package com.omnia.user.domain.command;


/**
 * Created by Administrator on 2015/5/4.
 */
public final class LoginCommand {

    private String username;

    private String password;

    public LoginCommand(String username, String password) {
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
