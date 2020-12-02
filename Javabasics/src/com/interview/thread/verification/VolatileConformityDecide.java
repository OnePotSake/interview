package com.interview.thread.verification;
import java.util.ArrayList;
import java.util.List;

/**
 * ��������: <br>
 *  @Description: volatile�����ܱ�֤����̹߳�ͬ�޸�running����ʱ�������Ĳ�һ�����⣬Ҳ����˵volatile�������synchronized
 * @Author: OnePotSake
 * @Date: 2020/11/26 0:35
 */
public class VolatileConformityDecide {
  volatile Integer sum  = 0;
  synchronized void m(){
    for (int i = 0; i < 10000; i++) {
     sum ++;
    }
    System.out.println(Thread.currentThread().getName());
  }

  public static void main (String[] args) {
    VolatileConformityDecide volatileConformity = new VolatileConformityDecide();
    List<Thread> list = new ArrayList<>();
    for(int i=0; i<10; i++) {
      list.add(new Thread(volatileConformity::m, "thread-"+i));
    }
    list.forEach((o)-> o.start());
    // ���������߳��еȴ������̼߳���
    list.forEach(o->{
      try {
        o.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println(volatileConformity.sum);
  }
}