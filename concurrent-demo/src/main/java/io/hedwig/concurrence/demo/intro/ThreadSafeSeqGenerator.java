package io.hedwig.concurrence.demo.intro;

import javax.annotation.concurrent.GuardedBy;

public class ThreadSafeSeqGenerator {
    @GuardedBy("this")
    private int value;

    public synchronized int getNext(){
        return value++;
    }
}
