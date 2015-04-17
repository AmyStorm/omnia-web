package com.omnia.user.entity;

/**
 *
 * User entity
 * Created by Administrator on 2015/4/17.
 */
public class User {

    private String id;

    private String name;

    private String key;

    public User(String id, String name, String key){
        this.id = id;
        this.name = name;
        this.key = key;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
