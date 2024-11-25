package com.EasyNetwork.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket implements SocketInterface {
    private final Socket cliSock;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;

    public ClientSocket(String host, Integer port) {
        try {
            this.cliSock = new Socket(InetAddress.getByName(host), port);
            this.oos = new ObjectOutputStream(cliSock.getOutputStream());
            this.ois = new ObjectInputStream(cliSock.getInputStream());
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ObjectInputStream getObjectInputStream() throws IOException {
        return this.ois;
    }

    @Override
    public ObjectOutputStream getObjectOutputStream() throws IOException {
        return this.oos;
    }
}
