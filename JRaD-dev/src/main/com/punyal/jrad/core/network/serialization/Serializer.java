/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.core.radius.Request;
import com.punyal.jrad.elements.RawData;

public class Serializer {
    
    /**
     * Serializes the specified request.
     * 
     * @param request the request
     * @return the request as raw data
     */
   /* public RawData serialize(Request request) {
        byte[] bytes = request.getBytes();
        if (bytes == null)
            bytes = new DataSerializer().serializeRequest(request);
        request.setBytes(bytes);
        return new RawData(bytes, request.getDestination(), request.getDestinationPort());
    }
    
    public RawData serialize(Response response) {
        byte[] bytes = response.getBytes();
        if (bytes == null)
            bytes = new DataSerializer().serializeResponse(response);
        response.setBytes(bytes);
        return new RawData(bytes, response.getDestination(), response.getDestinationPort());
    }
    
    
    public RawData serialize(EmptyMessage message) {
        byte[] bytes = message.getBytes();
        if (bytes == null)
            bytes = new DataSerializer().serializeEmptyMessage(message);
        message.setBytes(bytes);
        return new RawData(bytes, message.getDestination(), message.getDestinationPort());
    }*/
    
    public RawData serialize(Message message) {
        byte[] bytes = message.getBytes();
        if (bytes == null)
            bytes = new DataSerializer().serializeMessage(message);
        message.setBytes(bytes);
        return new RawData(bytes, message.getDestination(), message.getDestinationPort());
    }
    
}