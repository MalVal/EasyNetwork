package com.EasyNetwork.udp.service;

import com.EasyNetwork.exception.SocketException;
import com.EasyNetwork.udp.socket.UnreliableSocketInterface;

import java.io.*;
import java.net.DatagramPacket;

import com.EasyNetwork.helper.SerializationHelper;

public class UnreliableRequestService {
    private UnreliableSocketInterface si;

    public UnreliableRequestService(UnreliableSocketInterface si) {
        this.si = si;
    }

    public void setSocket(UnreliableSocketInterface si) {
        this.si = si;
    }

    public void sendBytes(byte[] data) throws IOException {
        try {
            DatagramPacket packet = new DatagramPacket(
                    data,
                    data.length,
                    this.si.getAddress(),
                    this.si.getDestinationPort()
            );
            this.si.getSocket().send(packet);
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    public void sendObject(Object obj) {
        try {
            byte[] data = SerializationHelper.serialize(obj);
            DatagramPacket packet = new DatagramPacket(
                    data,
                    data.length,
                    this.si.getAddress(),
                    this.si.getDestinationPort()
            );
            this.si.getSocket().send(packet);
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    public byte[] receiveBytes() {
        try {
            byte[] buffer = new byte[65535]; // Max size UDP safe
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            this.si.getSocket().receive(packet);
            return packet.getData();
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }

    public Object receiveObject() {
        try {
            byte[] buffer = new byte[65535]; // Max size UDP safe
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            this.si.getSocket().receive(packet);
            return SerializationHelper.deserialize(packet.getData(), packet.getLength());
        }
        catch (Exception e) {
            throw new SocketException(e.getMessage());
        }
    }
}
