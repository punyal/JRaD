/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */
package com.punyal.jrad.core.radius;


public class AttributesRADIUS extends Attribute {
    private boolean request;
    private boolean accept;
    private boolean reject;
    private boolean challenge;
    private boolean unique;
    private int minLength;
    private Field fieldType;
    
    /**
     * Constructor of Attribute RADIUS List
     * @param type of the attribute
     * @param minLength of the attribute (can be null) 
     * @param fieldType of the attribute
     * @param request if it is valid for request
     * @param accept if it is valid for accept
     * @param reject
     * @param challenge
     * @param unique 
     */
    public AttributesRADIUS(RADIUS.Type type, int minLength, Field fieldType, boolean request, boolean accept, 
        boolean reject, boolean challenge, boolean unique) {
        super(type, "NULL");
        this.minLength = minLength;
        this.fieldType = fieldType;
        this.request = request;
        this.accept = accept;
        this.reject = reject;
        this.challenge = challenge;
        this.unique = unique;
    }
    
    /**
     * To manage and load RADIUS Attributes
     * @param temp attribute to create.
     */
    public AttributesRADIUS(AttributesRADIUS temp) {
        super(temp.getType(),temp.getValue());
        this.copyAttributesRADIUS(temp);
    }
    
    /**
     * Copy an attribute to other
     * @param att attribute to copy
     */
    private void copyAttributesRADIUS(AttributesRADIUS att) {
        this.request = att.request;
        this.accept = att.accept;
        this.reject = att.reject;
        this.challenge = att.challenge;
        this.unique = att.unique;
        this.minLength = att.minLength;
        this.fieldType = att.fieldType;
        this.setType(att.getType());
        this.setValue(att.getValue());    
    }
    
    /**
     * Get type of the Field
     * @return Field type
     */
    public Field getFieldType() {return this.fieldType;}
    
    /**
     * Check if is a Request Attribute
     * @return 
     */
    public boolean isRequest() {return this.request;}
    
    /**
     * Check if is a Accept Attribute
     * @return 
     */
    public boolean isAccept() {return this.accept;}
    
    /**
     * Check if is a Reject Attribute
     * @return 
     */
    public boolean isReject() {return this.reject;}
    
    /**
     * Check if is a Challenge Attribute
     * @return 
     */
    public boolean isChallenge() {return this.challenge;}
    
    /**
     * Check if is an Unique Attribute
     * @return 
     */
    public boolean isUnique() {return this.unique;}
    
    /**
     * Get the minimal length of 
     * @return 
     */
    public int getMinLength() {return this.minLength;}
}