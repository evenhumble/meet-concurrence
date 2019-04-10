package io.hedwig.concurrence.basic.safe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: patrick on 2019-01-26
 * @Description:
 */
public class AtomicSolution {

  //counter is shared for all thread
  private static AtomicInteger count = new AtomicInteger(0);

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
//        count.addAndGet(1);
        System.out.println(Thread.currentThread().getName() +
                           ":" + count.addAndGet(1));
      }).start();
    }
  }
}
