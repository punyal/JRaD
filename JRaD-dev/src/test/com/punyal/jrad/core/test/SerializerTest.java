
package com.punyal.jrad.core.test;

import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.radius.Message;
import com.punyal.jrad.core.radius.RADIUS;
import static com.punyal.jrad.core.radius.RADIUS.Type.*;
import java.security.NoSuchAlgorithmException;

public class SerializerTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("# Test (START)");
        
        String realRequest = "01c1006fbcd3" +
            "afff1bded406f51fc9fdd66c0d8a0113" +
            "30382d65642d62392d30302d62632d35" +
            "350222bb47ce774c5cb030b7b167ead6" +
            "851cd7cdf3db8fd4ab1955d1a4c9ad80" +
            "22947c1e1342422d42422d42422d4242" +
            "2d42422d42421f1341412d41412d4141" +
            "2d41412d41412d4141";
        
            
        String realResponse = "02c10044f96c" +
            "33fb1003dbb6c4a6a064205976bb1a0c" +
            "00003a8c11063b997ffd1a0c0000372a" +
            "08060005dc001a0c000038df03063b99" +
            "7ffd1a0c0000372a07060005dc00";
        
     
        Message test;
        test = new Message();
        test.setSecretKey("RADIUStest");
        test.setCode(RADIUS.Code.ACCESS_REQUEST);
        test.setMID(193);
        test.setAuthenticator(Utils.hexStringToByteArray("bcd3afff1bded406f51fc9fdd66c0d8a"));
        test.newAttribute(USER_NAME, Utils.stringToByteArray("08-ed-b9-00-bc-55"));
        test.newAttribute(USER_PASSWORD, Utils.hexStringToByteArray("bb47ce774c5cb030b7b167ead6851cd7cdf3db8fd4ab1955d1a4c9ad8022947c"));
        test.newAttribute(CALLED_STATION_ID, Utils.stringToByteArray("BB-BB-BB-BB-BB-BB"));
        test.newAttribute(CALLING_STATION_ID, Utils.stringToByteArray("AA-AA-AA-AA-AA-AA"));
        test.newAttribute(FILTER_ID, Utils.stringToByteArray("human"));
        test.print();
        test.serialize();
        System.out.println("Serialization: "+Utils.toHexString(test.getBytes()));
        test.parse();
        test.print();
        
        /*
        Message test2;
        test2 = new Message();
        test2.setSecretKey("RADIUStest");
        test2.setCode(RADIUS.Code.ACCESS_ACCEPT);
        test2.setMID(193);
        test2.setAuthenticator(Utils.hexStringToByteArray("f96c33fb1003dbb6c4a6a064205976bb"));
        test2.newAttribute(VENDOR_SPECIFIC, 14988, 17, Utils.hexStringToByteArray("3b997ffd"));
        test2.newAttribute(VENDOR_SPECIFIC, 14122, 8, Utils.hexStringToByteArray("0005dc00"));
        test2.newAttribute(VENDOR_SPECIFIC, 14559, 3, Utils.hexStringToByteArray("3b997ffd"));
        test2.newAttribute(VENDOR_SPECIFIC, 14122, 7, Utils.hexStringToByteArray("0005dc00"));
        test2.print();
        test2.serialize();
        System.out.println("Serialization: "+Utils.toHexString(test2.getBytes()));
        test2.parse();
        test2.print();
        */
        
        System.out.println(test.getAttributeByType(FILTER_ID).getValueString());
        
        System.out.println("# Test (STOP)");
    }
}
    