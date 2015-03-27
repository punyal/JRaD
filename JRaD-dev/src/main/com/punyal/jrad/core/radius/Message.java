/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.radius;

import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.network.serialization.DataParser;
import com.punyal.jrad.core.network.serialization.Serializer;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.AUTHENTICATOR_BITS;
import static com.punyal.jrad.core.radius.RADIUS.Type.CHAP_PASSWORD;
import static com.punyal.jrad.core.radius.RADIUS.Type.VENDOR_SPECIFIC;
import com.punyal.jrad.elements.RawData;
import java.net.DatagramSocket;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * The class Message models the base class of all RADIUS messages.
 */
public abstract class Message {
    
    /** The Constant NONE in case no MID has been set */
    public static final int NONE = -1;
    
    /** The Constant EMPTY in case no length has been set */
    public static final int MINIMAL = ( RADIUS.MessageFormat.CODE_BITS +
            RADIUS.MessageFormat.IDENTIFIER_BITS + RADIUS.MessageFormat.LENGTH_BITS +
            RADIUS.MessageFormat.AUTHENTICATOR_BITS ) / 8;
    
    /** The Code. On of {Access-Request, Access-Accept, Access-Reject, Accounting-Request
     * ,Accounting-Response, Access-Challenge, Status-Server, Status-Client, Reserved}  */
    private RADIUS.Code code;
    
    /** The 8-bit Message Identification */
    private int mid = NONE;
    
    /** The 16-bit Message Length */
    private int length = MINIMAL;
    
    /** The 128-bit Authenticator (16 octets) */
    private byte[] authenticator;
    
    /** The set of attributes of this message */
    ArrayList <AttributesMessage> Attributes = new ArrayList <> ();
    
    /** The destination address of the message */
    private InetAddress destination;
    
    /** The destination port of the message */
    private int destinationPort;
    
    /** The source address of the message */
    private InetAddress source;
    
    /** The source port of the message */
    private int sourcePort;
    
    /** The serialized message as byte array */
    private byte[] bytes;
    
    /** The socket connection*/
    public DatagramSocket socket;
    
    /**
     * The timestamp when this message has been received or sent or 0 if neither
     * has happened yet.
     */
    private long timestamp;
    private int MINIMUM;
    
    /**
     * Instances a new message with no specified message code.
     */
    public Message() {}
    
    /**
     * Instantiates a new message with the given code. The code must be one of 
     * {Access-Request, Access-Accept, Access-Reject, Accounting-Request
     * ,Accounting-Response, Access-Challenge, Status-Server, Status-Client, Reserved}
     * 
     * @param code the code
     */
    public Message(RADIUS.Code code) {
        this.code = code;
                
    }
    
    /**
     * Gets the message code. If no code has been defined, the code is null.
     * 
     * @return the code
     */
    public RADIUS.Code getCode() {
        return code;
    }
    
    /**
     * Sets the RADIUS message code.
     * Provides a fluent API to chain setters.
     * 
     * @param code the new code
     * @return this Message
     */
    public Message setCode(RADIUS.Code code) {
        this.code = code;
        return this;
    }
    
    /**
     * Gets the 8-bit message identification.
     * 
     * @return the mid
     */
    public int getMID() {
        return mid;
    }
    
    /**
     * Gets the 8-bit message identification as String.
     * 
     * @return the mid
     */
    public String getMIDString() {
        if(mid == NONE) return "NONE";
        return String.valueOf(mid);
    }
    
    /**
     * Sets the 8-bit message identification.
     * Provides a fluent API to chain setters.
     * 
     * @param mid the new mid
     * @return this Message
     */
    public Message setMID(int mid) {
        if(mid >= 1 << 8 || mid < NONE)
			throw new IllegalArgumentException("The MID must be a 8-bit number between 0 and "+((1<<8)-1)+" inclusive but was "+mid);
        if(mid >= (1 << 8) -1) this.mid = NONE;
        else this.mid = mid;
        return this;
    }
    
    public void removeMID() {
        setMID(NONE);
    }
    
    /**
     * Sets the 16-bit message length.
     * Provides a fluent API to chain setters.
     * 
     * @param length the new length
     * @return this Message
     */
    public Message setLength(int length) {
        if(length >= 1<<16 || length < NONE)
			throw new IllegalArgumentException("The length must be a 16-bit number between 0 and "+((1<<8)-1)+" inclusive but was "+length);
        this.length = length;
        return this;
    }
    
    public int getLength() {
        return length;
    }
    
    public void removeLength() {
        setLength(NONE);
    }
    
    /**
     * Gets the raw authenticator.
     * 
     * @return the authenticator.
     */
    public byte[] getAuthenticator() {
        return authenticator;
    }
    
    /**
     * Gets the authenticator in the form of a string. Returns an empty string if no 
     * authenticator is defined.
     * 
     * @return the authenticator as string.
     */
    public String getAuthenticatorString() {
        if(authenticator == null)
            return "";
        return new String(authenticator, RADIUS.UTF8_CHARSET);
    }
    
    /**
     * Sets the UTF-8 bytes from the specified string as payload.
     * Provides a fluent API to chain setters.
     * 
     * @param authenticator the authenticator as string
     * @return this Message
     */
    public Message setAuthenticator(String authenticator) {
        if(authenticator == null) this.authenticator = null;
        else setAuthenticator(authenticator.getBytes(RADIUS.UTF8_CHARSET));
        return this;
    }
    
    /**
     * Sets the authenticator.
     * Provides a fluent API to chain setters.
     * 
     * @param authenticator the new authenticator
     * @return this Message
     */
    public Message setAuthenticator(byte[] authenticator){
        this.authenticator = Arrays.copyOfRange(authenticator, 0, AUTHENTICATOR_BITS/8);
        return this;
    }
    
    
    public int numberOfAttributes() {return this.Attributes.size();}
    
    public void addAttribute(AttributesMessage att) {
        this.Attributes.add(att);
        this.recalculateLength();
    }
    public void newAttribute(RADIUS.Type type, byte[] value, int chapIdent, int vendorID, int vendorType, byte[] vendorValue) {
        AttributesMessage temp = new AttributesMessage(type);
        temp.setValue(value);
        temp.setChapIdent(chapIdent);
        temp.setVendorID(vendorID);
        temp.setVendorType(vendorType);
        temp.setVendorValue(vendorValue);
        this.addAttribute(temp);    
    }
    
    public void newAttribute(RADIUS.Type type, byte[] value) {
        if(type.equals(CHAP_PASSWORD)||type.equals(VENDOR_SPECIFIC))
            throw new IllegalArgumentException("Illegal Attribute type " + type);
        AttributesMessage temp = new AttributesMessage(type);
        temp.setValue(value);
        this.addAttribute(temp);
    }
    
    public void newAttribute(RADIUS.Type type, byte[] value, int chapIdent) {
        if(!type.equals(CHAP_PASSWORD))
            throw new IllegalArgumentException("Illegal Attribute type " + type);
        AttributesMessage temp = new AttributesMessage(type);
        temp.setValue(value);
        temp.setChapIdent(chapIdent);
        this.addAttribute(temp);
    }
    
    public void newAttribute(RADIUS.Type type, int vendorID, int vendorType, byte[] vendorValue) {
        if(!type.equals(VENDOR_SPECIFIC))
            throw new IllegalArgumentException("Illegal Attribute type " + type);
        AttributesMessage temp = new AttributesMessage(type);
        temp.setVendorID(vendorID);
        temp.setVendorType(vendorType);
        temp.setVendorValue(vendorValue);
        this.addAttribute(temp);    
    }
    
    public void clearAttributes () {
        this.Attributes.clear();
        this.recalculateLength();
    }
     
    public ArrayList<AttributesMessage> getAttributes() {return this.Attributes;}
    public AttributesMessage getAttributes(int index) {return this.Attributes.get(index);}
    
    public void recalculateLength() {
        this.length = MINIMAL;
        this.Attributes.stream().forEach((attribute) -> {
            switch(attribute.getType()) {
                case VENDOR_SPECIFIC:
                    this.length += (attribute.getVendorValue().length +8);
                    break;
                case CHAP_PASSWORD:
                    this.length += (attribute.getValue().length +3);
                    break;
                default:
                    this.length += (attribute.getValue().length +2);
                    break;
                }
        });
    }
    
    /**
     * Gets the destination address.
     * 
     * @return the destination
     */
    public InetAddress getDestination() {
        return destination;
    }
    
    /**
     * Sets the destination address.
     * Provides a fluent API to chain setters.
     * 
     * @param destination the new destination
     * @return this Message
     */
    public Message setDestination(InetAddress destination) {
        this.destination = destination;
        return this;
    }
    
    /**
     * Gets the destination port.
     * 
     * @return the destination port
     */
    public int getDestinationPort() {
        return destinationPort;
    }
    
    /**
     * Sets the destination port.
     * Provides a fluent API to chain setters.
     * 
     * @param destinationPort the new destination port
     * @return this Message
     */
    public Message setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
        return this;
    }
    
    /**
     * Gets the source address.
     * 
     * @return the source
     */
    public InetAddress getSource() {
        return source;
    }
    
    /**
     * Sets the source address.
     * Provides a fluent API to chain setters.
     * 
     * @param source the new source
     * @return this Message
     */
    public Message setSource(InetAddress source) {
        this.source = source;
        return this;
    }
    
    /**
     * Gets the source port.
     * 
     * @return the source port
     */
    public int getSourcePort() {
        return sourcePort;
    }
    
    /**
     * Sets the source port.
     * Provides a fluent API to chain setters.
     * 
     * @param sourcePort the new source port
     * @return this Message
     */
    public Message setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
        return this;
    }
    
    /**
    * Gets the serialized message as byte array or null if not serialized yet.
    *
    * @return the bytes of the serialized message or null
    */
    public byte[] getBytes() {
        return bytes;
    }

   /**
    * Sets the bytes of the serialized message.
    * Not part of the fluent API.
    *
    * @param bytes the serialized bytes
    */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

   /**
    * Gets the timestamp.
    *
    * @return the timestamp
    */
    public long getTimestamp() {
        return timestamp;
    }

   /**
    * Sets the timestamp.
    * Not part of the fluent API.
    *
    * @param timestamp the new timestamp
    */
    public void setTimestamp() {
        java.util.Date date= new java.util.Date();
        this.timestamp = date.getTime();
    }
    
     public void serialize() {
        Serializer buffer = new Serializer();
        RawData buf = buffer.serialize(this);
    }
    
    public void parse(){
        this.clearAttributes();
        DataParser parser = new DataParser(this.getBytes());
        parser.parseMessagetest(this);
    }
    
    public void print() {
        System.out.print(Utils.messagePrint(this));
    }
    
    public void send() throws SocketException {
        this.socket = new DatagramSocket();
        this.socket.connect(destination, destinationPort);
    }
    
    
}