package com.omnia.infrastructure.eventstore.geteventstore.actor;

/**
 * Created by khaerothe on 2015/5/20.
 */
public class WriteCallback {

    private final Object identifier;

    public  WriteCallback(Object identifier){
        this.identifier = identifier;
    }

    public Object getIdentifier() {
        return identifier;
    }
}
