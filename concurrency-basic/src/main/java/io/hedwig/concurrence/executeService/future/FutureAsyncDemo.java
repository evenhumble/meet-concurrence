package io.hedwig.concurrence.executeService.future;

import java.util.concurrent.*;

/**
 * @author patrick
 * @date created on 2019-06-04
 **/
public class FutureAsyncDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("before sleeping");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("this is a future task");
                return "Hello World!";
            }
        });

        if (future.isDone() && future.isCancelled()) {
            System.out.println(future.get());
        }
        System.out.println("end of getting future......");
    }
}
