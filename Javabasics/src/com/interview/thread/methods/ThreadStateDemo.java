package com.interview.thread.methods;

public class ThreadStateDemo {
  public static void main (String[] args) {
    threadStatusMethodDemo();

  }

  private static void threadStatusMethodDemo () {
    Thread thread = new Thread(()->{
      System.out.println("running");
    });
    Thread.State state = thread.getState();
    System.out.println("线程状态:" + state);
    thread.start();
    Thread.State state1 = thread.getState();
    System.out.println("线程状态:" + state1);
    System.out.println("线程名称" + thread.getName());
  }
}
