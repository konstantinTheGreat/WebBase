package org.example.webbase.exception;

import java.sql.SQLException;

public class DaoException extends Exception{
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    public DaoException(SQLException exception) {
        super(exception);
    }
    public DaoException(Throwable cause) {
        super(cause);
    }
}
