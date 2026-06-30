package com.EasyNetwork.tcp.socket;

import com.EasyNetwork.exception.SocketException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientReliableSocket implements ReliableSocketInterface {
    private final Socket cliSock;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public ClientReliableSocket(Socket socket) {
        try {
            this.cliSock = socket;
            this.oos = new ObjectOutputStream(cliSock.getOutputStream());
            this.ois = new ObjectInputStream(cliSock.getInputStream());
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    public ClientReliableSocket(String host, Integer port) {
        try {
            this.cliSock = new Socket(InetAddress.getByName(host), port);
            this.oos = new ObjectOutputStream(cliSock.getOutputStream());
            this.ois = new ObjectInputStream(cliSock.getInputStream());
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    @Override
    public ObjectInputStream getObjectInputStream() {
        return this.ois;
    }

    @Override
    public ObjectOutputStream getObjectOutputStream() {
        return this.oos;
    }

    @Override
    public void close() throws IOException {
        this.cliSock.close();
    }
}
