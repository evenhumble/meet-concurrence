package io.hedwig.concurrence.executeService.executor;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author patrick
 * @date created on 2019-06-04
 **/
public class CountDownLatchDemo implements Callable<String> {

    private CountDownLatch latch;
    private String name;
    private long period;


    public CountDownLatchDemo(CountDownLatch latch, String name, long period) {
        this(name, period);
        this.latch = latch;
    }

    public CountDownLatchDemo(String name, long period) {
        this.name = name;
        this.period = period;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(period);
            if (this.latch != null) {
                latch.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return name;
    }

    public static class Worker implements Runnable{
        private List<String> output ;
        private CountDownLatch latch;

        public Worker(List<String> output, CountDownLatch latch) {
            this.output = output;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println("doing something");
                System.out.println("current latch count:"+latch.getCount());
                output.add("Count Down");
            }finally {
                latch.countDown();
            }
        }
    }
}
