package io.hedwig.concurrence.demo.DB;

public class ConnectionTask implements Runnable {
    @Override
    public void run() {

        System.out.printf("%s: Getting the connection...\n",Thread.currentThread().getName());
        DBConnection connection=DBConnection.getConnection();
        System.out.printf("%s: End\n",Thread.currentThread().getName());
    }
}
