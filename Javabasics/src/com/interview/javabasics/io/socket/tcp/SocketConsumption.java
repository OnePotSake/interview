package com.interview.javabasics.io.socket.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 19:43
 * @Description: socket 练习
 */
public class SocketConsumption {
  public static void main(String[] args) {
    int port = 4343; //端口号
    try {
      Socket socket = new Socket(InetAddress.getLocalHost(),port);
      socket.getOutputStream().write("你好骚".getBytes());

    /*  InputStream inputStream = socket.getInputStream();
      byte[] line = new byte[1024];
      inputStream.read(line);
      System.out.println(new String(line, "UTF-8"));*/

    /*  OutputStream outputStream = socket.getOutputStream();
      outputStream.write("你好骚!".getBytes());*/
    } catch (IOException e) {
      e.printStackTrace();
    }
    // demo(port);
  }

  private static void demo(int port) {
    try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
      bufferedReader.lines().forEach(s -> System.out.println("客户端：" + s));
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
