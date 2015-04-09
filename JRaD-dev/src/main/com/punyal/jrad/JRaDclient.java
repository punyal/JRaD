/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */
package com.punyal.jrad;

import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.network.UDPReceiver;
import com.punyal.jrad.core.network.UDPSender;
import com.punyal.jrad.core.network.events.MessageListenerInt;
import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.core.radius.RADIUS;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.EventObject;

public class JRaDclient {
    private int seed;
    private String secretKey;
    private InetAddress serverIP;
    private int serverPort;
    
    private MessageListenerInt newMessage = new MessageListenerInt() {

        @Override
        public void newIncomingMessage(EventObject evt) {
            Message message = (Message) evt.getSource();
            message.print();
            message.response.print();
        }
    };
    
    /**
     * Empty Constructor
     */
    public JRaDclient() {
        SecureRandom random = new SecureRandom();
        seed = random.nextInt();
    }
    
    /**
     * Set the Secret Key
     * @param secretKey information
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    
    /**
     * Set the Server Parameters
     * @param ip of the server
     * @param port of the server
     */
    public void setServer(String ip, int port) {
        try {
            this.serverIP = InetAddress.getByName(ip);
            this.serverPort = port;
        } catch (UnknownHostException ex) {
            System.err.println("Eception "+JRaDclient.class.getName());
        }
        
    }
    
    /**
     * Listener for incoming events
     * @param listener for incoming events
     */
    public void addListener(MessageListenerInt listener) {
        newMessage = listener;
    }
    
    /** 
     * Create a new Message with the correct ID and Authenticator
     * @return 
     */
    private Message createNewMessage() {
        Message message = new Message();
        // Set SecretKey for encrypted attributes
        message.setSecretKey(this.secretKey);
        // Create a new Message ID
        seed++;
        seed &= 0xFF;
        message.setMID(seed);
        // Create a new randomized Message Authenticator
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        message.setAuthenticator(bytes);
        // Set Timestamp of the creation
        message.setTimestamp();
        return message;
    }
    
    /**
     * Send a message
     * @param message 
     */
    private void sendMessage(Message message) {
        try {
            message.setDestination(serverIP);
            message.setDestinationPort(serverPort);
            UDPSender sender = new UDPSender(message);
            sender.start();
            UDPReceiver receiver = new UDPReceiver(message);
            receiver.addListener(newMessage);
            receiver.start();
            
        } catch (SocketException ex){
            System.err.println("Socket Exception: "+ex);
        }
    }
    
    // Helper functions
    
    /**
     * Helper to check the authentication of an user
     * @param userName
     * @param userPassword
     */
    public void authenticate(String userName, String userPassword) {
        Message message = createNewMessage();
        message.setCode(RADIUS.Code.ACCESS_REQUEST);
        message.newAttribute(RADIUS.Type.USER_NAME, Utils.stringToByteArray(userName));
        message.newAttribute(RADIUS.Type.USER_PASSWORD, Utils.stringToByteArray(userPassword));
        sendMessage(message);
    }
}