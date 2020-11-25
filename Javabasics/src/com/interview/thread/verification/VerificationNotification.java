package com.interview.thread.verification;

public class VerificationNotification implements Runnable {

  // 可见性,有序性 验证
  private  /*volatile*/ int count = 1000;

  public static void main (String[] args) {
    VerificationNotification verificationNotification = new VerificationNotification();
    for (int i = 0; i < 1000; i++) {
           new Thread(verificationNotification, "name: " + i ).start();
    }

  }

  @Override
  public /*synchronized*/ void run () {
    count--;
    System.out.println(Thread.currentThread().getName() + " count = " + count);
  }
}
