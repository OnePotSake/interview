package com.interview.thread.rettrenlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: ReetrantLockDemo2
 * @Description: interrupted
 * lock 与 lockInterruptibly比较区别在于：
 * lock 优先考虑获取锁，待获取锁成功后，才响应中断。
 * lockInterruptibly 优先考虑响应中断，而不是响应锁的普通获取或重入获取。
 * <p>
 * 详细区别：
 * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来
 * 中断等待线程的等待而直接返回，这时不用获取锁，而会抛出一个InterruptedException。
 * ReentrantLock.lock方法不允许Thread.interrupt中断,即使检测到Thread.isInterrupted,
 * 一样会继续尝试获取锁，失败则继续休眠。只是在最后获取锁成功后再把当前线程置为interrupted状态,然后再中断线程。
 *
 * @Author: OnePotSake
 * @Date: 2020/12/3 2:25
 */
public class ReetrantLockDemo3 {
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

    try {
      lock.lockInterruptibly();
      System.out.println("m2 方法获取锁后执行过..");
      TimeUnit.SECONDS.sleep(2);
    } catch(InterruptedException e) {
      System.out.println("m2 interrupted 执行!");
   //   e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ReetrantLockDemo3 reetrantLockDemo2 = new ReetrantLockDemo3();
    new Thread(() -> {
      reetrantLockDemo2.m();

    }, "t1").start();
    Thread t2 = new Thread(reetrantLockDemo2 :: m2);
    t2.start();
    t2.interrupt();

  }


}
