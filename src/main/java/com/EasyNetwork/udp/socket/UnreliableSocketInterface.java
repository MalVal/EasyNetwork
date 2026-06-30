package com.EasyNetwork.udp.socket;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface UnreliableSocketInterface {
    public DatagramSocket getSocket();
    public InetAddress getAddress();
    public Integer getPort();
    void close() throws IOException;
}
