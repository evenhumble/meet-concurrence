package io.hedwig.concurrence.demo.DB;

public class ConnectionSingletonTask implements Runnable {
    @Override
    public void run() {

        System.out.printf("%s: Getting the connection...\n",Thread.currentThread().getName());
        DBConnectionSingleton connection=DBConnectionSingleton.getConnection();
        System.out.printf("%s: End\n",Thread.currentThread().getName());
    }
}
