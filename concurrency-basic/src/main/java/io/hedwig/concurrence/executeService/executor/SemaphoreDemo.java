package io.hedwig.concurrence.executeService.executor;

import java.util.concurrent.Semaphore;

/**
 * @author patrick
 * @date created on 2019-06-04
 *
 * Semaphore is used to block thread level access
 **/
public class SemaphoreDemo {


    public static void main(String[] args) {
         Semaphore semaphore = new Semaphore(10);
        System.out.println("available permit: "+semaphore.availablePermits());
        System.out.println("Number of threads waiting to acquire: "+semaphore.getQueueLength());

        while(true){
            new Thread(() -> {
                if(semaphore.tryAcquire()){
                    try {
                        semaphore.acquire();
                        System.out.println("permit is acquired by "+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                }
            }).start();
        }

        //Mutex like data-structure using Semaphore.
    }

}
