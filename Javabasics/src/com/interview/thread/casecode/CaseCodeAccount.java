package com.interview.thread.casecode;
import java.util.concurrent.TimeUnit;


/**
 * 功能描述: <br>
 *  @Description: CaseCodeAccount2
 * 〈 执行写入方法加锁, 读方法不加锁是否可以? 不行,这样或造成脏读,出现脏数据  〉
 * @Author: OnePotSake
 * @Date: 2020/11/25 23:21
 */
public class CaseCodeAccount {
  private String name;
  private Integer money;

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public Integer getMoney () {
    return money;
  }

  public  void setMoney (Integer money) {
    this.money = money;
  }

  public synchronized  void set(String name, Integer money) {
    this.name = name;
    try {
      TimeUnit.MICROSECONDS.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    this.money = money;
  }

  public /*synchronized*/ Integer get() {
    return this.money;
  }

  public static void main (String[] args) {
    CaseCodeAccount zCode = new CaseCodeAccount();
    new Thread(()->{zCode.set("����", 100);}, "t1").start();
      try {
        TimeUnit.MICROSECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(zCode.get());
      try {
        TimeUnit.MICROSECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(zCode.get());
    }
}
