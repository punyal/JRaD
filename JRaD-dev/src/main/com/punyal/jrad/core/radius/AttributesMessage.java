/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */
package com.punyal.jrad.core.radius;

import com.punyal.jrad.core.Utils;
import static com.punyal.jrad.core.radius.Attribute.Field.STRING;


public class AttributesMessage extends AttributesRADIUS {

    public AttributesMessage(RADIUS.Type type) {
        super(RADIUS.getAttributeRADIUS(type.getValue()));
    }
    
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
}
  