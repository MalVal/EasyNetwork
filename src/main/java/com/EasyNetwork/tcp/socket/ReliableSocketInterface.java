package com.EasyNetwork.tcp.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ReliableSocketInterface {
    ObjectInputStream getObjectInputStream() throws IOException;
    ObjectOutputStream getObjectOutputStream() throws IOException;
    void close() throws IOException;
}
