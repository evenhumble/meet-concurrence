package io.hedwig.concurrence.basic.thread;

import java.util.Random;

public class MultipleThreadsDemo {
    static Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(random.nextInt(300));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Current Thread is "+Thread.currentThread().getName());
            }).start();
        }
    };
}
