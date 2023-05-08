package com.interview.javabasics.nio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: Darks
 * @CreateTime: 2020-03-18 22:01
 * @Description:
 */
public class Client {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("127.0.0.1", 443);
    Scanner scanner = new Scanner(System.in);
    String context = null;
    while(!"-1".equalsIgnoreCase(context)) {
      System.out.println("请输入发送数据!:");
      context = scanner.next();
      socket.getOutputStream().write(context.getBytes());
    }
  }
}
