/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import com.punyal.jrad.core.radius.EmptyMessage;
import com.punyal.jrad.core.radius.Request;
import com.punyal.jrad.elements.RawData;

public class Serializer {
    
    /**
     * Serializes the specified request.
     * 
     * @param request the request
     * @return the request as raw data
     */
    public RawData serialize(Request request) {
        byte[] bytes = request.getBytes();
        if (bytes == null)
            bytes = new DataSerializer().serializeRequest(request);
        request.setBytes(bytes);
        return new RawData(bytes, request.getDestination(), request.getDestinationPort());
    }
    
    
    public RawData serialize(EmptyMessage message) {
        byte[] bytes = message.getBytes();
        if (bytes == null)
            bytes = new DataSerializer().serializeEmptyMessage(message);
        message.setBytes(bytes);
        return new RawData(bytes, message.getDestination(), message.getDestinationPort());
    }
    
}