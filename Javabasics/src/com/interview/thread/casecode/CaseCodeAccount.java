package com.interview.thread.casecode;
import java.util.concurrent.TimeUnit;


/**
 * ��������: <br>
 *  @Description: CaseCodeAccount2
 * �� ִ��д�뷽������, �������������Ƿ����? ����,������������,����������  ��
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
