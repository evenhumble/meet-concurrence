package io.hedwig.concurrence.demo.DB;

public class DBConnectionSingleton {

    private DBConnectionSingleton() {
        System.out.printf("%s:Connection created.\n", Thread.currentThread().getName());
    }

    private static class LazyDBConnection {
        private static final DBConnectionSingleton INSTANCE = new DBConnectionSingleton();
    }

    public static DBConnectionSingleton getConnection() {
        return LazyDBConnection.INSTANCE;
    }
}
