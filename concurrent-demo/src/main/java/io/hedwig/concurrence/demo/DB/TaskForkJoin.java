package io.hedwig.concurrence.demo.DB;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Created by patrick on 16/12/7.
 */
public class TaskForkJoin extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    private int array[];
    private int start, end;

    public TaskForkJoin(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end-start>1000){
            int mid = (end+start)/2;
            TaskForkJoin task1 = new TaskForkJoin(array,start,mid);
            TaskForkJoin task2 = new TaskForkJoin(array,mid,end);
            task1.fork();
            task2.fork();
            task1.join();
            task2.join();
        }else {
            for (int i = start; i <end ; i++) {
                array[i]++;
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
