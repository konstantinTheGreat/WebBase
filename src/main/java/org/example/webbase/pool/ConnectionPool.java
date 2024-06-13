package org.example.webbase.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import static org.example.webbase.constant.Constant.*;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());
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
        dbProp.put(USER, DB_USER);
        dbProp.put(PASSWORD, DB_PASSWORD);

        for (int i = 0; i < EIGHT; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(DB_URL, dbProp);
                free.add(connection);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
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
            logger.log(Level.ERROR, "Thread was interrupted while getting a connection.");
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (used.remove(connection)) {
                try {
                    if (!connection.isClosed() && connection.isValid(2)) {
                        free.put(connection);
                    } else {
                        connection.close();
                    }
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "SQLException occurred while validating or closing the connection: " + e.getMessage());
                }
            } else {
                logger.log(Level.ERROR, "Attempted to release an unknown or already released connection.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.ERROR, "Thread was interrupted while releasing a connection.");
        }
        System.out.println(used);
        System.out.println(free);
    }

    public static void deregisterDriver() {
        try {
            DriverManager.deregisterDriver(DriverManager.getDriver(DB_URL));
        } catch (SQLException e) {
            logger.log(Level.ERROR, "error while deregister driver");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < EIGHT; i++) {
            try {
                Connection connection = free.take();
                connection.close();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, "Exception occurred while closing connections in the pool.");
                throw new RuntimeException(e);
            }
        }
    }
}
