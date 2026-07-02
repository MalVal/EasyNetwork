package com.EasyNetwork.udp.socket;

import com.EasyNetwork.exception.SocketException;

import java.net.*;

public class UnreliableSocket implements UnreliableSocketInterface {
    private final DatagramSocket socket;
    private final InetAddress address;
    private final int dPort;

    public UnreliableSocket(String host, Integer sPort, Integer dPort) {
        try {
            this.socket = new DatagramSocket(sPort);
            this.address = InetAddress.getByName(host);
            this.dPort = dPort;
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
    public Integer getDestinationPort() {
        return this.dPort;
    }

    @Override
    public void close() {
        socket.close();
    }
}