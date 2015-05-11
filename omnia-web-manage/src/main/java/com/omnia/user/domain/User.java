package com.omnia.user.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.Date;

/**
 * Created by Administrator on 2015/5/11.
 */
public class User extends AbstractAnnotatedAggregateRoot {

    private static final long serialVersionUID = -8790365271419005881L;
    @AggregateIdentifier
    private String userId;

    private String userName;

    private String passwordHash;

    private Date lastLogin;

    private boolean isLogin = false;


}
