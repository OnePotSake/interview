package com.interview.thread.verification;

import java.util.concurrent.TimeUnit;

public class ReentranMethodByParent extends Parent {
  public synchronized void boy(){
    System.out.println("boy�ķ���");

    try{
      TimeUnit.MICROSECONDS.sleep(2);
      man();
    } catch(Exception e) {
      e.getMessage();
    }
    System.out.println("boyend");
  }

  public static void main (String[] args) {
    Thread thread = new Thread(()->{
      new ReentranMethodByParent().boy();
    });
    thread.start();
  }
}

class Parent {

  public void  man() {
    System.out.println("����ķ���");

     try{
       TimeUnit.MICROSECONDS.sleep(2);
          } catch(Exception e) {
          e.getMessage();
        }
    System.out.println("����end");
  }


}