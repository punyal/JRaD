/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import com.punyal.jrad.core.radius.AttributesMessage;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.CODE_BITS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.IDENTIFIER_BITS;

import com.punyal.jrad.core.radius.EmptyMessage;
import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.core.radius.RADIUS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.LENGTH_BITS;
import com.punyal.jrad.core.radius.Request;
import com.punyal.jrad.core.radius.Response;
import java.util.ArrayList;


public class DataSerializer {
    
    private DatagramWriter writer;
    
    public byte[] serializeRequest(Request request){
        writer = new DatagramWriter();
        RADIUS.Code code = request.getCode();
        serializeMessage(request, code == null ?  0: code.value);
        return writer.toByteArray();
    }
    public byte[] serializeResponse(Response response){
        writer = new DatagramWriter();
        RADIUS.Code code = response.getCode();
        serializeMessage(response, code == null ?  0: code.value);
        return writer.toByteArray();
    }
    
    public byte[] serializeEmptyMessage(EmptyMessage message){
        writer = new DatagramWriter();
        serializeMessage(message, 0);
        return writer.toByteArray();
    }
    
    private void serializeMessage(Message message, int code){
        writer.write(code, CODE_BITS);
        writer.write(message.getMID(), IDENTIFIER_BITS);
        writer.write(message.getLength(), LENGTH_BITS);
        writer.writeBytes(message.getAuthenticator());
        
        // Attributes
        ArrayList<AttributesMessage> attributes = message.getAttributes();
        attributes.stream().forEach((attribute) -> {
            switch(attribute.getType()) {
                case VENDOR_SPECIFIC:
                    writer.write(attribute.getTypeValue(),8);
                    writer.write(attribute.getVendorValue().length+8,8);
                    writer.write(attribute.getVendorID(),32);
                    writer.write(attribute.getVendorType(),8);
                    writer.write(attribute.getVendorValue().length+2,8);
                    writer.writeBytes(attribute.getVendorValue());
                    break;
                case CHAP_PASSWORD:
                    break;
                default:
                    writer.write(attribute.getTypeValue(),8);
                    writer.write(attribute.getValue().length+2,8);
                    writer.writeBytes(attribute.getValue());
                    break;
            }
        });
    }
}