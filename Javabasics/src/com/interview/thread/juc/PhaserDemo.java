package com.interview.thread.juc;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: <br>
 *
 * @program: PhaserDemo
 * @Description: 分组 分阶段 执行线程内容
 * @Author: OnePotSake
 * @Date: 2020/12/6 22:02
 */
public class PhaserDemo {
  public static void main(String[] args) {
    MarriagePhaser phaser = new MarriagePhaser();
    phaser.bulkRegister(5);
    for(int i=0; i<5; i++) {
      final int nameIndex = i;
      new Thread(()->{

        Person p = new Person("person " + nameIndex);
        p.arrive();
        phaser.arriveAndAwaitAdvance();

        p.eat();
        phaser.arriveAndAwaitAdvance();

        p.leave();
        phaser.arriveAndAwaitAdvance();
      }).start();
    }


  }
}

class MarriagePhaser extends Phaser {
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
        return true;
      default:
        return false;
    }
  }
}

class Person {
 static Random r = new Random();
  String name;

  static void milliSleep(int milli) {
    try {
      TimeUnit.MILLISECONDS.sleep(milli);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  public Person(String name) {
    this.name = name;
  }

  public void arrive() {
    milliSleep(r.nextInt(1000));
    System.out.printf("%s 到达现场！\n", name);
  }

  public void eat() {
    milliSleep(r.nextInt(1000));
    System.out.printf("%s 吃完!\n", name);
  }

  public void leave() {
    milliSleep(r.nextInt(1000));
    System.out.printf("%s 离开！\n", name);
  }

}