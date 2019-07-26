package io.hedwig.concurrence.demo.DB;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by patrick on 16/12/7.
 */
public class TaskAtomic implements Runnable{
    private AtomicInteger number;

    public TaskAtomic() {
        this.number = new AtomicInteger();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            number.set(i);
        }
    }
}
