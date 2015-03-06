/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.elements;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class RawData {
    public final byte[] bytes;
    private InetAddress address;
    private int port;
    
    public RawData(byte[] bytes) {
        this.bytes = bytes;
    }
    
    public RawData(byte[] bytes, InetAddress address, int port) {
        this.bytes = bytes;
        this.address = address;
        this.port = port;
    }
    
    public byte[] getBytes() {
        return this.bytes;
    }
    
    public int getSize() {
        return this.bytes.length;
    }
    
    public InetAddress getAddress() {
        return this.address;
    }
    
    public void setAddress(InetAddress address) {
        this.address = address;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void setPort() {
        this.port = port;
    }
    
    public InetSocketAddress getInetSocketAddress(){
        InetSocketAddress socket =  new InetSocketAddress(address, port);
        return socket;
    }
    
    
}