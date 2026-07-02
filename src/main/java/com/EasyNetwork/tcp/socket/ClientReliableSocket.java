package com.EasyNetwork.tcp.socket;

import com.EasyNetwork.exception.SocketException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientReliableSocket implements ReliableSocketInterface {
    private final Socket cliSock;
    private final OutputStream os;
    private final InputStream is;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public ClientReliableSocket(Socket socket) {
        try {
            this.cliSock = socket;
            this.os = cliSock.getOutputStream();
            this.is = cliSock.getInputStream();
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
            this.os = cliSock.getOutputStream();
            this.is = cliSock.getInputStream();
            this.oos = new ObjectOutputStream(cliSock.getOutputStream());
            this.ois = new ObjectInputStream(cliSock.getInputStream());
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    @Override
    public InputStream getInputStream() {return this.is;}

    @Override
    public OutputStream getOutputStream() {return this.os;}

    @Override
    public ObjectInputStream getObjectInputStream() {
        return this.ois;
    }

    @Override
    public ObjectOutputStream getObjectOutputStream() {
        return this.oos;
    }

    @Override
    public void close() throws IOException {this.cliSock.close();}
}
