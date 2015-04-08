/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network;

import com.punyal.jrad.core.network.events.MessageListener;
import com.punyal.jrad.core.network.events.MessageListenerInt;
import com.punyal.jrad.core.radius.Message;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.EventObject;

public class UDPReceiver extends Thread {
    private Message message;
    private boolean stopped = false;
    private MessageListener mlistener = new MessageListener();
    
    public UDPReceiver(Message message)  {
        this.message =  message;
    }
    
    public void addListener(MessageListenerInt listener) {
        mlistener.addMessageListener(listener);
    }
    
    public void halt() {
        this.stopped = true;
    }
    
    @Override
    public void run() {
        byte[] buffer = new byte[65507];
        while (true) {
            if(stopped)
                return;
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            try {
                message.socket.receive(dp);
                message.response = new Message();
                message.response.setBytes(dp.getData());
                message.response.parse();
                
                mlistener.newIncommingMessage(new EventObject(message));
                Thread.yield();
            } catch(IOException ex) {
                System.err.println(ex);
            }
        }
    }
}