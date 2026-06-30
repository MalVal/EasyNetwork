package com.EasyNetwork.helper;

import java.io.*;

public class SerializationHelper {
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        return bos.toByteArray();
    }

    public static Object deserialize(byte[] data, int length) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data, 0, length);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}
