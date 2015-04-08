/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network;

import com.punyal.jrad.core.radius.Message;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPSender extends Thread {
    private Message message;
    private boolean stopped = false;
    
    /**
     * Set message parameters
     * @param message
     * @throws SocketException 
     */
    public UDPSender(Message message) throws SocketException {
        this.message = message;
        this.message.serialize();
        this.message.socket = new DatagramSocket();
        this.message.socket.connect(message.getDestination(), message.getDestinationPort());
    }
    
    /**
     * Stop thread
     */
    public void halt() {
        this.stopped = true;
    }
       
    /**
     * Get socket connection
     * @return 
     */
    public DatagramSocket getSocket() {
        return this.message.socket;
    }
    
    /**
     * Runnable thread
     */
    @Override
    public void run() {
        try {
            DatagramPacket output = new DatagramPacket(this.message.getBytes(), this.message.getBytes().length, this.message.getDestination(), this.message.getDestinationPort());
            message.socket.send(output);
            Thread.yield();
        } catch (IOException e){
            System.err.println(e);
        }
        
    }
    
    
    
}