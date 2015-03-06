/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.CODE_BITS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.IDENTIFIER_BITS;

import com.punyal.jrad.core.radius.EmptyMessage;
import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.core.radius.RADIUS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.AUTHENTICATOR_BITS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.LENGTH_BITS;
import com.punyal.jrad.core.radius.Request;


public class DataSerializer {
    
    private DatagramWriter writer;
    
    public byte[] serializeRequest(Request request){
        writer = new DatagramWriter();
        RADIUS.Code code = request.getCode();
        serializeMessage(request, code == null ?  0: code.value);
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
        // TODO: ADD attributes!!!
    }
}