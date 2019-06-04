package io.hedwig.concurrence.executeService.executor;

import java.util.concurrent.Executor;

/**
 * @author patrick
 * @date created on 2019-06-04
 **/
public class ExecutorDemo implements Executor {
    @Override
    public void execute(Runnable command) {
        System.out.println("this is a executor");
        command.run();
        System.out.println("end of executing......");
    }
}
