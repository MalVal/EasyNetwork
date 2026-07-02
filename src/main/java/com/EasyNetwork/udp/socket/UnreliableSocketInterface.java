package com.EasyNetwork.udp.socket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface UnreliableSocketInterface {
    DatagramSocket getSocket();
    InetAddress getAddress();
    Integer getPort();
    void close() throws IOException;
}
