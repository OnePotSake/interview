package com.interview.javabasics.io.socket.udp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 19:43
 * @Description: socket 练习
 */
public class SocketServer {
  public static void main(String[] args) {
    int port = 45454;
    String context = "你好骚!";
    try {
      DatagramSocket datagramSocket = new DatagramSocket();
      DatagramPacket packet = new DatagramPacket(context.getBytes("UTF-8"), context.getBytes().length, InetAddress.getLoopbackAddress(), port);
      datagramSocket.send(packet);
      datagramSocket.close();
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
