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
        
        ACCESS_REQUEST          (1),
        ACCESS_ACCEPT           (2),
        ACCESS_REJECT           (3),
        ACCOUNTING_REQUEST      (4),
        ACCOUNTING_RESPONSE     (5),
        ACCESS_CHALLENGE       (11),
        STATUS_SERVER          (12),  /** (Experimental) */
        STATUS_CLIENT          (13),  /** (Experimental) */
        RESERVED              (255);
        
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
                   case   1: return ACCESS_REQUEST;
                   case   2: return ACCESS_ACCEPT;
                   case   3: return ACCESS_REJECT;
                   case   4: return ACCOUNTING_REQUEST;
                   case   5: return ACCOUNTING_RESPONSE;
                   case  11: return ACCESS_CHALLENGE;
                   case  12: return STATUS_SERVER;
                   case  13: return STATUS_CLIENT;
                   case 255: return RESERVED;
                   default: throw new IllegalArgumentException("Unknown RADIUS code "+value); 
            }
        }        
    }
    
    /**
     * RADIUS RFC 2865 defines some Attributes:
     * User-Name, User-Password, CHAP-Password, NAS-IP-Address, NAS-Port,
     * Service-Type, Framed-Protocol, Framed-IP-Address, Framed-IP-Netmask,
     * Framed-Routing, Filter-Id, Framed-MTU, Framed-Compression, Login-IP-Host,
     * Login-Service, Login-TCP-Port, (unassigned) Reply-Message,
     * Callback-Number, Callback-Id, (unassigned), Framed-Route,
     * Framed-IPX-Network, State, Class, Vendor-Specific, Session-Timeout
     * Idle-Timeout, Termination-Action, Called-Station-Id,
     * Calling-Station-Id, NAS-Identifier, Proxy-State, Login-LAT-Service,
     * Login-LAT-Node, Login-LAT-Group, Framed-AppleTalk-Link,
     * Framed-AppleTalk-Network, Framed-AppleTalk-Zone,
     * (reserved for accounting), CHAP-Challenge, NAS-Port-Type, Port-Limit,
     * Login-LAT-Port
     */
    public enum  Attribute{
        
        USER_NAME                   (1),
        USER_PASSWORD               (2),
        CHAP_PASSWORD               (3),
        NAS_IP_ADDRESS              (4),
        NAS_PORT                    (5),
        SERVICE_TYPE                (6),
        FRAMED_PROTOCOL             (7),
        FRAMED_IP_ADDRESS           (8),
        FRAMED_IP_NETMASK           (9),
        FRAMED_ROUTING             (10),
        FILTER_ID                  (11),
        FRAMED_MTU                 (12),
        FRAMED_COMPRESSION         (13),
        LOGIN_IP_HOST              (14),
        LOGIN_SERVICE              (15),
        LOGIN_TCP_PORT             (16),
        REPLY_MESSAGE              (18),
        CALLBACK_NUMBER            (19),
        CALLBACK_ID                (20),
        FRAMED_ROUTE               (22),
        FRAMED_IPX_NETWORK         (23),
        STATE                      (24),
        CLASS                      (25),
        VENDOR_SPECIFIC            (26),
        SESSION_TIMEOUT            (27),
        IDLE_TIMEOUT               (28),
        TERMINATION_ACTION         (29),
        CALLED_STATION_ID          (30),
        CALLING_STATION_ID         (31),
        NAS_IDENTIFIER             (32),
        PROXY_STATE                (33),
        LOGIN_LAT_SERVICE          (34),
        LOGIN_LAT_NODE             (35),
        LOGIN_LAT_GROUP            (36),
        FRAMED_APPLETALK_LINK      (37),
        FRAMED_APPLETALK_NETWORK   (38),
        FRAMED_APPLETALK_ZONE      (39),
        CHAP_CHALLENGE             (60),
        NAS_PORT_TYPE              (61),
        PORT_LIMIT                 (62),
        LOGIN_LAT_PORT             (63);
        
        /* The code value. */
	public final int value;
        
        /**
         * Instantiates a new code with the specified code value.
         * 
         * @param value the integer value of the code
         */
        Attribute(int value){
            this.value = value;
        }
        
        /**
        * Converts the specified integer value to a request code.
        *
        * @param value the integer value
        * @return the request code
        * @throws IllegalArgumentException if the integer value is unrecognized
        */
        public static Attribute valueOf(int value) {
            switch (value) {
                   case  1: return USER_NAME;
                   case  2: return USER_PASSWORD;
                   case  3: return CHAP_PASSWORD;
                   case  4: return NAS_IP_ADDRESS;
                   case  5: return NAS_PORT;
                   case  6: return SERVICE_TYPE;
                   case  7: return FRAMED_PROTOCOL;
                   case  8: return FRAMED_IP_ADDRESS;
                   case  9: return FRAMED_IP_NETMASK;
                   case 10: return FRAMED_ROUTING;
                   case 11: return FILTER_ID;
                   case 12: return FRAMED_MTU;
                   case 13: return FRAMED_COMPRESSION;
                   case 14: return LOGIN_IP_HOST;
                   case 15: return LOGIN_SERVICE;
                   case 16: return LOGIN_TCP_PORT;
                   case 18: return REPLY_MESSAGE;
                   case 19: return CALLBACK_NUMBER;
                   case 20: return CALLBACK_ID;
                   case 22: return FRAMED_ROUTE;
                   case 23: return FRAMED_IPX_NETWORK;
                   case 24: return STATE;
                   case 25: return CLASS;
                   case 26: return VENDOR_SPECIFIC;
                   case 27: return SESSION_TIMEOUT;
                   case 28: return IDLE_TIMEOUT;
                   case 29: return TERMINATION_ACTION;
                   case 30: return CALLED_STATION_ID;
                   case 31: return CALLING_STATION_ID;
                   case 32: return NAS_IDENTIFIER;
                   case 33: return PROXY_STATE;
                   case 34: return LOGIN_LAT_SERVICE;
                   case 35: return LOGIN_LAT_NODE;
                   case 36: return LOGIN_LAT_GROUP;
                   case 37: return FRAMED_APPLETALK_LINK;
                   case 38: return FRAMED_APPLETALK_NETWORK;
                   case 39: return FRAMED_APPLETALK_ZONE;
                   case 60: return CHAP_CHALLENGE;
                   case 61: return NAS_PORT_TYPE;
                   case 62: return PORT_LIMIT;
                   case 63: return LOGIN_LAT_PORT;
                   default: throw new IllegalArgumentException("Unknown RADIUS 2865 Attribute "+value); 
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
		
	/** The code value of an empty message. */
	public static final int EMPTY_CODE = 0;
    }
    
}