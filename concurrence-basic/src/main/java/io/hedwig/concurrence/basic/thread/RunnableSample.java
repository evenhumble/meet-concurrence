package io.hedwig.concurrence.basic.thread;

/**
 * @author: patrick on 2019-01-27
 * @Description:
 */
public class RunnableSample implements Runnable {

  public void run() {
    System.out.println("Runnable Sample!");
  }

  public static void main(String[] args) {
    new Thread(new RunnableSample()).start();
  }
}
