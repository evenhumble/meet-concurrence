package io.hedwig.concurrence.executeService.executor;

import java.util.concurrent.*;

/**
 * @author patrick
 * @date created on 2019-06-04
 **/
public class ExecutorServiceDemo {

    ExecutorService executorService ;
    ScheduledExecutorService scheduledExecutorService ;

    public void initExecutorService(){

        this.executorService= Executors.newFixedThreadPool(10);

        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    }

    public void submitTask(Runnable task){
        executorService.submit(task);
    }

    public void scheduleTask(Runnable task){
        System.out.println("schedule a task");
        this.scheduledExecutorService.schedule(task,1000,TimeUnit.MICROSECONDS);
        System.out.println("end of scheduling");
    }

    public void shutdown(){
        this.executorService.shutdown();
        this.scheduledExecutorService.shutdown();
    }


    public static void main(String[] args) {
        ExecutorServiceDemo demo = new ExecutorServiceDemo();
        demo.initExecutorService();

        Runnable task = () -> System.out.println("running for feature.....");

        demo.submitTask(task);
        demo.scheduleTask(task);
        demo.shutdown();

    }
}
