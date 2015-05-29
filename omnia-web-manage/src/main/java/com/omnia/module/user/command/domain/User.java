package com.omnia.module.user.command.domain;

import com.omnia.module.user.command.domain.event.LoginFailedEvent;
import com.omnia.module.user.command.domain.event.LoginSuccessEvent;
import com.omnia.module.user.command.domain.event.LogoutSuccessEvent;
import com.omnia.module.user.command.domain.event.UserCreateEvent;
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
    private String identifier;

    private String userName;

    private String passwordHash;

    private Date lastLogin;

    private Date createTime;

    private boolean isLogin = false;

    User(){

    }

//    public User(CreateUserCommand command){
//        this.userName = command.getUsername();
//        this.passwordHash = command.getPassword();
//    }

    public User(UserCreateEvent userCreateEvent){
        apply(userCreateEvent);
    }

    public User(String identifier, String userName, String passwordHash){
        apply(new UserCreateEvent(identifier, userName, passwordHash));
    }

    @Override
    public String getIdentifier() {
        return identifier;
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
            apply(new LoginSuccessEvent(this.identifier));
            return true;
        }else{
            apply(new LoginFailedEvent(this.identifier));
            return false;
        }
    }

    public boolean logout(){
        apply(new LogoutSuccessEvent(this.identifier));
        return true;
    }

    public void applyEvent(Object event){
        apply(event);
    }

    @EventSourcingHandler
    private void handle(LoginSuccessEvent event){
        this.lastLogin = event.getLoginTime();
        this.isLogin = true;
    }

    @EventSourcingHandler
    private void handle(LoginFailedEvent event){
        this.identifier = event.getIdentifier();
        this.isLogin = false;
    }

    @EventSourcingHandler
    private void handle(LogoutSuccessEvent event){
        this.identifier = event.getIdentifier();
        this.isLogin = false;
    }

    @EventSourcingHandler
    private void handle(UserCreateEvent event){
        this.identifier = event.getIdentifier();
        this.userName = event.getUserName();
        this.passwordHash = event.getPassword();
        this.createTime = event.getCreateTime();
    }
}
