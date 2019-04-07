package io.hedwig.concurrence.basic.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: patrick on 2019-02-24
 * @Description:
 */
public class ContainerMonitorDemo {

  volatile List list = new ArrayList();

  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  public static void main(String[] args) {
    ContainerMonitorDemo demo = new ContainerMonitorDemo();

    new Thread(()->{
      for (int i = 0; i < 10; i++) {
        Object o = new Object();
        System.out.println(o);
        demo.add(o);
        System.out.println("add "+i);
      }
      try {
        TimeUnit.MILLISECONDS.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    },"t1").start();

    new Thread(()->{
      while(true){

        if (demo.size()>8) {
          for (Object o : demo.list) {
            System.out.println(o);
          }
          break;
        }
      }
      System.out.println("end thread 2");
    },"t2").start();
  }
}
