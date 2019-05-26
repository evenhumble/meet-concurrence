package io.hedwig.concurrence.demo.intro;

public class ThreadSafeSeqGenerator {
    @GuardedBy("this")
    private int value;

    public synchronized int getNext(){
        return value++;
    }
}
