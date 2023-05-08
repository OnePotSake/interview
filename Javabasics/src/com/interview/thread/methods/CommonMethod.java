package com.interview.thread.methods;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述: <br>
 *   @program: ${PROJECT_NAME}
 *  @Description: CommonMethod
 * 〈〉
 * @Author: OnePotSake
 * @Date: 2020/11/23 2:29
 */
public class CommonMethod {


  public static void main (String[] args) throws InterruptedException {
    // yield();
   // join();
    waitMethod();

  }

  private static void waitMethod (){
    ThreadA t1 = new ThreadA("t1");

    synchronized(t1) {
      try{
        // 启动线程并准备wait
        t1.start();
        System.out.println("开始 wait() :" + System.currentTimeMillis());
        t1.wait();
        System.out.println("结束 wait() " + System.currentTimeMillis() );
      } catch(Exception e) {
        e.getMessage();

      }
    }
  }

  /**
  * @Description: join
   * 将线程置于另一个线程中执行,其会在加入线程之后执行.
  * @Param: []
  * @return: void
  * @Author: OnePotSake
  * @Date: 2020/11/23
  */
  private static void join () {
    Thread t1 = new Thread(()->{
      for (int i = 0; i < 100; i++) {

        try {
          TimeUnit.MICROSECONDS.sleep(1);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("我是线程1");
      }

    }, "t1");

    Thread t2 = new Thread(()->{
      for (int i = 0; i < 100; i++) {
        System.out.println("我是线程2,我要加入t1中");
        try {
          t1.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("我是线程2");
      }

    }, "t2");

    t1.start();
    t2.start();
  }

  /**
  * @Description: yield
  *  退出让行,但不意味放弃主动权,还是会争夺到,故并没有实现真正让行
  *  暂停当前正在执行的线程对象，并执行其他线程
  * @Author: cp
  * @Date: 2020/11/23
  */
  private static void yield () {
    Thread t1 = new Thread(()->{
     for (int i = 0; i < 100; i++) {
       try {
         TimeUnit.MICROSECONDS.sleep(1);
         if (i > 5) {
           Thread.yield();
           System.out.println("我是线程1, 大于5 了");
         }
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
       System.out.println("我是线程1");
     }

   }, "t1");

    Thread t2 = new Thread(()->{
     for (int i = 0; i < 100; i++) {
       try {
         TimeUnit.MICROSECONDS.sleep(1);
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
       System.out.println("我是线程2");
     }

   }, "t2");

    t1.start();
    t2.start();
  }


}


 class ThreadA extends  Thread {
   public  ThreadA(String name) {
     super(name);
   }

   @Override
   public void run() {
     synchronized (this) {
       System.out.println("this:" + this);
       try {
         Thread.sleep(2000);//使当前线程阻塞1秒
       } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
       }
       System.out.println(Thread.currentThread().getName() + " call notify()");
       this.notify();
     }

   }
}
