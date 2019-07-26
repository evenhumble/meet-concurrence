package io.hedwig.concurrence.demo.DB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by patrick on 16/12/7.
 */
public class TaskLock implements Runnable {
    private Lock lock;
    private int number;

    public TaskLock() {
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            lock.lock();
            number = i;
            lock.unlock();
        }
    }
}
