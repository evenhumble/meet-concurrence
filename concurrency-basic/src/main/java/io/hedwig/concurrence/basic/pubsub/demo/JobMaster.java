package io.hedwig.concurrence.basic.pubsub.demo;


import static io.hedwig.concurrence.basic.utils.MTUtil.sleep;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: patrick on 2019-04-10
 * @Description:
 */
public class JobMaster {

  public AtomicBoolean jobStatus = new AtomicBoolean(false);
  public int FROM_POSITION = 0;
  public int batchSize = 300;
  public ConcurrentLinkedQueue<Map<String, Object>> queue = new ConcurrentLinkedQueue<>();
  ExecutorService es;
  List<Map<String,Object>> overall = new LinkedList();
  private static CountDownLatch latch = new CountDownLatch(5);

  public class FetchDataWorker extends Thread {

    @Override
    public void run() {
      while (jobStatus.get() && FROM_POSITION < 1000000000) {
//        if (!queue.isEmpty()) {
//          System.out.println("fetch data thread is alive");
//          try {
//            sleep(30);
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        } else {
          try {
              sleep(30);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          FROM_POSITION += batchSize;
          for (int i = 0; i < batchSize; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(String.valueOf(FROM_POSITION+i), new Object());
            queue.offer(map);
          }

//        }
      }
//      while (!queue.isEmpty()){
//        try {
//          System.out.println("waiting queue empty");
//          sleep(300);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//      }

      System.out.println("start shutdown es");
        if(!queue.isEmpty())  {
            System.out.println(queue.poll());
            queue.clear();
            System.out.println(queue.size());
        }
      System.out.println(overall.size());
        for (Map<String, Object> entry : overall) {
            System.out.println(entry);
        }
      es.shutdownNow();
//      try {
//        latch.await();
//        es.shutdown();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }

    }
  }

  public class WorkThread extends Thread {

    @Override
    public void run() {
        // !queue.isEmpty() is not meaningful because the state may be different when others add to queue
      while (jobStatus.get()) {
          System.out.println("working thread is still alive");
        Map<String, Object> result = queue.poll();
        if(result!=null){
            System.out.println(result);
            overall.add(result);
        }
      }
      if(!jobStatus.get()) System.out.println("job status is changed");

    }
  }

  public void runJob() {
    jobStatus.set(true);
    es= Executors.newFixedThreadPool(6);
    es.submit(new FetchDataWorker());
    for (int i = 0; i < 5; i++) {
      es.submit(new WorkThread());
    }
  }

  public static void main(String[] args) {
    JobMaster master = new JobMaster();
    master.runJob();
    System.out.println("end of job running");

    sleep(3000);
    master.jobStatus.set(false);
    System.out.println("all shutdown");

  }
}
