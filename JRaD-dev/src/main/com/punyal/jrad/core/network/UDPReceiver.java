/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network;

import com.punyal.jrad.JRaDclient;
import com.punyal.jrad.core.radius.Message;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;

public class UDPReceiver extends Thread {
    private ActionEvent event;
    private Message message;
    private boolean stopped = false;
    
    public UDPReceiver(Message message)  {
        this.message =  message;
        this.event = new ActionEvent(this, 1, "pepe");
    }
    
    public ActionEvent getActionEvent() {
        return this.event;
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
                message.print();
                message.response.print();
                Thread.yield();
            } catch(IOException ex) {
                System.err.println(ex);
            }
        }
    }
}