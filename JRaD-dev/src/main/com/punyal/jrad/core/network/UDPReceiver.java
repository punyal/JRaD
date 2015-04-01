/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver extends Thread {
    DatagramSocket socket;
    private boolean stopped = false;
    
    public UDPReceiver(DatagramSocket ds) throws SocketException {
        this.socket = ds;
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
                socket.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println(s);
                Thread.yield();
            } catch(IOException ex) {
                System.err.println(ex);
            }
        }
    }
}