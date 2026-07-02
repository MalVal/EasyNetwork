package com.EasyNetwork.tcp.socket;

import com.EasyNetwork.exception.SocketException;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.*;

public class ClientReliableSocketSecure implements ReliableSocketInterface {
    private final Socket sslSocket;
    private final OutputStream os;
    private final InputStream is;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public ClientReliableSocketSecure(SSLSocket sslSocket) {
        try {
            this.sslSocket = sslSocket;
            this.os = sslSocket.getOutputStream();
            this.is = sslSocket.getInputStream();
            this.oos = new ObjectOutputStream(sslSocket.getOutputStream());
            this.ois = new ObjectInputStream(sslSocket.getInputStream());
        }
        catch(Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    public ClientReliableSocketSecure(String host, Integer port, KeyStore keyStore, String ksPassword) throws SocketException {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.3");

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, ksPassword.toCharArray());

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);

            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            SSLSocketFactory sslSocketFactory= sslContext.getSocketFactory();
            this.sslSocket = sslSocketFactory.createSocket(host, port);

            this.os = sslSocket.getOutputStream();
            this.is = sslSocket.getInputStream();
            this.oos = new ObjectOutputStream(sslSocket.getOutputStream());
            this.ois = new ObjectInputStream(sslSocket.getInputStream());
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
    public void close() throws IOException {this.sslSocket.close();}
}
