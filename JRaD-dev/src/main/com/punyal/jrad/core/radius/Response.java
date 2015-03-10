/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.radius;

import com.punyal.jrad.core.network.serialization.Serializer;
import com.punyal.jrad.elements.RawData;


/**
 * Standard RADIUS request class
 */

public class Response extends Message {
    /** The Response code. */
    private final RADIUS.Code code;
    
    public Response(RADIUS.Code code){
        super();
        this.code = code;
    }
    
    /**
     * Gets the request code
     * @return 
     */
    @Override
    public RADIUS.Code getCode(){
        return code;
    }
    
    /**
     * Sets the authenticator as String
     * 
     * @param authenticator
     * @return 
     */
    @Override
    public Response setAuthenticator(String authenticator) {
        super.setAuthenticator(authenticator);
        return this;
    }
    
    /**
     * Sets the authenticator as byte array
     * 
     * @param authenticator
     * @return 
     */
    @Override
    public Response setAuthenticator(byte[] authenticator){
        super.setAuthenticator(authenticator);
        return this;
    }
    
    
    
    
    
    public void serialize() {
        Serializer buffer = new Serializer();
        RawData buf = buffer.serialize(this);
    }
    
    
    
    public static Response newAccessAccept() {return new Response(RADIUS.Code.ACCESS_ACCEPT);}
    public static Response newAccessReject() {return new Response(RADIUS.Code.ACCESS_REJECT);}
    public static Response newAccountingResponse() {return new Response(RADIUS.Code.ACCOUNTING_RESPONSE);}
    public static Response newAccessChallenge() {return new Response(RADIUS.Code.ACCESS_CHALLENGE);}
    public static Response newStatusServer() {return new Response(RADIUS.Code.STATUS_SERVER);}
    
    
}