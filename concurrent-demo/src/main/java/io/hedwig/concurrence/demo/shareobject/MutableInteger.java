package io.hedwig.concurrence.demo.shareobject;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        MutableInteger mutableInteger = new MutableInteger();

        for (int i = 1; i < 101; i++) {
//            final int index = i;
//            new Thread(() -> mutableInteger.set(index)).start();
            mutableInteger.set(i);
        }

        System.out.println(mutableInteger.get());
    }
}


