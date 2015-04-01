package com.punyal.jrad.core.test;

import com.punyal.jrad.core.Utils;
import com.punyal.jrad.core.crypto.Crypto;
import java.security.*;

public class EncryptionTest {
    public EncryptionTest(byte[] authenticator, byte[] toEncrypt) {
        
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        //EncryptionTest crypto = new EncryptionTest();
        String auth = "50b69e7035ebdc295a3cfb6566a397f3";
        byte[] bytes = Utils.hexStringToByteArray(auth);
        System.out.println("Authorizator: "+Utils.toHexString(bytes).toUpperCase());
        
        String secKey = "testing123";
        bytes = Utils.stringToByteArray(secKey);
        System.out.println("Secret Key: "+Utils.toHexString(bytes).toUpperCase());
        
        String originalPass = "click_to_connect";
        bytes = Utils.stringToByteArray(originalPass);
        System.out.println("Original Pass: "+Utils.toHexString(bytes).toUpperCase());
        
        String crypto = "0c0de8cd7afe016fb24738d7a91e738e";
        bytes = Utils.hexStringToByteArray(crypto);
        System.out.println("Crypto Pass: "+Utils.toHexString(bytes).toUpperCase());
        
        
        //MessageDigest md = new MessageDigest.getInstance("MD5");
        MessageDigest md = MessageDigest.getInstance("MD5");
        
        byte[] concat = new byte[Utils.stringToByteArray(secKey).length + Utils.hexStringToByteArray(auth).length];
        System.arraycopy(Utils.stringToByteArray(secKey), 0, concat, 0, Utils.stringToByteArray(secKey).length);
        System.arraycopy(Utils.hexStringToByteArray(auth), 0, concat, Utils.stringToByteArray(secKey).length, Utils.hexStringToByteArray(auth).length);
        
        bytes = md.digest(concat);
        
        System.out.println("b1_2: "+Utils.toHexString(bytes).toUpperCase());
        
        
        for(int i=0; i<bytes.length; i++)
            bytes[i] = (byte)(0xFF & ((int)Utils.stringToByteArray(originalPass)[i]) ^((int)bytes[i]));
        
        
        System.out.println("C: "+Utils.toHexString(bytes).toUpperCase());
        
        
        // 0c0de8cd7afe016fb24738d7a91e738e
        
        Crypto crypt = new Crypto();
        System.out.println("T: "+Utils.toHexString(crypt.encrypt(secKey, Utils.hexStringToByteArray(auth), originalPass)).toUpperCase());
        
    }
    
    
}