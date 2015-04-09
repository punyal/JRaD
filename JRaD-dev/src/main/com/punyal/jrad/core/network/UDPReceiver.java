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
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.EventObject;

public class UDPReceiver extends Thread {
    private Message message;
    private boolean stopped = false;
    private MessageListener mlistener = new MessageListener();
    
    /**
     * Constructor to set all parameters for the connection
     * @param message with the socket to configure the incoming connection.
     */
    public UDPReceiver(Message message)  {
        this.message =  message;
    }
    
    /**
     * Add a event listener for incoming messages
     * @param listener for incoming events
     */
    public void addListener(MessageListenerInt listener) {
        mlistener.addMessageListener(listener);
    }
    
    /**
     * Kill thread
     */
    public void halt() {
        this.stopped = true;
    }
    
    /**
     * Runnable thread
     */
    @Override
    public void run() {
        try {
            byte[] buffer = new byte[65507];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            message.socket.setSoTimeout(1000); // 1 sec timeout
            
            while(true) {
                try {
                    message.socket.receive(dp);
                    message.response = new Message();
                    message.response.setBytes(dp.getData());
                    message.response.parse();
                    mlistener.newIncommingMessage(new EventObject(message));
                    message.socket.close();
                    Thread.yield();
                } catch (SocketTimeoutException e) {
                    mlistener.newIncommingMessage(new EventObject(message));
                    message.socket.close();
                }
            }
        } catch (SocketException e1) {
            //System.out.println("Socket closed" + e1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}