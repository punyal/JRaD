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
    private DatagramSocket socket;
    private boolean stopped = false;
    
    public UDPSender(Message message) throws SocketException {
        this.message = message;
        this.socket = new DatagramSocket();
        this.socket.connect(message.getDestination(), message.getDestinationPort());
    }
    
    public void halt() {
        this.stopped = true;
    }
    
    public DatagramSocket getSocket() {
        return this.socket;
    }
    
    @Override
    public void run() {
        try {
            DatagramPacket output = new DatagramPacket(this.message.getBytes(), this.message.getBytes().length, this.message.getDestination(), this.message.getDestinationPort());
            socket.send(output);
            Thread.yield();
        } catch (IOException e){
            System.err.println(e);
        }
        
    }
    
}