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

/**
 * @author: patrick on 2019-04-10
 * @Description:
 */
public class JobMaster {

  public  boolean jobStatus = false;
  public int FROM_POSITION = 0;
  public int batchSize = 300;
  public ConcurrentLinkedQueue<Map<String, Object>> queue = new ConcurrentLinkedQueue<>();
  ExecutorService es;
  List<Map<String,Object>> overall = new LinkedList();
  private static CountDownLatch latch = new CountDownLatch(5);

  public class FetchDataWorker extends Thread {

    @Override
    public void run() {
      while (jobStatus && FROM_POSITION < 10000) {
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
            map.put(String.valueOf(i), new Object());
            queue.offer(map);
          }

//        }
      }
      while (!queue.isEmpty()){
        try {
          System.out.println("waiting queue empty");
          sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("start shutdown es");
      System.out.println(overall.size());

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
      while (jobStatus || !queue.isEmpty()) {
        System.out.println("working thread is still alive");
        Map<String, Object> result = queue.poll();
        if(result!=null){
            System.out.println(result);
            overall.add(result);
        }
      }

    }
  }

  public void runJob() {
    jobStatus = true;
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
    master.jobStatus =false;
    System.out.println("all shutdown");

  }
}
