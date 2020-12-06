package com.interview.thread.juc;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserDemo2 {
  static  MarriagePhaser2 phaser = new MarriagePhaser2();
  public static void main(String[] args) {
    phaser.bulkRegister(7);

    for(int i=0; i<5; i++) {
      new Thread(new People("p" + i)).start();
    }

    new Thread(new People("man")).start();
    new Thread(new People("woman")).start();

  }


 static class MarriagePhaser2 extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
      switch(phase) {
        case 0:
          System.out.println("人齐吃饭");
          return false;
        case 1:
          System.out.println("喜宴完毕");
          return false;
        case 2:
          System.out.println("清场");
          return false;
        case 3:
          System.out.println("入洞房");
          return true;
        default:
          return false;
      }
    }
  }

 static class People implements  Runnable{
     Random r = new Random();
    String name;

     void milliSleep(int milli) {
      try {
        TimeUnit.MILLISECONDS.sleep(milli);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }

    public People(String name) {
      this.name = name;
    }

    public void arrive() {
      milliSleep(r.nextInt(1000));
      System.out.printf("%s 到达现场！\n", name);
      phaser.arriveAndAwaitAdvance();
    }

    public void eat() {
      milliSleep(r.nextInt(1000));
      System.out.printf("%s 吃完!\n", name);
      phaser.arriveAndAwaitAdvance();
    }

    public void leave() {
      milliSleep(r.nextInt(1000));
      System.out.printf("%s 离开！\n", name);
      phaser.arriveAndAwaitAdvance();
    }

    public void hug() {
      if(name.equals("man") || name.equals("woman")) {
        milliSleep(r.nextInt(1000));
        System.out.printf("%s 洞房！\n", name);
        phaser.arriveAndAwaitAdvance();
      } else {
        milliSleep(r.nextInt(1000));
        phaser.arriveAndDeregister();
        System.out.printf("%s 离开！\n", name);
      }
    }

   @Override
   public void run() {
     arrive();
     eat();
     leave();
     hug();
   }
 }
}