package io.hedwig.concurrence.basic.safe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: patrick on 2019-01-26
 * @Description:
 */
public class RaceConditionIssue {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService es = Executors.newFixedThreadPool(10);
    SharedCounter c = new SharedCounter();

    for (int i = 0; i < 100; i++) {
      es.submit(c::increment);
      //Thread interference errors, synchronizing access
      //steps: not atomic: RaceCondition and Critical Section
//      1. get current value
//          2. add current value 1
//        3. store back
    }

    es.shutdown();
    es.awaitTermination(60, TimeUnit.SECONDS);
    System.out.println(c.getCount());
  }
}
