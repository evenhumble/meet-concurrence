package io.hedwig.concurrence.basic.thread.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MasterThread {
    public static int threadPoolSize = 50;
    public static int loopSize = 50;

    public void launch(File file, boolean isParallel, boolean isSynchronized)
            throws InterruptedException, IOException {

        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        List<Thread> threads = new ArrayList<Thread>(threadPoolSize);
        for (int i = 1; i <= threadPoolSize; i++) {
            threads.add(new Thread(new WorkerThread(i, loopSize, bw,
                    isSynchronized)));
        }

        if (isParallel) {
            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

        } else {
            for (Thread thread : threads) {
                thread.start();
                thread.join();
            }
        }

        bw.close();
        fos.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        MasterThread mt = new MasterThread();
        mt.launch(new File("test.txt"),false,true);
        long end = System.currentTimeMillis();
        System.out.println("total running time:"+(end-start));

        start = System.currentTimeMillis();
        mt.launch(new File("test.txt"),true,true);
        end = System.currentTimeMillis();
        System.out.println("total running time:"+(end-start));

        start = System.currentTimeMillis();
        mt.launch(new File("test.txt"),true,false);
        end = System.currentTimeMillis();
        System.out.println("total running time:"+(end-start));


        start = System.currentTimeMillis();
        mt.launch(new File("test.txt"),false,false);
        end = System.currentTimeMillis();
        System.out.println("total running time:"+(end-start));
    }
}
