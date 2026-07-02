package com.EasyNetwork.udp.socket;

import com.EasyNetwork.exception.SocketException;

import java.net.*;

public class UnreliableSocket implements UnreliableSocketInterface {
    private final DatagramSocket socket;
    private final InetAddress address;
    private final int port;

    public UnreliableSocket(String host, Integer port) {
        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName(host);
            this.port = port;
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    @Override
    public DatagramSocket getSocket() {
        return this.socket;
    }

    @Override
    public InetAddress getAddress() {
        return this.address;
    }

    @Override
    public Integer getPort() {
        return this.port;
    }

    @Override
    public void close() {
        socket.close();
    }
}