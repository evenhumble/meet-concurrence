package io.hedwig.concurrence.basic.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: patrick on 2019-02-25
 * @Description:
 */
public class MTUtil {
  static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
  static final int THREAD_COUNT= CPU_COUNT*2;
  static  final ExecutorService es = Executors.newFixedThreadPool(THREAD_COUNT);
  static volatile boolean isLoadMade = false;

  public static synchronized void makeLoad(){
    if(isLoadMade) return;
    for (int i = 0; i < THREAD_COUNT; i++) {
      es.submit(new Runnable() {
        @Override
        public void run() {
          for (int i = 1; ; ++i) {
            if (i % 1000000 == 0) {
              sleep(1);
            }
          }
        }
      });
    }
  }

  public static void sleep(long l){
    try {
      Thread.sleep(l);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
