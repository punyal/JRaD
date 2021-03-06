/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core;

import com.punyal.jrad.core.radius.*;
import static com.punyal.jrad.core.radius.Attribute.Field.STRING;
import static com.punyal.jrad.core.radius.RADIUS.UTF8_CHARSET;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Utils {
    // Prevent initialization
    private Utils() {}
    
    /**
     * Converts the specified byte array to a hexadecimal string.
     * 
     * @param bytes
     * @return 
     */
    public static String toHexString(byte[] bytes) {
        if(bytes == null) return "null";
        StringBuilder sb = new StringBuilder();
        for(byte b:bytes)
            sb.append(String.format("%02x", b & 0xFF));
        return sb.toString();
    }
    
    public static int toInteger(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }
    
    /**
     * Converts the specified byte array to a binary string.
     * 
     * @param bytes
     * @return 
     */
    public static String toBinaryString(byte[] bytes) {
        if(bytes == null) return "null";
        StringBuilder sb = new StringBuilder();
        for(byte b:bytes)
            sb.append(String.format("[%8s]", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        return sb.toString();
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len/2];
        for(int i = 0; i < len; i+=2)
            data[i/2] = (byte) ((Character.digit(s.charAt(i),16) << 4) +
                    Character.digit(s.charAt(i+1), 16));
        return data;
    }
    
    public static byte[] stringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len/2];
        data = s.getBytes(UTF8_CHARSET);
        return data;
    }
    
    
    /**
     * Formats a EmptyMessage into a readable String representation
     * @param m the EmptyMessage
     * @return 
     */
    public static String messagePrint(Message m) {
        ArrayList<AttributesMessage> attributes = m.getAttributes();
        StringBuilder info = new StringBuilder();
        info.append("==================================================\n");
        info.append(String.format("Code: %s\n", m.getCode()));
        info.append(String.format("MID: %s\n", m.getMIDString()));
        info.append(String.format("Length: %d\n", m.getLength()));
        info.append(String.format("Authenticator: %s\n", toHexString(m.getAuthenticator())));
        if(attributes.size() > 0){
            info.append(String.format("--------------- Attributes [%d] ----------------\n", attributes.size()));
            attributes.stream().forEach((attribute) -> {
                switch(attribute.getType()) {
                    case VENDOR_SPECIFIC:
                        info.append(String.format("%-2d %-18s (%d) %-2d %s\n",
                                attribute.getTypeValue(),
                                attribute.getType(),
                                attribute.getVendorID(),
                                attribute.getVendorType(),
                                Utils.toHexString(attribute.getVendorValue())));
                        break;
                    case CHAP_PASSWORD:
                        info.append(String.format("%-2d %-18s (%d) %s\n",
                                attribute.getTypeValue(),
                                attribute.getType(),
                                attribute.getChapIdent(),
                                attribute.getValueString()));
                        break;
                    default:
                        if(attribute.getFieldType().equals(STRING))
                            info.append(String.format("%-2d %-25s %s\n", attribute.getTypeValue(), attribute.getType(), attribute.getValueString()));
                        else
                            info.append(String.format("%-2d %-25s %s\n", attribute.getTypeValue(), attribute.getType(), Utils.toHexString(attribute.getValue())));
                        break;
                }
            });
        }
        info.append("==================================================\n");
        return info.toString();
    }
    
    public static String printAttributesDB() {
        StringBuilder info = new StringBuilder();
        info.append("~~~~~~~~~~~~~~~~~~~~~~~~ RFC 2865 Supported Attributes ~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        info.append(String.format(" %-2s %-22s %s %s %s %s %s %s %s\n",
                    "#",
                    "Attribute",
                    "MinLen",
                    "FieldType",
                    "Request",
                    "Acept",
                    "Reject",
                    "Challenge",
                    "Unique"
                    ));
        for (AttributesRADIUS attributesDB : RADIUS.attributesDB) {
            info.append(String.format(" %-2d %-25s %-4s %-9s %-6s %-6s %-7s %-8s %s\n",
                    attributesDB.getTypeValue(),
                    attributesDB.getType(),
                    attributesDB.getMinLength(),
                    attributesDB.getFieldType(),
                    attributesDB.isRequest(),
                    attributesDB.isAccept(),
                    attributesDB.isReject(),
                    attributesDB.isChallenge(),
                    attributesDB.isUnique()
                    ));
        }
        info.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return info.toString();
    }
}