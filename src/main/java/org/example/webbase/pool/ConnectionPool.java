package org.example.webbase.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> free = new LinkedBlockingQueue<>(8);
    private BlockingQueue<Connection> used = new LinkedBlockingQueue<>(8);
    private Properties dbProp = new Properties();

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    private ConnectionPool() {
        String dbUrl = "jdbc:postgresql://localhost:5432/WebServer";
        dbProp.put("user", "postgres");
        dbProp.put("password", "gustavofabelo2169");

        for (int i = 0; i < 8; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dbUrl, dbProp);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            free.add(connection);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
                //log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //deregisterDriver

    public void destroyPool() {
        for (int i = 0; i < 8; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
                /// log throw new RuntimeException(e);
                throw new RuntimeException(e);
            }
        }
    }
}
