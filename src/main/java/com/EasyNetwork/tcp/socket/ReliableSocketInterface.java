package com.EasyNetwork.tcp.socket;

import java.io.*;

public interface ReliableSocketInterface {
    InputStream getInputStream();
    OutputStream getOutputStream();
    ObjectInputStream getObjectInputStream() throws IOException;
    ObjectOutputStream getObjectOutputStream() throws IOException;
    void close() throws IOException;
}
