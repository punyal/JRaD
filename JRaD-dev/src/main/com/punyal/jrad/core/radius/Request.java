/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.radius;

import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.network.serialization.DataParser;
import com.punyal.jrad.core.network.serialization.Serializer;
import com.punyal.jrad.elements.RawData;


/**
 * Standard RADIUS request class
 */

public class Request extends Message {
    /** The request code. */
    
    /** The current response for the request. */
	private Response response;
    
    /**
     * Instantiates a new request with the specified RADIUS code
     * 
     * @param secretKey
     * @param code the request code
     */
    public Request(String secretKey, RADIUS.Code code){
        super(secretKey, code);
    }
    
    /**
     * Instantiates a new request with the specified RADIUS code
     * 
     * @param secretKey
     * @param code the request code
     */
    public Request(byte[] secretKey, RADIUS.Code code){
        super(secretKey, code);
    }
    
    public Request(String secretKey){
        super(secretKey);
    }
    
    public Request(byte[] secretKey){
        super(secretKey);
    }
    
    
    
    private void validateBeforeSending() {
        if(getDestination() == null) throw new NullPointerException("Destination is null");
        if(getDestinationPort() == 0) throw new NullPointerException("Destination port is 0");
    }
    
    
    /*
    public void serialize() {
        Serializer buffer = new Serializer();
        RawData buf = buffer.serialize(this);
    }
    
    public void parse(){
        this.clearAttributes();
        DataParser parser = new DataParser(this.getBytes());
        parser.parseMessagetest(this);
    }*/
    
    //////////// Some static factory methods for convience ////////////
    
    /**
     * Convenience factory method to construct a ACCESS-REQUEST
     * 
     * @return a new ACCESS-REQUEST
     */
    //public static Request newAccess() { return new Request(RADIUS.Code.ACCESS_REQUEST); }
    
    /**
     * Convenience factory method to construct a ACCOUNTING_REQUEST
     * 
     * @return a new ACCOUNTING_REQUEST
     */
    //public static Request newAccounting() { return new Request(RADIUS.Code.ACCOUNTING_REQUEST); }
    
    /**
     * Convenience factory method to construct a STATUS_CLIENT
     * 
     * @return a new STATUS_CLIENT
     */
    //public static Request newStatusClient() { return new Request(RADIUS.Code.STATUS_CLIENT); }
    
    
}