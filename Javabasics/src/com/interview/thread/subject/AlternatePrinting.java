package com.interview.thread.subject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @program: AlternatePrinting
 * @Description: 交替打印
 * @Author: OnePotSake
 * @Date: 2020/12/3 1:44
 */
public class AlternatePrinting {
  public static void main (String[] args) throws InterruptedException {
   // 交替打印,启动一个线程
   /* Method1 method1 = new Method1();
    new Thread(method1 :: m).start();*/

  }
}

class Method1 {
  Lock lock = new ReentrantLock();
  volatile AtomicInteger count = new AtomicInteger(0);
  void m () {
    String[] letter = new String[]{"A", "B", "C", "D", "E", "F", "G"};
    lock.lock();
    try {
      TimeUnit.SECONDS.sleep(1);
      for (int i = 0; i < letter.length; i++) {
        if (count.get() > letter.length - 1) {
          break;
        }
        System.out.println(letter[count.get()]);

        m2();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void m2 () {
    int[] number = new int[]{1, 2, 3, 4, 5, 6, 7};
    lock.lock();
    try {
      TimeUnit.SECONDS.sleep(1);
      for (int i = 0; i < number.length; i++) {
        if (count.get() > number.length - 1) {
          break;
        }
        System.out.println(number[count.get()]);
        count.getAndIncrement();
        m();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

class Method2 {

}


