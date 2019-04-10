package io.hedwig.concurrence.basic.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: patrick on 2019-02-24
 * @Description:
 */
public class ContainerLatch {

   List list = new ArrayList();

  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  public static void main(String[] args) {
    ContainerLatch demo = new ContainerLatch();
    CountDownLatch latch = new CountDownLatch(1);
    new Thread(() -> {
      System.out.println("t2 启动");
      if (demo.size() != 5) {
        try {
          latch.await();
          // 可以指定等待时间
          // latch.await(5000, TimeUnit.MILLISECONDS)
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("t2 结束");
    }, "2").start();

    new Thread(() -> {
      for (int i=0; i<10; i++) {
        demo.add(new Object());
        System.out.println("add " + i);

        if (demo.size() == 5) {
          // 打开门闩，让t2得以执行
          latch.countDown();
        }

        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "t1").start();
  }
}
