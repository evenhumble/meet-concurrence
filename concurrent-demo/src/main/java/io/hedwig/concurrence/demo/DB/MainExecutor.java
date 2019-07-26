package io.hedwig.concurrence.demo.DB;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by patrick on 16/12/7.
 */
public class MainExecutor {
    public static void main(String[] args) {
        TaskAtomic atomicTask = new TaskAtomic();
        TaskLock lockTask = new TaskLock();
        runTask(lockTask, "lock");
        runTask(atomicTask, "atomicTask");
        Lock lock = new ReentrantLock();
        runTaskInThreadPool(lockTask, "lock");
        runTaskInThreadPool(atomicTask, "atomicTask");
        runTaskInThreadPool(new ConnectionTask(), "connection-task");
        runTaskInThreadPool(new ConnectionSingletonTask(), "connection-singleton-task");
        //for unlock once and unlock multiple
//        runTask(new UnlockTaskOnce(lock), "unlock-once");
//        runTask(new UnlockTaskMultiple(lock), "unlock-multiple");

    }

    private static void runTask(Runnable task, String taskName) {
        int numThreads = 50;
        Thread threads[] = new Thread[numThreads];
        Date begin, end;
        begin = new Date();
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = new Date();
        System.out.printf(("Main: " + taskName + "results: %d\n"), (end.getTime() - begin.getTime()));
    }

    private static void runTaskInThreadPool(Runnable task, String taskName) {
        int numThreads = 50;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Date begin, end;
        begin = new Date();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(task);
            executor.execute(thread);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = new Date();
        System.out.printf("Main: " + taskName + "Executor: %d\n", (end.getTime() - begin.getTime()));

    }

    private static void runForkJoinTask(ForkJoinTask task, String taskName) {
        Date start, end;
        ForkJoinPool pool = new ForkJoinPool();
        start = new Date();
        pool.execute(task);
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = new Date();
        System.out.printf("Core: Fork/Join" + taskName + ": %d\n", (end.getTime() - start.getTime()));

    }
}
