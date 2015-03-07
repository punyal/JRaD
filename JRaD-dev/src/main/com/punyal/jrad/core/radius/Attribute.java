package com.punyal.jrad.core.radius;

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
    private int type;
    private byte[] value;
    

    
    public void Attribute() {
        this.type = 0;
        this.value = "NULL".getBytes(UTF8_CHARSET);
    }
    
    public void Attribute(int type, byte[] value) {
        this.type = type;
        this.value = value;
    }
    
    public Attribute(int type, String testString) {
        this.type = type;
        this.value = testString.getBytes(UTF8_CHARSET);
    }
    
    
    public void setType(int type) {this.type = type;}
    
    public int getType() {return this.type;}
    
    public void setValue(byte[] value) {this.value = value;}
    
    public void setValue(String value) {this.value = value.getBytes(UTF8_CHARSET);}
    
    public byte[] getValue() {return this.value;}
    
    public String getValueString() {return new String(this.value);}
    
}