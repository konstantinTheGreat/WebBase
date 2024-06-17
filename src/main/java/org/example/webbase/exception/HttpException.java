package org.example.webbase.exception;

import java.sql.SQLException;

public class HttpException extends Exception{
    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
    public HttpException(SQLException exception) {
        super(exception);
    }
    public HttpException(Throwable cause) {
        super(cause);
    }
}

