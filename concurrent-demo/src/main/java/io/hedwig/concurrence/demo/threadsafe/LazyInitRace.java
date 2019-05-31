package io.hedwig.concurrence.demo.threadsafe;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    //todo: race condition
    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}

class ExpensiveObject { }
