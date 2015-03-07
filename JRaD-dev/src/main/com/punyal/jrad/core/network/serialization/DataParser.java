/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import static com.punyal.jrad.core.radius.RADIUS.Code.ACCESS_ACCEPT;
import static com.punyal.jrad.core.radius.RADIUS.Code.ACCOUNTING_REQUEST;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.CODE_BITS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.EMPTY_CODE;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.IDENTIFIER_BITS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.LENGTH_BITS;

import com.punyal.jrad.core.radius.EmptyMessage;
import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.core.radius.RADIUS;
import static com.punyal.jrad.core.radius.RADIUS.MessageFormat.AUTHENTICATOR_BITS;
import com.punyal.jrad.core.radius.Request;

public class DataParser {
    
    private DatagramReader reader;
    private int code;
    private int mid;
    private int length;
    
    public DataParser(byte[] bytes) {
        setBytes(bytes);
    }
    
    public void setBytes(byte[] bytes) {
        this.reader = new DatagramReader(bytes);
        this.code = reader.read(CODE_BITS);
        this.mid = reader.read(IDENTIFIER_BITS);
        this.length = reader.read(LENGTH_BITS);
    }
    
    public boolean isWellFormed() {
        //TODO: check lenght and length field!!
        return true;
    }
    
    public int getCode() {
        return code;
    }
    
    public int getMID() {
        return mid;
    }
    
    public boolean isRequest() {
        return RADIUS.Code.valueOf(code) == ACCESS_ACCEPT || RADIUS.Code.valueOf(code) == ACCOUNTING_REQUEST;
    }
    
    public boolean isResponse() {
        return RADIUS.Code.valueOf(code) != ACCESS_ACCEPT && RADIUS.Code.valueOf(code) != ACCOUNTING_REQUEST;
    }
    
    public boolean isEmpty() {
        return code == EMPTY_CODE;
    }
    
    public Request parseRequest() {
        assert(isRequest());
        Request request = new Request(RADIUS.Code.valueOf(code));
        parseMessage(request);
        return request;
    }
    
    public EmptyMessage parseEmptyMessage() {
        assert(!isRequest() && !isResponse());
        EmptyMessage message = new EmptyMessage();
        parseMessage(message);
        return message;
    }
    
    private void parseMessage(Message message){
        message.setCode(RADIUS.Code.valueOf(code));
        message.setMID(mid);
        message.setLength(length);
        
        message.setAuthenticator(reader.readBytes(AUTHENTICATOR_BITS/8));
        // TODO: ADD attributes and modify authenticator
    }
}