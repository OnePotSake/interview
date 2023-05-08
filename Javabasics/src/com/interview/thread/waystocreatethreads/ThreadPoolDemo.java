package com.interview.thread.waystocreatethreads;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述: <br>
 *   @program: ${PROJECT_NAME}
 *  @Description: ThreadPoolDemo
 * 〈采用线程工厂命名,并用线程池创建线程,  推荐方法〉
 * @Author: OnePotSake
 * @Date: 2020/11/22 23:19
 */
public class ThreadPoolDemo {

  static ThreadLocal<SimpleDateFormat>threadLocal = new ThreadLocal<SimpleDateFormat>() {

    @Override
    protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
  };

  //核心线程满了，则进入队列，队列满了，则创建新线程，当线程数达到最大线程数，则进入拒绝策略
  static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,5,1,
      TimeUnit.MINUTES,new LinkedBlockingDeque<>(),new NamedThreadFactory("测试"));



  public static class NamedThreadFactory implements ThreadFactory{

    private final AtomicInteger poolNumber = new AtomicInteger(1);

    private final ThreadGroup threadGroup;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public  final String namePrefix;

    NamedThreadFactory(String name){
      SecurityManager s = System.getSecurityManager();
      threadGroup = (s != null) ? s.getThreadGroup() :
          Thread.currentThread().getThreadGroup();
      if (null==name || "".equals(name.trim())){
        name = "pool";
      }
      namePrefix = name +"-"+
          poolNumber.getAndIncrement() +
          "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(threadGroup, r,
          namePrefix + threadNumber.getAndIncrement(),
          0);
      if (t.isDaemon()) {
        t.setDaemon(false);
      }
      if (t.getPriority() != Thread.NORM_PRIORITY) {
        t.setPriority(Thread.NORM_PRIORITY);
      }
      return t;
    }
  }

  public static void main(String[] args) {
    threadPoolExecutor.execute(new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(threadLocal.get().parse("2019-10-22 16:59:00"));
          throw new NullPointerException("sfa");
        } catch (ParseException | java.text.ParseException e) {
          e.printStackTrace();
        }
      }
    });
  }

}
