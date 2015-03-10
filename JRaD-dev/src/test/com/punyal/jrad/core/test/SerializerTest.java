/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.test;


import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.network.serialization.*;
import static com.punyal.jrad.core.radius.RADIUS.Type.*;
import com.punyal.jrad.core.radius.Request;
import com.punyal.jrad.core.radius.Response;
import com.punyal.jrad.elements.RawData;

public class SerializerTest {
    public static void main(String[] args) {
        System.out.println("# Test (START)");
        
        String realRequest = "01c1006fbcd3" +
            "afff1bded406f51fc9fdd66c0d8a0113" +
            "30382d65642d62392d30302d62632d35" +
            "350222bb47ce774c5cb030b7b167ead6" +
            "851cd7cdf3db8fd4ab1955d1a4c9ad80" +
            "22947c1e1342422d42422d42422d4242" +
            "2d42422d42421f1341412d41412d4141" +
            "2d41412d41412d4141";
        
//    Code:           01 [Access-Request]
//    ID:             c1 (193)
//    Length:         006f (111)
//    Authenticator:  bcd3afff1bded406f51fc9fdd66c0d8a
//    Attributes============================================================
//    #1
//    Type:           01 [User-Name]
//    Length:         13 (19)
//    String:         30382d65642d62392d30302d62632d3535 [08-ed-b9-00-bc-55]
//    ----------------------------------------------------------------------
//    #2
//    Type:           02 [User-Password]
//    Length:         22 (34)
//    String:         bb47ce774c5cb030b7b167ead6851cd7cd
//                    f3db8fd4ab1955d1a4c9ad8022947c
//    ----------------------------------------------------------------------
//    #3
//    Type:           1e (30)[Called-Station-ID]
//    Length:         13 (19)
//    String:         42422d42422d42422d42422d42422d4242 [BB-BB-BB-BB-BB-BB]
//    ----------------------------------------------------------------------
//    #4
//    Type:           1f (31)[Calling-Station-ID]
//    Length:         13 (19)
//    String:         41412d41412d41412d41412d41412d4141 [AA-AA-AA-AA-AA-AA]
//    ======================================================================
           
            
        String realResponse = "02c10044f96c" +
            "33fb1003dbb6c4a6a064205976bb1a0c" +
            "00003a8c11063b997ffd1a0c0000372a" +
            "08060005dc001a0c000038df03063b99" +
            "7ffd1a0c0000372a07060005dc00";
        
     
        Request test;
        test = Request.newAccess();
        test.setMID(193);
        test.setAuthenticator(Utils.hexStringToByteArray("bcd3afff1bded406f51fc9fdd66c0d8a"));
        test.newAttribute(USER_NAME, Utils.stringToByteArray("08-ed-b9-00-bc-55"));
        test.newAttribute(USER_PASSWORD, Utils.hexStringToByteArray("bb47ce774c5cb030b7b167ead6851cd7cdf3db8fd4ab1955d1a4c9ad8022947c"));
        test.newAttribute(CALLED_STATION_ID, Utils.stringToByteArray("BB-BB-BB-BB-BB-BB"));
        test.newAttribute(CALLING_STATION_ID, Utils.stringToByteArray("AA-AA-AA-AA-AA-AA"));
        
        Response test2;
        test2 = Response.newAccessAccept();
        test2.setMID(193);
        test2.setAuthenticator(Utils.hexStringToByteArray("f96c33fb1003dbb6c4a6a064205976bb"));
        //test2.newAttribute(VENDOR_SPECIFIC, 14988, 17, Utils.hexStringToByteArray("3b997ffd"));
        //test2.newAttribute(VENDOR_SPECIFIC, 14122, 8, Utils.hexStringToByteArray("0005dc00"));
        //test2.newAttribute(VENDOR_SPECIFIC, 14559, 3, Utils.hexStringToByteArray("3b997ffd"));
        //test2.newAttribute(VENDOR_SPECIFIC, 14122, 7, Utils.hexStringToByteArray("0005dc00"));
        
       
        
        System.out.print(Utils.messagePrint(test));
        //System.out.print(Utils.messagePrint(test2));
        
        
        
        Serializer buffer = new Serializer();
        RawData buf = buffer.serialize(test);
        
        
        
        DataParser parser = new DataParser(buf.getBytes());
//        System.out.println("Empty: "+parser.isEmpty());
//        System.out.println("Request: "+parser.isRequest());
//        System.out.println("Response: "+parser.isResponse());
        
        
        if(parser.isRequest()) {
            Request message;
            try {
                message = parser.parseRequest();
                System.out.print(Utils.messagePrint(message)); 

            } catch (IllegalStateException e) {
                //String log = "message format error caused by " + buf.getInetSocketAddress();
            }
        } else {
            Response message;
            try {
                message = parser.parseResponse();
                System.out.print(Utils.messagePrint(message)); 

            } catch (IllegalStateException e) {
                //String log = "message format error caused by " + buf.getInetSocketAddress();
            }
            
        }
        
        
        
        
        
        
        
        System.out.println("# Test (STOP)");
    }
}
    