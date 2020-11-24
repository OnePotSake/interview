package com.interview.javabasics.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 22:53
 * @Description: 单线程NIO
 */
public class QQServerNio {
  static ByteBuffer byteBuffer = ByteBuffer.allocate(512);
  public static void main(String[] args) {
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.bind(new InetSocketAddress(443));
      // serverSocketChannel.register();
      // 设置是否阻塞连接socket状态
      serverSocketChannel.configureBlocking(false);
      List<SocketChannel> socketChannelList = new ArrayList<>();
      while (true) {
        System.out.println("start socket conn");
        foreachSocketChannel(socketChannelList);
        //阻塞
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (null == socketChannel) {
          Thread.sleep(500);
          System.out.println("没人连接");
          // 循环socket
        } else {
          System.out.println("socket conn");
          socketChannel.configureBlocking(false);
          socketChannelList.add(socketChannel);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void foreachSocketChannel(List<SocketChannel> socketChannelList) {
    if (socketChannelList != null && socketChannelList.size() > 0) {
      for (int i = 0; i < socketChannelList.size(); i++) {
        SocketChannel socket = socketChannelList.get(i);
        int read = 0;
        try {
          read = socket.read(byteBuffer);
        } catch (IOException e) {
          System.out.println("socket连接数据了");
          System.out.println(e.getMessage());
          e.printStackTrace();
        }
        if (read == -1) {
          try {
            System.out.println("客户端关闭");
            socket.close();
            socketChannelList.remove(i);
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else if (read > 0) {
          byteBuffer.flip();
          System.out.println(new String(byteBuffer.array()));
        }
      }
    }
  }
}
