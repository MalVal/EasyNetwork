package com.EasyNetwork.udp.service;

import com.EasyNetwork.exception.SocketException;
import com.EasyNetwork.udp.socket.UnreliableSocketInterface;

import java.io.*;
import java.net.DatagramPacket;
import java.util.Arrays;

import com.EasyNetwork.helper.SerializationHelper;

public class UnreliableRequestService {
    private final UnreliableSocketInterface si;

    public UnreliableRequestService(UnreliableSocketInterface si) {
        this.si = si;
    }

    public void sendBytes(byte[] data) {
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

    public byte[] receiveBytes(int size) {
        try {
            byte[] buffer = new byte[size];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            this.si.getSocket().receive(packet);
            return Arrays.copyOf(packet.getData(), packet.getLength());
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
