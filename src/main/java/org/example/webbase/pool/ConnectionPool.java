package org.example.webbase.pool;

import org.example.webbase.exception.DaoException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws DaoException;
    void releaseConnection(Connection connection);
    void destroyPool();
}
