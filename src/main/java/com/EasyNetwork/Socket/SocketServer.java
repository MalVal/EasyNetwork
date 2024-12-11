package com.EasyNetwork.Socket;

import com.EasyNetwork.Exception.SocketException;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer implements SocketServerInterface {

    private final ServerSocket serverSocket;

    public SocketServer(Integer port) {
        try {
            this.serverSocket = new ServerSocket(port);
        }
        catch (Exception e) {
            throw new SocketException(e);
        }
    }

    @Override
    public void setTimout(int milliseconds) throws java.net.SocketException {
        this.serverSocket.setSoTimeout(milliseconds);
    }

    @Override
    public SocketInterface acceptConnection() throws IOException {
        return new ClientSocket(this.serverSocket.accept());
    }
}
