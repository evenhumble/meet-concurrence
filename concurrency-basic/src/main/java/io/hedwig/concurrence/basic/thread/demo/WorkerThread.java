package io.hedwig.concurrence.basic.thread.demo;

import java.io.BufferedWriter;
import java.io.IOException;

public class WorkerThread implements Runnable {
    private int threadNum = Runtime.getRuntime().availableProcessors()*2;
    private int loopSize =10;
    private BufferedWriter bw;
    private boolean isSync;

    public WorkerThread(int threadNum, int loopSize, BufferedWriter bw, boolean isSync) {
        this(loopSize,bw,isSync);
        this.threadNum=threadNum;

    }
    public WorkerThread( int loopSize, BufferedWriter bw, boolean isSync) {

        this.loopSize = loopSize;
        this.bw = bw;
        this.isSync = isSync;
    }

    @Override
    public void run() {
        for(int i=1; i<=loopSize; i++) {
            try {
                if(isSync) {
                    synchronized(bw) {
                        writeLine(bw);
                    }
                } else {
                    writeLine(bw);
                }

                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void writeLine(BufferedWriter bw) {
        try {
            bw.write("something="+Thread.currentThread().getId());
            bw.newLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String command(int i) {
        return "Callable thread "+this.threadNum+"-loopNumber "+i;
    }

    public String toString() {
        return "Thread "+threadNum;
    }
}
