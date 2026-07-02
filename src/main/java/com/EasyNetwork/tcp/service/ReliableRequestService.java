package com.EasyNetwork.tcp.service;

import com.EasyNetwork.exception.CommunicationException;
import com.EasyNetwork.request.Request;
import com.EasyNetwork.response.Response;
import com.EasyNetwork.tcp.socket.ReliableSocketInterface;

import java.io.IOException;

public class ReliableRequestService {
    private ReliableSocketInterface si;

    public ReliableRequestService(ReliableSocketInterface si) {
        this.si = si;
    }

    public void setSocket(ReliableSocketInterface si) {
        this.si = si;
    }

    public Response sendBytes(Byte[] b) throws CommunicationException {
        try {
            if (si == null || si.getObjectOutputStream() == null || si.getObjectInputStream() == null) {
                throw new CommunicationException("SocketInterface is not properly initialized");
            }
            si.getObjectOutputStream().write(b);
            return (Response) si.getObjectInputStream().readObject();
        }
        catch(ClassNotFoundException e) {
            throw new CommunicationException("Unknown response received", e);
        }
        catch (IOException e) {
            throw new CommunicationException("Communication error: " + e.getMessage(), e);
        }
    }

    public Response sendObject(Request r) throws CommunicationException {
        try {
            if (si == null || si.getObjectOutputStream() == null || si.getObjectInputStream() == null) {
                throw new CommunicationException("SocketInterface is not properly initialized");
            }
            si.getObjectOutputStream().writeObject(r);
            return (Response) si.getObjectInputStream().readObject();
        }
        catch(ClassNotFoundException e) {
            throw new CommunicationException("Unknown response received", e);
        }
        catch (IOException e) {
            throw new CommunicationException("Communication error: " + e.getMessage(), e);
        }
    }
}
