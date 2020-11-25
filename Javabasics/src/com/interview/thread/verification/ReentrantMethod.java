package com.interview.thread.verification;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述: <br>
 *  @Description: ThreadFeature
 * 〈 锁对象的方法是可以重入的,即可重入锁〉
 * @Author: OnePotSake
 * @Date: 2020/11/25 23:25
 */
public class ReentrantMethod {

  public synchronized  void m() {
    System.out.println("m:我执行了");
    try {
      TimeUnit.MICROSECONDS.sleep(2);
      m2();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("m:end");
  }

  public synchronized  void m2() {
    System.out.println("我是m2方法,我执行了");
     try{
       TimeUnit.MICROSECONDS.sleep(5);
          } catch(Exception e) {
          e.getMessage();
        }
    System.out.println("我是m2方法,end");
  }

  public static void main (String[] args) {
   new Thread(()->{
      new ReentrantMethod().m();

   }, "t1").start();
  }
}
