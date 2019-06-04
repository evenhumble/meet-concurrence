package io.hedwig.concurrence.executeService.executor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author patrick
 * @date created on 2019-06-04
 **/
public class CyclicBarrierDemo implements Runnable {

    private CyclicBarrier barrier; //reuse it await: known as barrier condition,like a CountDownLatch

    public CyclicBarrierDemo(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is waiting");
        try {
            Thread.sleep(1000L);
            barrier.await();

            System.out.println(Thread.currentThread().getName() + " is released");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All Previous tasks are completed"));

        Thread t1 = new Thread(new CyclicBarrierDemo(barrier));
        Thread t2 = new Thread(new CyclicBarrierDemo(barrier));
        Thread t3 = new Thread(new CyclicBarrierDemo(barrier));
        if (!barrier.isBroken()) {
            t1.start();
            t2.start();
            t3.start();
        }
    }

}
