package com.punyal.jrad.core.network.events;

import java.util.EventObject;
import javax.swing.event.EventListenerList;

public class MessageListener {
    protected EventListenerList listenerList = new EventListenerList();
    
    public void addMessageListener(MessageListenerInt listener) {
        listenerList.add(MessageListenerInt.class, listener);
    }
    public void removeMessageListenet(MessageListenerInt listener) {
        listenerList.remove(MessageListenerInt.class, listener);
    }
   public void newIncommingMessage(EventObject evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i=0; i<listeners.length; i = i+2) {
            if(listeners[i] == MessageListenerInt.class) {
                ((MessageListenerInt) listeners[i+1]).newIncomingMessage(evt);
            }
        }
    }
}