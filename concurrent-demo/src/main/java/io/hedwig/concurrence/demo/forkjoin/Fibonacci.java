package io.hedwig.concurrence.demo.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer>{

    private final int n;
    public static final int[] SMALL_INTS = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if(n<=10){
            return compute(n);
        }
        Fibonacci f1 = new Fibonacci(n - 2);
        Fibonacci f2 = new Fibonacci(n - 1);
        System.out.printf("New Thread for : %s\n", n-2);
        f1.fork();
        System.out.printf("New Thread for : %s\n", n - 1);
        f2.fork();
        return f1.join() + f2.join();
    }

    private Integer compute(int n) {
        return SMALL_INTS[n];
    }

    public static void main(String[] args) {
        int n = 1234;
        Fibonacci fibonacci = new Fibonacci(n);
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.invoke(fibonacci);
        System.out.printf("fibonacci :%s array is %s", n, fibonacci.join());
    }
}
