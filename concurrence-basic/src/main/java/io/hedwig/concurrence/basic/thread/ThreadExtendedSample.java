package io.hedwig.concurrence.basic.thread;

/**
 * @author: patrick on 2019-01-27
 * @Description:
 */
public class ThreadExtendedSample extends Thread {

  @Override
  public void run() {
    System.out.println("this thread extended sample");
  }

  public static void main(String[] args) {
    new ThreadExtendedSample().start();
  }
}
