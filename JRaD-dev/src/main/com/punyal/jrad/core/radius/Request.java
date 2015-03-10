/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.radius;


/**
 * Standard RADIUS request class
 */

public class Request extends Message {
    /** The request code. */
    private final RADIUS.Code code;
    
    /** The current response for the request. */
	private Response response;
    
        /**
     * Instantiates a new request with the specified RADIUS code
     * 
     * @param code the request code
     */
    public Request(RADIUS.Code code){
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
    public Request setAuthenticator(String authenticator) {
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
    public Request setAuthenticator(byte[] authenticator){
        super.setAuthenticator(authenticator);
        return this;
    }
    
    private void validateBeforeSending() {
        if(getDestination() == null) throw new NullPointerException("Destination is null");
        if(getDestinationPort() == 0) throw new NullPointerException("Destination port is 0");
    }
    
    
    //////////// Some static factory methods for convience ////////////
    
    /**
     * Convenience factory method to construct a ACCESS-REQUEST
     * 
     * @return a new ACCESS-REQUEST
     */
    public static Request newAccess() { return new Request(RADIUS.Code.ACCESS_REQUEST); }
    
    /**
     * Convenience factory method to construct a ACCOUNTING_REQUEST
     * 
     * @return a new ACCOUNTING_REQUEST
     */
    public static Request newAccounting() { return new Request(RADIUS.Code.ACCOUNTING_REQUEST); }
    
    /**
     * Convenience factory method to construct a STATUS_CLIENT
     * 
     * @return a new STATUS_CLIENT
     */
    public static Request newStatusClient() { return new Request(RADIUS.Code.STATUS_CLIENT); }
    
    
}