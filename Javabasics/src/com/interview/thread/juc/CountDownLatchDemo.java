package com.interview.thread.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述: <br>
 *
 * @program: countdownlock
 * @Description: join 方法与countdown 方式比较
 * join 控制粒度不是很细,且需要阻塞等待, 而countdown 仅检查是否处于0状态便可以继续执行
 * @Author: OnePotSake
 * @Date: 2020/12/6 17:50
 */
public class CountDownLatchDemo {
  public static void main(String[] args) {
    countDownLatchMethod();
    join();
  }

  private static void join() {
    long l = System.currentTimeMillis();
    Thread[] threads = new Thread[100];
    AtomicInteger count = new AtomicInteger(0);

    for(int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
        // 我们是
        int result = 0;
        for(int j = 0; j < 10000; j++) {
          result += j;
          count.getAndIncrement();
        }
      });
    }

    for(int i = 0; i < threads.length; i++) {
      try {
        threads[i].start();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }

    for(int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("join 方式 商品已经完成, 花费时间: " + (System.currentTimeMillis() - l));
    System.out.println("count" + count);

  }


  private static void countDownLatchMethod() {
    Thread[] threads = new Thread[100];
    CountDownLatch countDownLatch = new CountDownLatch(threads.length);
    long l = System.currentTimeMillis();
    AtomicInteger count = new AtomicInteger(0);
    for(int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
        int result = 0;
        for(int j = 0; j < 10000; j++) {
          result += j;
          count.getAndIncrement();
        }
        countDownLatch.countDown();
      });
    }

    for(int i = 0; i < threads.length; i++) {
      try {
        threads[i].start();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }

    try {
      countDownLatch.await();
      System.out.println("countdown 方式商品已经完成, 花费时间: " + (System.currentTimeMillis() - l));
      System.out.println("count" + count);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
}
