package com.punyal.jrad.core.test;

import java.util.EventListener;
import java.util.EventObject;
import javax.swing.event.EventListenerList;


class MyEvent extends EventObject {
    public MyEvent(Object source) {
        super(source);
    }
}

interface MyEventListener extends EventListener {
  public void myEventOccurred(MyEvent evt);
}

class MyClass {
  protected EventListenerList listenerList = new EventListenerList();

  public void addMyEventListener(MyEventListener listener) {
    listenerList.add(MyEventListener.class, listener);
  }
  public void removeMyEventListener(MyEventListener listener) {
    listenerList.remove(MyEventListener.class, listener);
  }
  void fireMyEvent(MyEvent evt) {
    Object[] listeners = listenerList.getListenerList();
    for (int i = 0; i < listeners.length; i = i+2) {
      if (listeners[i] == MyEventListener.class) {
        ((MyEventListener) listeners[i+1]).myEventOccurred(evt);
      }
    }
  }
}


public class EventTester {
    
    public static void main(String[] args){
        MyClass c = new MyClass();
        MyEventListener event = new MyEventListener() {

            @Override
            public void myEventOccurred(MyEvent evt) {
                System.out.println("fired " + evt);
            }
        };
        
        c.addMyEventListener(event);
        
        System.out.println("---");
        
        c.fireMyEvent(new MyEvent(c));
        System.out.println("---");
    }
}
