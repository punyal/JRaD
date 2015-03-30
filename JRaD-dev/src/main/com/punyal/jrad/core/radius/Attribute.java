/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.radius;

import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.ATTRIBUTE_LENGTH_BITS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.ATTRIBUTE_TYPE_BITS;
import static com.punyal.jrad.core.radius.RADIUS.UTF8_CHARSET;

/**
     * RADIUS attributes format.
     * 0                   1                   2
     * 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     * |     Type      |    Length     |  Value ...
     * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
     */

public class Attribute {
    private RADIUS.Type type;
    private int length;
    private byte[] value;
    

    /**
     * Empty Constructor
     */
    public Attribute() {
        this.type = RADIUS.Type.valueOf(0);
        this.value = "NULL".getBytes(UTF8_CHARSET);
        this.length = ATTRIBUTE_TYPE_BITS/8 + ATTRIBUTE_LENGTH_BITS/8 + this.value.length;
    }
    
    /**
     * Attribute Constructor
     * @param type
     * @param value as byte array
     */
    public Attribute(RADIUS.Type type, byte[] value) {
        this.type = type;
        this.value = value;
        this.length = ATTRIBUTE_TYPE_BITS/8 + ATTRIBUTE_LENGTH_BITS/8 + this.value.length;
    }
    
    /**
     * Attribute Constructor
     * @param type
     * @param testString as String
     */
    public Attribute(RADIUS.Type type, String testString) {
        this.type = type;
        this.value = testString.getBytes(UTF8_CHARSET);
        this.length = ATTRIBUTE_TYPE_BITS/8 + ATTRIBUTE_LENGTH_BITS/8 + this.value.length;
    }
    
    /**
     * Set attribute type
     * @param type 
     */
    public void setType(RADIUS.Type type) {this.type = type;}
    
    /**
     * Get attribute type
     * @return attribute type
     */
    public RADIUS.Type getType() {return this.type;}
    
    /**
     * Get the value type of the attribute
     * @return attribute value
     */
    public int getTypeValue() {return this.type.getValue();}
    
    /**
     * Set attribute value
     * @param value as byte array
     */
    public void setValue(byte[] value) {
        this.value = value;
        this.length = ATTRIBUTE_TYPE_BITS/8 + ATTRIBUTE_LENGTH_BITS/8 + this.value.length;
    }
    
    /**
     * Set attribute value
     * @param value as String
     */
    public void setValue(String value) {
        this.value = value.getBytes(UTF8_CHARSET);
        this.length = ATTRIBUTE_TYPE_BITS/8 + ATTRIBUTE_LENGTH_BITS/8 + this.value.length;
    }
    
    /**
     * Get attribute value
     * @return byte array
     */
    public byte[] getValue() {return this.value;}
    
    /**
     * Get attribute value
     * @return String
     */
    public String getValueString() {return new String(this.value);}
    
    /**
     * Get attribute length
     * @return length
     */
    public int getLength() {return this.length;}
    
    /**
     * Definition of possible Field types.
     */
    public enum Field {
        
        STRING              (1),
        VALUE               (2),
        ADDRESS             (3),
        VENDOR              (4),
        UNKNOWN           (255);
        
        /* The code value. */
	public final int value;
        
        /**
         * Instantiates a new code with the specified code value.
         * 
         * @param value the integer value of the code
         */
        Field(int value) {
            this.value = value;
        }
        public static Field valueOf(int value) {
            switch (value) {
                   case   1: return STRING;
                   case   2: return VALUE;
                   case   3: return ADDRESS;
                   case   4: return VENDOR;
                   case 255: return UNKNOWN;
                   default: throw new IllegalArgumentException("Unknown Attribute Field "+value); 
            }
        }        
    }
}