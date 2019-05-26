package io.hedwig.concurrence.demo.intro;

public class NoThreadSafeSeqGenerator {

    private int value;

    public int getNext(){
        return value++;
    }
}
