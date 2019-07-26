package io.hedwig.concurrence.demo.DB;

import java.util.concurrent.locks.Lock;

/**
 * Created by patrick on 16/12/7.
 */
public class UnlockTaskOnce implements Runnable{
    private Lock lock;

    public UnlockTaskOnce(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        FakeOperations.readData();
        FakeOperations.processData();
        FakeOperations.writeData();
        lock.unlock();
    }
}
