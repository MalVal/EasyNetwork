package com.EasyNetwork.Socket;

import com.EasyNetwork.Exception.SocketException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket implements SocketInterface {
    private final Socket cliSock;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public ClientSocket(Socket socket) {
        try {
            this.cliSock = socket;
            this.oos = new ObjectOutputStream(cliSock.getOutputStream());
            this.ois = new ObjectInputStream(cliSock.getInputStream());
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    public ClientSocket(String host, Integer port) {
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
}
