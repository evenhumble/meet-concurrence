package io.hedwig.concurrence.demo.shareobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://localhost/mydatabase";

    private ThreadLocal<Connection> connectionHolder
            = ThreadLocal.withInitial(() -> {
                try {
                    return DriverManager.getConnection(DB_URL);
                } catch (SQLException e) {
                    throw new RuntimeException("Unable to acquire Connection, e");
                }
            });

    public Connection getConnection() {
        return connectionHolder.get();
    }
}