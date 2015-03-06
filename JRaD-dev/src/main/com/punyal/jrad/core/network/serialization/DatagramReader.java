/**
 * JRaD 2015
 * @author Pablo Puñal Pereira <pablo@punyal.com>
 * @version 0.1
 */

package com.punyal.jrad.core.network.serialization;

import java.io.ByteArrayInputStream;


public class DatagramReader {
    
    // Attributes /////////////
    private ByteArrayInputStream byteStream;
    private byte currentByte;
    private int currentBitIndex;
    
    // Constructors ///////////
    
    public DatagramReader(byte[] byteArray) {
        // initialize underlying byte stream
        byteStream = new ByteArrayInputStream(byteArray);
        // initialize bit buffer
        currentByte = 0;
        currentBitIndex = -1; // indicates that no byte read yet
    }
    
    // Methods ////////////////
    /**
     * Reads a sequence of bits from the stream
     * 
     * @param numBits the number of bits to read
     * @return a long containing the bits read
     */
    public long readLong(int numBits) {
        long bits = 0; // all bits to zero
        for(int i = numBits -1; i >= 0; i--){
            // checks whether new byte needs to be read
            if(currentBitIndex < 0)
                readCurrentByte();
            // test current bit
            boolean bit = (currentByte >> currentBitIndex & 1) != 0;
            if(bit)
                bits |= (1L << i); // set bit at i-th position
            //decrease current bit index
            --currentBitIndex;
        }
        return bits;
    }
    
    /**
     * Reads a sequence of bits from the stream
     * 
     * @param numBits the number of bits to read
     * @return a int containing the bits read
     */
    public int read(int numBits) {
        int bits = 0; // all bits to zero
        for(int i = numBits -1; i >= 0; i--){
            // checks whether new byte needs to be read
            if(currentBitIndex < 0)
                readCurrentByte();
            // test current bit
            boolean bit = (currentByte >> currentBitIndex & 1) != 0;
            if(bit)
                bits |= (1 << i); // set bit at i-th position
            //decrease current bit index
            --currentBitIndex;
        }
        return bits;
    }
    
    /**
     * Reads a sequence of bytes from the stream
     * 
     * @param count the number of bytes to read
     * @return the sequence of bytes read from the stream
     */
    public byte[] readBytes(int count) {
        // for negative count values, read all bytes left
        if(count < 0)
            count = byteStream.available();
        // allocate byte array
        byte[] bytes = new byte[count];
        
        // are there bit left to read in buffer?
        if(currentBitIndex >= 0)
            for(int i = 0; i < count; i++)
                bytes[i] = (byte) read(Byte.SIZE);
        else byteStream.read(bytes, 0, bytes.length);
        return bytes;
    }
    
    /**
     * Reads the next byte from the stream.
     * 
     * @return the next byte
     */
    public byte readNextByte() {
        byte[] bytes = readBytes(1);
        return bytes[0];
    }
    
    /**
     * Reads the complete sequence of bytes left in the stream.
     * 
     * @return the sequence of bytes left in the stream
     */
    public byte[] readBytesLeft() {
        return readBytes(-1);
    }
    
    /**
     * Returns if there are more bytes
     * @return 
     */
    public boolean bytesAvailable() {
        return byteStream.available() > 0;
    }
    
    // Utilities ///////////////
    
    private void readCurrentByte() {
        // try to read from byte stream
        int val = byteStream.read();
        if(val >= 0)
            currentByte = (byte) val; // byte successfully read
        else
            currentByte = 0; // end of stream reached.
        // reset current bit index
        currentBitIndex = Byte.SIZE - 1;
    }
    
}
