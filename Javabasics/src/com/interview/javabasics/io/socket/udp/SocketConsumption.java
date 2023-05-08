package com.interview.javabasics.io.socket.udp;

import sun.nio.ch.DatagramSocketAdaptor;

import java.io.*;
import java.net.*;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 19:43
 * @Description: socket 练习
 */
public class SocketConsumption {
  public static void main(String[] args) {
    DatagramSocket socket = null;
    try {
      socket = new DatagramSocket(45454);
    } catch (SocketException e) {
      e.printStackTrace();
    }

    // 创建接收端Packet, 用来接收数据
    DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

    // 用Socket接收Packet, 未收到数据时会阻塞
    try {
      socket.receive(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 关闭Socket
    socket.close();

    // 从Packet中获取数据
    byte[] data = packet.getData();
    int len = packet.getLength();
    String s = null;
    try {
      s = new String(data, 0, len, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    System.out.println(s);

  }

}
