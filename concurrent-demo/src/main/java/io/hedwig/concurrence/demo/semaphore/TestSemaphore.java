package io.hedwig.concurrence.demo.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: patrick on 2019-05-11
 * @Description:
 * todo: if not release, what happened? how to implemented into
 * distribution system
 */
public class TestSemaphore {

  public static void main(String[] args) {
    ExecutorService es = Executors.newCachedThreadPool();
    final Semaphore semaphore = new Semaphore(5);

    for (int i = 0; i < 20; i++) {
      final int index = i;
      Runnable runnable = () -> {
        try {
          semaphore.acquire();
          System.out.println("accessing no " + index);
          Thread.sleep(1000L);
//          semaphore.release();
          System.out.println("----end of running ----");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          semaphore.release();
        }
      };
      es.execute(runnable);
    }

//    try {
//      es.awaitTermination(1000, TimeUnit.MILLISECONDS);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    es.shutdown();
//    es.shutdownNow();
  }

}
