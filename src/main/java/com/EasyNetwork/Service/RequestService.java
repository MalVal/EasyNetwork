package com.EasyNetwork.Service;

import com.EasyNetwork.Exception.CommunicationException;
import com.EasyNetwork.Request.Request;
import com.EasyNetwork.Response.Response;
import com.EasyNetwork.Socket.SocketInterface;

import java.io.IOException;

public class RequestService {
    private SocketInterface si;

    public RequestService(SocketInterface si) {
        this.si = si;
    }

    public void setSocket(SocketInterface si) {
        this.si = si;
    }

    Response sendObject(Request r) throws CommunicationException {
        try {
            si.getObjectOutputStream().writeObject(r);
            return (Response) si.getObjectInputStream().readObject();
        }
        catch (IOException e) {
            throw new CommunicationException("Communication error", e);
        }
        catch(ClassNotFoundException e) {
            throw new CommunicationException("Unknown response received", e);
        }
    }
}
