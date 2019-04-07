package io.hedwig.concurrence.basic.issues;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: patrick on 2019-02-24
 * @Description: Monitor Container Size
 */
public class ContainerMonitor<T> {

  List<T> container = new ArrayList<>();
  Thread watchThread;

  public ContainerMonitor() {
    this.watchThread = new Thread(this::watchSize, "watcher-thread");
    System.out.println("start watch thread");
    this.watchThread.start();
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      this.watchThread.join();
      System.out.println("finalize container");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void add(T obj) throws InterruptedException {
    Thread.sleep(10);
    this.container.add(obj);
  }

  public int size() {
    return this.container.size();
  }

  private void watchSize() {
    do {
//      try {
//        Thread.sleep(1);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
      System.out.println("start detected");
      if (this.container.size()>10) {
        System.out.println(String.format("container size %d is detected", this.container.size()));
      }
    } while (this.container.size() <= 100);
    System.out.println("stop watch container");
  }

  public static void main(String[] args) throws InterruptedException {
     ContainerMonitor<Integer> container = new ContainerMonitor<>();
    for (int i = 0; i < 200; i++) {
      container.add(i);
    }
    System.out.println("end of container");
    System.exit(0);
  }

}
