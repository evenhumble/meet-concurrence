package io.hedwig.concurrence.demo.DB;

public class DBConnection {
    private static DBConnection connection;

    private DBConnection() {
        System.out.printf("%s:Connection created.\n", Thread.currentThread().getName());
    }

    public static DBConnection getConnection() {
        if (connection == null) {
            connection = new DBConnection();
        }
        return connection;
    }

}
