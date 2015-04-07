/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.elements.RawData;

public class Serializer {
    
    /**
     * Serializes the specified request.
     * 
     * @param request the request
     * @return the request as raw data
     */
    
    public RawData serialize(Message message) {
        byte[] bytes = message.getBytes();
        if (bytes == null)
            bytes = new DataSerializer().serializeMessage(message);
        message.setBytes(bytes);
        return new RawData(bytes, message.getDestination(), message.getDestinationPort());
    }
    
}