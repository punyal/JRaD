package com.punyal.jrad.core.network.events;


import java.util.EventListener;
import java.util.EventObject;

public interface MessageListenerInt extends EventListener {
    
    /**
     * Method to execute when an event appear
     * @param evt 
     */
    public void newIncomingMessage(EventObject evt);
}