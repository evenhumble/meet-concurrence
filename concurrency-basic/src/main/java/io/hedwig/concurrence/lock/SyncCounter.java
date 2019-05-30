package io.hedwig.concurrence.lock;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author patrick
 * @date created on 2019-05-30
 **/
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class SyncCounter {
    private int count = 0;
    private SelfLock lock = new SelfLock();

    @Benchmark
    public int syncInc() {
        synchronized (this) { //synchronized, every thread need to wait the working thread completed
            return this.count++;
        }
    }

    @Benchmark
    public int syncIncWithLock() throws InterruptedException {
        lock.lock(); //lock
        int newCount = ++count;
        lock.unlock(); //unlock
        return newCount;
    }

    public static class SelfLock {
        private boolean isLocked = false;

        public synchronized void lock()
                throws InterruptedException {
            while (isLocked) { //自旋锁 spin lock while loop
                wait();
            }
            isLocked = true;
        }

        public synchronized void unlock() {
            isLocked = false;
            notify();
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SyncCounter.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();

        new Runner(opt).run();
    }


}
