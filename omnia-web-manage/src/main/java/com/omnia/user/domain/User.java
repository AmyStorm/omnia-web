package com.omnia.user.domain;

import com.omnia.user.domain.event.LoginFailedEvent;
import com.omnia.user.domain.event.LoginSuccessEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;

/**
 * Created by khaerothe on 2015/5/11.
 */
public class User extends AbstractAnnotatedAggregateRoot {

    private static final long serialVersionUID = -8790365271419005881L;
    @AggregateIdentifier
    private String id;

    private String userName;

    private String passwordHash;

    private Date lastLogin;

    private Date createTime;

    private boolean isLogin = false;

    User(){

    }

    public User(String id, String username, String passwordHash){
        this.id = id;
        this.userName = username;
        this.passwordHash = passwordHash;
        this.createTime = new Date();
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public boolean authentication(String password){
        if(BCrypt.checkpw(password, passwordHash)){
            apply(new LoginSuccessEvent(this.id));
            return true;
        }else{
            apply(new LoginFailedEvent(this.id));
            return false;
        }
    }

    @EventSourcingHandler
    public void handle(LoginSuccessEvent event){
        this.lastLogin = event.getLoginTime();
        this.isLogin = true;
    }

    @EventSourcingHandler
    public void handle(LoginFailedEvent event){
        this.isLogin = false;
    }

}
