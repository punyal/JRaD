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
        info.append(String.format("Authenticator: %s\n", m.getAuthenticatorString()));
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
        info.append(String.format("Authenticator: %s\n", m.getAuthenticatorString()));
        info.append("==================================================\n");
        return info.toString();
    }
}