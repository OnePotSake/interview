package com.interview.javabasics.io.socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 19:43
 * @Description: socket 练习
 */
public class SocketServer {
  public static void main(String[] args) {
    //1.定义地址,连接端口
    int port = 4343;
    //发布server 服务
    try {
      // 开启socket
      ServerSocket socketServer = new ServerSocket(port);
      //连接端口阻塞
      Socket accept = socketServer.accept();

     /* OutputStream out = accept.getOutputStream();
      out.write("你好骚!".getBytes());*/

      //阻塞read 读了多少数据
      InputStream inputStream = accept.getInputStream();
      byte[] line = new byte[1024];
      inputStream.read(line);
      System.out.println(new String(line, "UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static void demo() {
    int port = 4343; //端口号
// Socket 服务器端（简单的发送信息）
    Thread sThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          ServerSocket serverSocket = new ServerSocket(port);
          while (true) {
            // 等待连接
            Socket socket = serverSocket.accept();
            Thread sHandlerThread = new Thread(new Runnable() {
              @Override
              public void run() {
                try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                  printWriter.println("hello world！");
                  printWriter.flush();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            });
            sHandlerThread.start();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    sThread.start();
  }
}
