package org.example.webbase.pool.connectionPoolImpl;

import org.example.webbase.exception.DaoException;
import org.example.webbase.pool.ConnectionPool;


import java.sql.Connection;

public class ConnectionPoolProxy implements ConnectionPool {
    private final ConnectionPool realConnectionPool;

    public ConnectionPoolProxy() {
        this.realConnectionPool = ConnectionPoolImpl.getInstance();
    }

    @Override
    public Connection getConnection() throws DaoException {
        return realConnectionPool.getConnection();
    }

    @Override
    public void releaseConnection(Connection connection) {
        realConnectionPool.releaseConnection(connection);
    }

    @Override
    public void destroyPool() {
        realConnectionPool.destroyPool();
    }
}
