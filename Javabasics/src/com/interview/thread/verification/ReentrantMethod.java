package com.interview.thread.verification;

import java.util.concurrent.TimeUnit;

/**
 * ��������: <br>
 *  @Description: ThreadFeature
 * �� ������ķ����ǿ��������,������������
 * @Author: OnePotSake
 * @Date: 2020/11/25 23:25
 */
public class ReentrantMethod {

  public synchronized  void m() {
    System.out.println("m:��ִ����");
    try {
      TimeUnit.MICROSECONDS.sleep(2);
      m2();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("m:end");
  }

  public synchronized  void m2() {
    System.out.println("����m2����,��ִ����");
     try{
       TimeUnit.MICROSECONDS.sleep(5);
          } catch(Exception e) {
          e.getMessage();
        }
    System.out.println("����m2����,end");
  }

  public static void main (String[] args) {
   new Thread(()->{
      new ReentrantMethod().m();

   }, "t1").start();
  }
}
