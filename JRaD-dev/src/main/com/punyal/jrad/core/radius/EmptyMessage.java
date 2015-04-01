/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.radius;

/**
 * EmptyMessage represents a empty RADIUS message.
 */
public class EmptyMessage extends Message {
    
    /**
     * Instantiates a new empty message.
     * 
     * @param secretKey
     * @param code the message type
     */
    public EmptyMessage(String secretKey, RADIUS.Code code) {
        super(secretKey, code);
    }
    
    /**
     * Instantiates a new empty message.
     * 
     * @param secretKey
     * @param code the message type
     */
    public EmptyMessage(byte[] secretKey, RADIUS.Code code) {
        super(secretKey, code);
    }

    
    /* (non-Javadoc)
     * @see java.langObject#toString()
     */
    @Override
    public String toString() {
        String authenticator = getAuthenticatorString();
        if(authenticator == null) authenticator = "no payload";
        return String.format("%s\t\tMID=%d LEN=%d [%s]", getCode(), getMID(), getLength(), authenticator );
    }
    
}