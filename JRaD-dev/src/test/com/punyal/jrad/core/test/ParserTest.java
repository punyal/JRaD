/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.test;

import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.network.serialization.DataParser;
import com.punyal.jrad.core.radius.Request;
import com.punyal.jrad.core.radius.Response;



public class ParserTest {
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
        
//            
//    Code:           02 [Access-Accept]
//    ID:             c1 (193)
//    Length:         0044 (68)
//    Authenticator:  f96c33fb1003dbb6c4a6a064205976bb
//    Attributes============================================================
//    #1
//    Type:           1a (26)[Vendor-Specific]
//    Length:         0c (12)
//    Vendor-Id:      00003a8c (14988) [?]
//    Vendor-Type:    11 (17)
//    Vendor-length:  06 (6)    
//    String:         3b997ffd [?]
//    ----------------------------------------------------------------------
//    #2
//    Type:           1a (26)[Vendor-Specific]
//    Length:         0c (12)
//    Vendor-Id:      0000372a (14122) [?]
//    Vendor-Type:    08 (8)
//    Vendor-length:  06 (6)    
//    String:         0005dc00 [?]
//    ----------------------------------------------------------------------
//    #3
//    Type:           1a (26)[Vendor-Specific]
//    Length:         0c (12)
//    Vendor-Id:      000038df (14543) [?]
//    Vendor-Type:    03 (3)
//    Vendor-length:  06 (6)    
//    String:         3b997ffd [?]
//    ----------------------------------------------------------------------
//    #4
//    Type:           1a (26)[Vendor-Specific]
//    Length:         0c (12)
//    Vendor-Id:      0000372a (14122) [?]
//    Vendor-Type:    07 (7)
//    Vendor-length:  06 (6)    
//    String:         0005dc00 [?]
//    ======================================================================
        DataParser parser = new DataParser(Utils.hexStringToByteArray(realRequest));
        
        Request request;
        try {
            request = parser.parseRequest();
            System.out.print(Utils.messagePrint(request)); 
            
        } catch (IllegalStateException e) {
            //String log = "message format error caused by " + buf.getInetSocketAddress();
        }
     
        
        DataParser parser2 = new DataParser(Utils.hexStringToByteArray(realResponse));
        
        Response response;
        try {
            response = parser2.parseResponse();
            System.out.print(Utils.messagePrint(response)); 
            
        } catch (IllegalStateException e) {
            //String log = "message format error caused by " + buf.getInetSocketAddress();
        }
       
       
        
        //System.out.println(Utils.printAttributesDB());
        System.out.println("# Test (STOP)");
    }
}
    