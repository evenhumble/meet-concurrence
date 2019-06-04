package io.hedwig.concurrence.executeService.executor;

import java.util.concurrent.ThreadFactory;

/**
 * @author patrick
 * @date created on 2019-06-04
 **/
public class ThreadFactorDemo implements ThreadFactory {
    private int threadId;
    private String name;

    public ThreadFactorDemo(String name) {
        threadId = 1;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread_" + threadId);
        System.out.println("created new thread with id : " + threadId +
                " and name : " + t.getName());
        threadId++;
        return t;
    }
}
