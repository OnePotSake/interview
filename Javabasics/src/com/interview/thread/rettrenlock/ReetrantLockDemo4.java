package com.interview.thread.rettrenlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ReetrantLockDemo2
 * lock 公平锁 顺序执行,谁先来,谁先运行,完成后有序(代码顺序) 切换另一个线程运行
 * @Author: OnePotSake
 * @Date: 2020/12/3 2:25
 */
public class ReetrantLockDemo4 {
  Lock lock = new ReentrantLock(true);

  void m() {
    try {
      lock.lock();
      for(int i = 0; i < 4; i++) {
        System.out.println("m 执行");
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void m2() {
    lock.lock();
    try {
      for(int i = 0; i < 4; i++) {
        System.out.println("m2 执行");
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ReetrantLockDemo4 reetrantLockDemo2 = new ReetrantLockDemo4();
    new Thread(() -> {
      reetrantLockDemo2.m2();

    }, "t2").start();
    new Thread(() -> {
      reetrantLockDemo2.m();

    }, "t1").start();



  }


}
