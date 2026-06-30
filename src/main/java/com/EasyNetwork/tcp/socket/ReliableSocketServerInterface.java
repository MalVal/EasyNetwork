package com.EasyNetwork.tcp.socket;

import java.io.IOException;
import java.net.SocketException;

public interface ReliableSocketServerInterface {
    void setTimeout(int milliseconds) throws SocketException;
    ReliableSocketInterface acceptConnection() throws IOException;
    void close() throws IOException;
}
