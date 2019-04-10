package io.hedwig.concurrence.basic.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: patrick on 2019-02-24
 * @Description:
 */
public class ContainerWaitNotify {

   List list = new ArrayList();

  public void add(Object o) {
    list.add(o);
  }

  public int size() {
    return list.size();
  }

  public static void main(String[] args) {
    ContainerWaitNotify demo = new ContainerWaitNotify();
    final Object lock = new Object();

    new Thread(() -> {
      System.out.println("start watching ...");
      synchronized (lock){
        System.out.println("start T2");
        if(demo.size()!=5){
          try {
            lock.wait(); //wait for notify
          }catch (InterruptedException e){
            e.printStackTrace();
          }
        }
        lock.notify();
      }
      System.out.println("t2 结束");
    }, "t2").start();

    new Thread(() -> {
      synchronized (lock){
        System.out.println("start add items");
        for (int i = 0; i < 10; i++) {
           demo.add(new Object());
          System.out.println("add "+i);
          if(demo.size()==5){ // actually it is not good
            // in reality for separating concern
            lock.notify();
            try {
              lock.wait();
            }catch (InterruptedException e){
              e.printStackTrace();
            }
          }
          try {
            TimeUnit.MILLISECONDS.sleep(20);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    },"t1").start();
  }
}
