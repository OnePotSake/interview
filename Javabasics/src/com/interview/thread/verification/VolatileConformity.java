package com.interview.thread.verification;
import java.util.ArrayList;
import java.util.List;
/**
 * 功能描述: <br>
 *  @Description: volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * @Author: OnePotSake
 * @Date: 2020/11/26 0:35
 */
public class VolatileConformity {
  volatile Integer sum  = 0;
  void m(){
    for (int i = 0; i < 10000; i++) {
     sum ++;
    }
  }

  public static void main (String[] args) {
    VolatileConformity volatileConformity = new VolatileConformity();
    List<Thread> list = new ArrayList<>();
    for(int i=0; i<10; i++) {
      list.add(new Thread(volatileConformity::m, "thread-"+i));
    }
    list.forEach((o)-> o.start());
    // 加入其他线程中等待其他线程继续
    list.forEach(o->{
      try {
        o.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.println(volatileConformity.sum);
  }
}
