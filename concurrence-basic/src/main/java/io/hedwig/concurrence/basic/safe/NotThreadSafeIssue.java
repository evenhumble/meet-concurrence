package io.hedwig.concurrence.basic.safe;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author: patrick on 2019-01-26
 * @Description:
 */
@NotThreadSafe
public class NotThreadSafeIssue {

  private static int count = 0;

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + ":" + count++);
      }).start();
    }
  }
}
