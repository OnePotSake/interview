package com.interview.thread.verification;

import java.util.concurrent.TimeUnit;

/**
 * ��������: <br>
 * @Description: û�� volatile ʱ, �̵߳��÷�������δ�յ�֪ͨ������³���ִ��,������ڲ�����,���ǲ��ܽ��յ�֪ͨ
 * @Author: OnePotSake
 * @Date: 2020/11/26 0:14
 */
public class VolatileDemo {
  static VolatileDemo dd = new VolatileDemo();

  private  volatile   Boolean flag = true;
  void m(){
    System.out.println("��ʼִ��m() "+ Thread.currentThread().getName());
    while(flag) {
    }
    System.out.println("һֱ�ڵȴ��õ�false �ɹ�"+ Thread.currentThread().getName());
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
