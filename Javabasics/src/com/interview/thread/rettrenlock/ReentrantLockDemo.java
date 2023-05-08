package com.interview.thread.rettrenlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ReentrantLockDemo
 * @Description: ReentrantLock
 * @Author: OnePotSake
 * @Date: 2020/12/3 1:13
 */
public class ReentrantLockDemo {
  Lock lock = new ReentrantLock();

  public void m() {
    lock.lock();
    for(int i = 1; i < 10; i++) {

      try {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(i);
        if(i % 2 == 0) {
          m2();
        }
      } catch(InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }

  public void m2() {
    lock.lock();
    try {
      for(int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("我是m2方法中的内容 " + i);
      }
    } catch(Exception e) {
      e.getMessage();

    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
    new Thread(reentrantLockDemo :: m).start();
  }
}
