/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.radius;

import java.nio.charset.Charset;

/**
 * Radius defines several constants.
 * <ul>
 * <li>Default Configuration</li>
 * <li>RADIUS Codes</li>
 * </ul>
 */

public class RADIUS {
    
    /** RFC 2865 RADIUS port */
    public static final int DEFAULT_RADIUS_PORT = 1682;
    
    /** The RADIUS charset is UTF-8 */
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    
    private RADIUS() {
        // prevent initialization
    }
    
    /**
     * RADIUS defines some message codes (in decimal):
     * Access-Request, Access-Accept, Access-Reject, Accounting-Request,
     * Accounting-Response, Access-Challenge, Status-Server, Status-Client,
     * Reserved.
     */
    public enum Code {
        
        /** Access-Request */
        ACCESS_REQUEST(1),
        /** Access-Accept */
        ACCESS_ACCEPT(2),
        /** Access-Reject */
        ACCESS_REJECT(3),
        /** Accounting-Request */
        ACCOUNTING_REQUEST(4),
        /** Accounting-Response */
        ACCOUNTING_RESPONSE(5),
        /** Access_Challenge */
        ACCESS_CHALLENGE(11),
        /** Status-Server (Experimental) */
        STATUS_SERVER(12),
        /** Status-Client (Experimental) */
        STATUS_CLIENT(13),
        /** Accounting-Response */
        RESERVED(255);
        
        /* The code value. */
	public final int value;
        
        /**
         * Instantiates a new code with the specified code value.
         * 
         * @param value the integer value of the code
         */
        Code(int value){
            this.value = value;
        }
        
        /**
        * Converts the specified integer value to a request code.
        *
        * @param value the integer value
        * @return the request code
        * @throws IllegalArgumentException if the integer value is unrecognized
        */
        public static Code valueOf(int value) {
            switch (value) {
                   case 1:     return ACCESS_REQUEST;
                   case 2:     return ACCESS_ACCEPT;
                   case 3:     return ACCESS_REJECT;
                   case 4:     return ACCOUNTING_REQUEST;
                   case 5:     return ACCOUNTING_RESPONSE;
                   case 11:    return ACCESS_CHALLENGE;
                   case 12:    return STATUS_SERVER;
                   case 13:    return STATUS_CLIENT;
                   case 255:   return RESERVED;
                   default: throw new IllegalArgumentException("Unknwon RADIUS request code "+value); 
            }
        }        
    }
    
    /**
     * RADIUS message format.
     * 
     * 0                   1                   2                   3
     * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |     Code      |  Identifier   |            Length             |
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |                                                               |
     * |                         Authenticator                         |
     * |                                                               |
     * |                                                               |
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     * |  Attributes ...
     * +-+-+-+-+-+-+-+-+-+-+-+-+-
     */
    /**
     * RADIUS attributes format.
     * 0                   1                   2
     * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     * |     Type      |    Length     |  Value ...
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     */
    public class MessageFormat {
        
        /** number of bits used for the encoding of the Code */
        public static final int CODE_BITS = 8;
        
        /** number of bits used for the encoding of the Identifier */
        public static final int IDENTIFIER_BITS = 8;
        
        /** number of bits used for the encoding of the Length */
        public static final int LENGTH_BITS = 16;
        
        /** number of bits used for the encoding of the Authenticator */
        public static final int AUTHENTICATOR_BITS = 128; // 16 octets
        
        /** number of bits used for the encoding of the Attribute Type */
        public static final int ATTRIBUTE_TYPE_BITS = 8;
        
        /** number of bits used for the encoding of the Attribute Length */
        public static final int ATTRIBUTE_LENGTH_BITS = 8;
    }
    
}