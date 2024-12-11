package com.EasyNetwork.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface SocketInterface {
    ObjectInputStream getObjectInputStream() throws IOException;
    ObjectOutputStream getObjectOutputStream() throws IOException;
    void close() throws IOException;
}
