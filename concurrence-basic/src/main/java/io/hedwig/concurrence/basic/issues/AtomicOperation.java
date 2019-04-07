package io.hedwig.concurrence.basic.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: patrick on 2019-02-23
 * @Description:
 */
public class AtomicOperation {

  static int unsafeCounter = 0;
  static AtomicInteger safeCounter = new AtomicInteger(0);
  //don't use same string as lock
  static Object lock = new Object(); //used as lock, if it is different object, then it is not the same lock

  public static void unsafeCounting(){
    try {
      for (int i = 0; i < 10; i++) {
        Thread.sleep(20);
        unsafeCounter++;
      }
      System.out.println(
          String.format("current thread: %s,%d",
                        Thread.currentThread().getName(),
                        unsafeCounter));
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public synchronized static void syncSafeCounting(){
    unsafeCounting();
  }

  public static void syncSafeCounting2(){
    synchronized (lock){
      unsafeCounting();
    }
  }

  public static void atomicCounting(){
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(20);
        safeCounter.getAndIncrement();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

  }
  public static void main(String[] args) {
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      new Thread(AtomicOperation::unsafeCounting).start();
//      threads.add(new Thread(AtomicOperation::unsafeCounting));
//      threads.add(new Thread(AtomicOperation::syncSafeCounting));
      threads.add(new Thread(AtomicOperation::atomicCounting));
    }
    threads.forEach(Thread::start);

    threads.forEach((o)-> {
      try {
        o.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    //  the problem
    System.out.println("unsafeCounter: "+unsafeCounter);
    System.out.println("safeCounter:"+safeCounter.get());
  }
}
