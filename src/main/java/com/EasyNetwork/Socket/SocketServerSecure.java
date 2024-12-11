package com.EasyNetwork.Socket;

import com.EasyNetwork.Exception.SocketException;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;

public class SocketServerSecure implements SocketServerInterface {

    private final ServerSocket serverSocket;

    public SocketServerSecure(Integer port, KeyStore keyStore, String ksPassword) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.3");

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, ksPassword.toCharArray());

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);

            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();

            this.serverSocket = sslServerSocketFactory.createServerSocket(port);
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    @Override
    public void setTimout(int milliseconds) throws java.net.SocketException {
        this.serverSocket.setSoTimeout(milliseconds);
    }

    @Override
    public SocketInterface acceptConnection() throws IOException {
        return new ClientSocketSecure((SSLSocket)this.serverSocket.accept());
    }

    @Override
    public void close() throws IOException {
        this.serverSocket.close();
    }
}
