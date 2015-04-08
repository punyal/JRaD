/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */
package com.punyal.jrad;

import com.punyal.jrad.core.network.UDPReceiver;
import com.punyal.jrad.core.network.UDPSender;
import com.punyal.jrad.core.network.events.MessageListenerInt;
import com.punyal.jrad.core.radius.Message;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.EventObject;

public class JRaDclient {
    private int seed;
    private String secretKey;
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
    
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    
    public void addListener(MessageListenerInt listener) {
        newMessage = listener;
    }
    
    public Message createNewMessage() {
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
    
    public void sendMessage(Message message) {
        try {
            UDPSender sender = new UDPSender(message);
            sender.start();
            UDPReceiver receiver = new UDPReceiver(message);
            receiver.addListener(newMessage);
            receiver.start();
            
            //receiver.addActionListener(this);
            
            // Handler thisHandler = new Handler(receiver)
            //thisHandler.listenTo();
        } catch (SocketException ex){
            System.err.println("Socket Exception: "+ex);
        }
            
    }


    
    
    
}