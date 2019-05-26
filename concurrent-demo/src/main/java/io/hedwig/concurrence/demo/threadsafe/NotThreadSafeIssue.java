package io.hedwig.concurrence.demo.threadsafe;


import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author: patrick on 2019-01-26
 * @Description:
 */
@NotThreadSafe
public class NotThreadSafeIssue {

  private static int count = 0;

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> {
        count++;
        System.out.println(Thread.currentThread().getName() + ":" + count);
      }).start();
    }
  }
}
