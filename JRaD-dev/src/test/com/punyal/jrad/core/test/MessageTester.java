/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.test;

import com.punyal.jrad.*;
import com.punyal.jrad.core.*;
import com.punyal.jrad.core.network.serialization.DataParser;
import com.punyal.jrad.core.network.serialization.Serializer;
import com.punyal.jrad.core.radius.*;
import com.punyal.jrad.elements.RawData;


public class MessageTester{
    
    public static void main(String[] args){
        System.out.println("# Test (START)");
        
        
        
        
        Request test = Request.newAccounting();
        test.setAuthenticator("es una prueba!!!");
        System.out.print(Utils.messagePrint(test)); 
        
        Serializer buffer = new Serializer();
        RawData buf = buffer.serialize(test);
        
        
        DataParser parser = new DataParser(buf.getBytes());
        
        System.out.println("Empty: "+parser.isEmpty());
        System.out.println("Request: "+parser.isRequest());
        System.out.println("Response: "+parser.isResponse());
        
        Request request;
        try {
            request = parser.parseRequest();
            System.out.print(Utils.messagePrint(request)); 
            
        } catch (IllegalStateException e) {
            String log = "message format error caused by " + buf.getInetSocketAddress();
        }
        
        System.out.println("# Test (END)");
    }
    
}