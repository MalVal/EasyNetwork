package com.EasyNetwork.Socket;

import com.EasyNetwork.Exception.SocketException;

import javax.net.ssl.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.*;

public class ClientSocketSecure implements SocketInterface {

    private Socket sslSocket;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public ClientSocketSecure(SSLSocket sslSocket) {
        try {
            this.sslSocket = sslSocket;
            this.oos = new ObjectOutputStream(sslSocket.getOutputStream());
            this.ois = new ObjectInputStream(sslSocket.getInputStream());
        }
        catch(Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    public ClientSocketSecure(String host, Integer port, KeyStore keyStore, String ksPassword) throws SocketException {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.3");

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, ksPassword.toCharArray());

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);

            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            SSLSocketFactory sslSocketFactory= sslContext.getSocketFactory();
            this.sslSocket = sslSocketFactory.createSocket(host, port);

            this.oos = new ObjectOutputStream(sslSocket.getOutputStream());
            this.ois = new ObjectInputStream(sslSocket.getInputStream());
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
