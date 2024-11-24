package com.EasyNetwork.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket implements SocketInterface {
    private final Socket cliSock;

    public ClientSocket(String host, Integer port) {
        try {
            this.cliSock = new Socket(InetAddress.getByName(host), port);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(cliSock.getInputStream());
    }

    @Override
    public ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(cliSock.getOutputStream());
    }
}
