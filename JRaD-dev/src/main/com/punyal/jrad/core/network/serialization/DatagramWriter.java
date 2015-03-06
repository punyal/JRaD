/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import java.io.ByteArrayOutputStream;


public class DatagramWriter {
    
    // Attributes ///////////////////
    private ByteArrayOutputStream byteStream;
    private byte currentByte;
    private int currentBitIndex;
    
    // Constructor //////////////////
    
    /**
     * Initialization
     */
    public DatagramWriter() {
        // initialize underlying byte stream
        byteStream = new ByteArrayOutputStream();
        
        // initialize bit buffer
        currentByte = 0;
        currentBitIndex = Byte.SIZE -1;
    }
    
    // Methods //////////////////////
    
    /**
     * Writes a sequence of bits to the stream
     * 
     * @param data long data
     * @param numBits number of bits
     */
    public void writeLong(long data, int numBits) {
        if(numBits < 32 && data >= (1 << numBits))
            throw new RuntimeException(String.format("Truncating value %d to %d-bit integer\n", data, numBits));
        for(int i = numBits -1; i >= 0; i--){
            // test bit
            boolean bit = (data >> i & 1) != 0;
            if(bit) currentByte |= (1 << currentBitIndex);
            
            // decrease current bit index
            --currentBitIndex;
            
            // check if current byte can be written
            if(currentBitIndex < 0)
                writeCurrentByte();
        }
    }
    
    /**
     * Writes a sequence of bits to the stream
     * 
     * @param data int data
     * @param numBits number of bits
     */
    public void write(int data, int numBits) {
        if(numBits < 32 && data >= (1 << numBits))
            throw new RuntimeException(String.format("Truncating value %d to %d-bit integer\n", data, numBits));
        for(int i = numBits -1; i >= 0; i--) {
            // test bit
            boolean bit = (data >> i & 1) != 0;
            if(bit) currentByte |= (1 << currentBitIndex);
            
            // decrease current bit index
            --currentBitIndex;
            
            // check if current byte can be written
            if(currentBitIndex < 0)
                writeCurrentByte();
        }
    }
    
    /**
     * Writes a sequence of bytes to the stream
     * 
     * @param bytes the sequence of bytes to write
     */
    public void writeBytes(byte[] bytes) {
        
        // check if anything to do at all
        if(bytes == null)
            return;
        // are there bits left to write in buffer?
        if(currentBitIndex < Byte.SIZE -1) {
            for(int i = 0; i < bytes.length; i++)
                write(bytes[i], Byte.SIZE);
        } else {
            // if bit buffer is empty, call can be delegated
            // to byte stream to increase
            byteStream.write(bytes, 0, bytes.length);
        }
    }
    
    /**
     * Writes one byte to the streamm
     * 
     * @param b 
     */
    public void writeByte(byte b) {
        writeBytes(new byte[] { b });
    }
    
    // Functions ////////////////
    
    /**
     * Returns a byte array containing the sequence of bits written
     * 
     * @return the byte array containing the written bits
     */
    public byte[] toByteArray(){
        
        // write any bits left in the buffer to the stream
        writeCurrentByte();
        
        // retrieve the byte array from the stream
        byte[] byteArray = byteStream.toByteArray();
        
        // reset stream for the sake of consistency
        byteStream.reset();
        
        // return the byte array
        return byteArray;
    }
    
    // Utilities ///////////////
    
    /**
     * Writes pending bits to the stream
     */
    private void writeCurrentByte() {
        if(currentBitIndex < Byte.SIZE - 1) {
            byteStream.write(currentByte);
            currentByte = 0;
            currentBitIndex = Byte.SIZE - 1;
        }
    }
    
    @Override
    public String toString() {
        byte[] byteArray = byteStream.toByteArray();
        if(byteArray != null && byteArray.length !=0) {
            StringBuilder builder = new StringBuilder(byteArray.length * 3);
            for(int i = 0; i < byteArray.length; i++){
                builder.append(String.format("%02X", 0xFF & byteArray[i]));
                if(i<byteArray.length - 1)
                    builder.append(' ');
            }
            return builder.toString();
        } else return "--";
    }
}