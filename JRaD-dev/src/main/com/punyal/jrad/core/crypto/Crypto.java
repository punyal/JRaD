/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.crypto;

import com.punyal.jrad.core.Utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Crypto {
    
    private byte[] secretKey;
    private byte[] authenticator;
    private byte[] decrypted;
    private byte[] crypted;
    
    /**
     * Empty Constructor
     */
    public void Crypto(){}
    
    /**
     * Encrypt the decrypted data
     * @param secretKey
     * @param authenticator
     * @param decrypted
     * @return 
     */
    public byte[] encrypt(byte[] secretKey, byte[] authenticator, byte[] decrypted) throws NoSuchAlgorithmException{
        this.secretKey = secretKey;
        this.authenticator = authenticator;
        this.decrypted = decrypted;
        this.encryption();
        return crypted;
    }
    
    /**
     * Encrypt the decrypted data
     * @param secretKey
     * @param authenticator
     * @param decrypted
     * @return 
     */
    public byte[] encrypt(String secretKey, byte[] authenticator, String decrypted) throws NoSuchAlgorithmException{
        this.secretKey = Utils.stringToByteArray(secretKey);
        this.authenticator = authenticator;
        this.decrypted = Utils.stringToByteArray(decrypted);
        this.encryption();
        return crypted;
    }
    
    /**
     * Encryption method
     */
    private void encryption() throws NoSuchAlgorithmException {
        // Check critical lengths
        if(authenticator.length != 16)
            throw new IllegalArgumentException("Authenticator with wrong length: "+authenticator.length);
        
        int len = 0;
        int tot_len = 0;
        // Check final length to prevent errors
        if(decrypted.length%16 != 0) tot_len = 16;
        tot_len += ((int)decrypted.length/16)*16; 
        
        // Create crypted array
        crypted = new byte[tot_len];
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b_temp = new byte[secretKey.length+authenticator.length];
        byte[] c_temp = new byte[16];
        System.arraycopy(secretKey, 0, b_temp, 0, secretKey.length);
        System.arraycopy(authenticator, 0, b_temp, secretKey.length, authenticator.length);
        b_temp = md.digest(b_temp);
        
        while(len < tot_len) {
            // Copy the 16th bytes to XOR
            if((decrypted.length - len) < 16) {
                System.arraycopy(decrypted, len, c_temp, 0, decrypted.length-len);
                for(int i=decrypted.length-len; i<16; i ++) 
                    c_temp[i] = 0;
            } else System.arraycopy(decrypted, len, c_temp, 0, 16);
            
            for(int i=0; i<16; i++)
                c_temp[i] = (byte)(0xFF & ((int)c_temp[i]) ^((int)b_temp[i]));
            
            System.arraycopy(c_temp, 0, crypted, len, 16);
            len += 16;
        }
    }
}