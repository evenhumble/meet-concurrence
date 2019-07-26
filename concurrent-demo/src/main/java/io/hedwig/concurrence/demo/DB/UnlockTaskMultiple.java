package io.hedwig.concurrence.demo.DB;

import java.util.concurrent.locks.Lock;

/**
 * Created by patrick on 16/12/7.
 */
public class UnlockTaskMultiple implements Runnable{
    private Lock lock;

    public UnlockTaskMultiple(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        FakeOperations.readData();
        lock.unlock();
        FakeOperations.processData();
        lock.lock();
        FakeOperations.writeData();
        lock.unlock();
    }
}
