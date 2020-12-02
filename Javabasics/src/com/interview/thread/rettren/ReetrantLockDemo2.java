package com.interview.thread.rettren;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  @program: ReetrantLockDemo2
 *  @Description:
 * trylock 使用
 * @Author: OnePotSake
 * @Date: 2020/12/3 2:25
 */
public class ReetrantLockDemo2 {
  Lock lock = new ReentrantLock();
  void m() {
    lock.lock();
    try {
      System.out.println("M 方法锁定");
      TimeUnit.SECONDS.sleep(100);
    } catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void m2() {
    boolean b = false;

    try {
      b = lock.tryLock(3L, TimeUnit.SECONDS);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }

    if(!b) {
      System.out.println("m方法 锁定,m2 无法获取lock锁,等待m释放.....");
      try {
        for(int i = 1; i < 4 ; i++) {
          TimeUnit.SECONDS.sleep(1);
          System.out.println("等待" + i + "秒了");
        }

      } catch(InterruptedException e) {
        e.printStackTrace();
      }
      // 强制开启,结束m方法所占用的锁
      System.out.println("结束原有锁占用成功,开始进行m2 业务");
      lock.unlock();
    }
    lock.lock();
    try {
      TimeUnit.SECONDS.sleep(1);
      System.out.println("m2 方法获取锁后执行过..");
    } catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ReetrantLockDemo2 reetrantLockDemo2 = new ReetrantLockDemo2();
    new Thread(()->{
      reetrantLockDemo2.m();

    }, "t1").start();
    new Thread(()->{
      reetrantLockDemo2.m2();

    }, "t2").start();
  }


}
