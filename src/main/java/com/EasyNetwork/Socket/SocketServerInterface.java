package com.EasyNetwork.Socket;

import java.io.IOException;
import java.net.SocketException;

public interface SocketServerInterface {
    void setTimout(int milliseconds) throws SocketException;
    SocketInterface acceptConnection() throws IOException;
}
