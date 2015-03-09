/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */
package com.punyal.jrad.core.radius;


public class AttributesRADIUS extends Attribute {
    private boolean request;
    private boolean acept;
    private boolean reject;
    private boolean challenge;
    private boolean unique;
    private int minLength;
    private Field fieldType;

    public AttributesRADIUS(RADIUS.Type type, int minLength, Field fieldType, boolean request, boolean acept, 
            boolean reject, boolean challenge, boolean unique) {
        super(type, "NULL");
        this.minLength = minLength;
        this.fieldType = fieldType;
        this.request = request;
        this.acept = acept;
        this.reject = reject;
        this.challenge = challenge;
        this.unique = unique;
    }
    
    public Field getFieldType() {return this.fieldType;}
    public boolean isRequest() {return this.request;}
    public boolean isAcept() {return this.acept;}
    public boolean isReject() {return this.reject;}
    public boolean isChallenge() {return this.challenge;}
    public boolean isUnique() {return this.unique;}
    public int getMinLength() {return this.minLength;}
}