package com.interview.javabasics.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 21:56
 * @Description: BIO 单线程阻塞
 */
public class QQServer {
  private static List<Socket> socketList = new ArrayList<>();
  private static byte[] bytes = new byte[1024];
  public static void main(String[] args) throws IOException {
    ServerSocket socketSer = new ServerSocket();
    socketSer.bind(new InetSocketAddress(443));
    // 设置阻塞状态xxxx,是否继续
    while (true) {
     /* // 阻塞连接
      Socket socket = socketSer.accept();
      // 阻塞,数据接收,nio 就是解决如何持续接收数据的问题.
      int read = socket.getInputStream().read(bytes);
      System.out.println("success");
      System.out.println(new String(bytes, "UTF-8"));*/

      Socket socket = socketSer.accept();
      if (socket ==null) {
        System.out.println("xxx连接了");
        // 继续新的socket 连接
      } else {
        socketList.add(socket);
        // 更改阻塞状态为等待数据
      }
      for (Socket s:socketList) {
        int read = socket.getInputStream().read(bytes);
        if(read >0 ) {
          System.out.println("连接后有数据");
        } else {
          System.out.println("连接后没有数据");
          continue;
        }
      }

     
     
    }
  }
}
