/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core;

import com.punyal.jrad.core.radius.*;

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
    
    /**
     * Formats a EmptyMessage into a readable String representation
     * @param m the EmptyMessage
     * @return 
     */
    public static String messagePrint(EmptyMessage m) {
        StringBuilder info = new StringBuilder();
        String string_t;
        int int_t;
        info.append("==[RADIUS EmptyMessage]===========================\n");
        info.append(String.format("Code: %s\n", m.getCode()));
        info.append(String.format("MID: %s\n", m.getMIDString()));
        info.append(String.format("Length: %d\n", m.getLength()));
        info.append(String.format("Authenticator: %s\n", toHexString(m.getAuthenticator())));
        info.append("==================================================\n");
        return info.toString();
    }
    
    /**
     * Formats a EmptyMessage into a readable String representation
     * @param m the EmptyMessage
     * @return 
     */
    public static String messagePrint(Request m) {
        StringBuilder info = new StringBuilder();
        String string_t;
        int int_t;
        info.append("==[RADIUS Request]================================\n");
        info.append(String.format("Code: %s\n", m.getCode()));
        info.append(String.format("MID: %s\n", m.getMIDString()));
        info.append(String.format("Length: %d\n", m.getLength()));
        info.append(String.format("Authenticator: %s\n", toHexString(m.getAuthenticator())));
        info.append("==================================================\n");
        return info.toString();
    }
}