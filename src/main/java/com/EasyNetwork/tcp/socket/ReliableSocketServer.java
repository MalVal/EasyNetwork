package com.EasyNetwork.tcp.socket;

import com.EasyNetwork.exception.SocketException;

import java.io.IOException;
import java.net.ServerSocket;

public class ReliableSocketServer implements ReliableSocketServerInterface {
    private final ServerSocket serverSocket;

    public ReliableSocketServer(Integer port) {
        try {
            this.serverSocket = new ServerSocket(port);
        }
        catch (Exception e) {
            throw new SocketException(e);
        }
    }

    @Override
    public void setTimeout(int milliseconds) throws java.net.SocketException {
        this.serverSocket.setSoTimeout(milliseconds);
    }

    @Override
    public ReliableSocketInterface acceptConnection() throws IOException {
        return new ClientReliableSocket(this.serverSocket.accept());
    }

    @Override
    public void close() throws IOException {
        this.serverSocket.close();
    }
}
