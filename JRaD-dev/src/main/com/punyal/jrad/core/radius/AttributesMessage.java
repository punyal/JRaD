/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */
package com.punyal.jrad.core.radius;

import com.punyal.jrad.core.Utils;
import static com.punyal.jrad.core.radius.Attribute.Field.STRING;


public class AttributesMessage extends AttributesRADIUS {
    private int chapIdent;
    private int vendorID;
    private int vendorType;
    private int vendorLength;
    private byte[] vendorValue;
    
    /**
     * Constructor using Attribute type
     * @param type 
     */
    public AttributesMessage(RADIUS.Type type) {
        super(RADIUS.getAttributeRADIUS(type.getValue()));
    }
    
    /**
     * Create a readable string ready to print with all the attribute information
     * @return 
     */
    public String print() {
        StringBuilder info = new StringBuilder();
        String value;
        if(this.getFieldType().equals(STRING)) value = this.getValueString();
        else value = Utils.toHexString(this.getValue());
        info.append(String.format(" %-2s %-22s %s",
                this.getTypeValue(),
                this.getType(),
                value));
        
        return info.toString();
    }
    
    /**
     * Get Chap Ident
     * @return Chap Ident
     */
    public int getChapIdent() {return this.chapIdent;}
    
    /**
     * Set Chap Ident
     * @param chapIdent 
     */
    public void setChapIdent(int chapIdent) {this.chapIdent = chapIdent;}
    
    /**
     * Get Vendor ID
     * @return Vendor ID
     */
    public int getVendorID() {return this.vendorID;}
    
    /**
     * Set Vendor ID
     * @param vendorID 
     */
    public void setVendorID(int vendorID) {this.vendorID = vendorID;}
    
    /**
     * Get Vendor Type
     * @return Vendor Type
     */
    public int getVendorType() {return this.vendorType;}
    
    /**
     * Set Vendor Type
     * @param vendorType 
     */
    public void setVendorType(int vendorType) {this.vendorType = vendorType;}
    
    /**
     * Get Vendor Value
     * @return byte array
     */
    public byte[] getVendorValue() {return this.vendorValue;}
    
    /**
     * Set Vendor Value
     * @param value 
     */
    public void setVendorValue(byte[] value) {
        this.vendorValue = value;
        this.vendorLength = this.vendorValue.length + 2; // vendor lenght = length(vendor type + vendor length + vendor value)
    }
    
}
  