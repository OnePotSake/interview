package com.interview.thread.verification;

import java.util.concurrent.TimeUnit;

/**
 * ������ִ�й����У���������쳣��Ĭ��������ᱻ�ͷ�
 * ���ԣ��ڲ�������Ĺ����У����쳣Ҫ���С�ģ���Ȼ���ܻᷢ����һ�µ������
 * ���磬��һ��web app��������У����servlet�̹߳�ͬ����ͬһ����Դ����ʱ����쳣�������ʣ�
 * �ڵ�һ���߳����׳��쳣�������߳̾ͻ����ͬ�����������п��ܻ���ʵ��쳣����ʱ�����ݡ�
 * ���Ҫ�ǳ�С�ĵĴ���ͬ��ҵ���߼��е��쳣
 */
public class SyncExceptionAttention {
  private volatile  Integer account = 0;
  synchronized void m() {
    while(true){
      account++;
      try {
        System.out.println(Thread.currentThread().getName() + " count = " + account);
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (account == 5) {
        System.out.println(5 / 0);
      }
    }
  }

  public static void main (String[] args) {
    SyncExceptionAttention syncExceptionAttention = new SyncExceptionAttention();
    // ��֤ͬ���쳣�߼�
    new Thread(()->{
      syncExceptionAttention.m();

    }, "t3").start();

   // �����߳�һ�����Ի�ȡ������Դ,�ʶ��߳����Ҫ��ע��,�������߳��쳣,�����߳���Ȼ���������������Դ�ϼ�������
      new Thread(()->{
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        syncExceptionAttention.m();
      }, "t1").start();

  }
}
