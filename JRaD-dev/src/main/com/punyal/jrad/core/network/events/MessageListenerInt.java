package com.punyal.jrad.core.network.events;


import java.util.EventListener;
import java.util.EventObject;

public interface MessageListenerInt extends EventListener {
    public void newIncomingMessage(EventObject evt);
}