package com.interview.thread.waystocreatethreads;

import java.util.concurrent.*;

/**
 * 功能描述: <br>
 *
 * @Description: CreateThreadVariants
 * 创建线程的其他方式,变种,获取返回值
 * @Author: OnePotSake
 * @Date: 2020/11/22 18:11
 */
public class CreateThreadVariants {

  public static void main (String[] args) {
    useFutureTaskAndCallableCreation();

    // 不在推荐适用 Excutors 创建线程,底部内置的队列为无限可增长的队列,容易造成溢出
   ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.submit(()-> {
      return 999;
    });

  }

  /**
  * @Description: useFutureTaskAndCallableCreation, 采用FuterIask 配合 Callable 回调线程返回值
  * @Param:
  * @return: void
  * @Author:
  * @Date: 2020/11/22
  */
  private static void useFutureTaskAndCallableCreation () {
    FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
      return 9;
    });
    new Thread(futureTask, "有返回值的线程").start();
    try {
      System.out.println("子线程的返回值：" + futureTask.get());//get()方法会阻塞，直到子线程执行结束才返回
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


