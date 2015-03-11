package com.punyal.jrad.core.test;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPSimpleServer {

  public static void main(String[] args) throws Exception {
    DatagramChannel channel = DatagramChannel.open();
    DatagramSocket socket = channel.socket();
    SocketAddress address = new InetSocketAddress(9999);
    socket.bind(address);
    ByteBuffer buffer = ByteBuffer.allocateDirect(65507);
    while (true) {
      SocketAddress client = channel.receive(buffer);
      buffer.flip();
      channel.send(buffer, client);
      buffer.clear();
    }
  }
}