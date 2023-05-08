package com.interview.thread.sync;

public class SynchornizedDemo {
  private int count = 10;
  private Object o = new Object();

  public synchronized void m() { //等同于在方法的代码执行时要synchronized(this)
    count--;
    System.out.println(Thread.currentThread().getName() + " count = " + count);
  }

}
