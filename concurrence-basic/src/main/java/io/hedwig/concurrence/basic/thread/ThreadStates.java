package io.hedwig.concurrence.basic.thread;

/**
 * @author: patrick on 2019-01-27
 * @Description:
 */
public class ThreadStates {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      try {
        if (Thread.currentThread().isInterrupted()) {
          return;
        }
        Thread.sleep(i * 10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
