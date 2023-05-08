package com.interview.thread.verification;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述: <br>
 * @Description: 没有 volatile 时, 线程调用方法会在未收到通知的情况下持续执行,如果是内部属性,则还是不能接收到通知
 * @Author: OnePotSake
 * @Date: 2020/11/26 0:14
 */
public class VolatileDemo {
  static VolatileDemo dd = new VolatileDemo();

  private  volatile   Boolean flag = true;
  void m(){
    System.out.println("开始执行m() "+ Thread.currentThread().getName());
    while(flag) {
    }
    System.out.println("一直在等待得到false 成功"+ Thread.currentThread().getName());
  }


  public static void main (String[] args) {
    VolatileDemo volatileDemo = new VolatileDemo();
    new Thread(volatileDemo::m, "t1").start();
    new Thread(dd::m, "t2").start();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    volatileDemo.flag = false;
  }
}
