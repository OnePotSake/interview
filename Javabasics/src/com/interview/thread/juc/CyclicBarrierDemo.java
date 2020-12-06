package com.interview.thread.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

  public static void main(String[] args) {
    // easyDemo();
  }

  private static void easyDemo() {
    CyclicBarrier cb = new CyclicBarrier(3, () -> System.out.println("3人齐发车"));
    for(int i = 0; i < 9; i++) {
      new Thread(() -> {
        try {
          cb.await();
        } catch(InterruptedException e) {
          e.printStackTrace();
        } catch(BrokenBarrierException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }


}
