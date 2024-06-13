package org.example.webbase.validator.impl;

import org.example.webbase.exception.DaoException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.pool.ConnectionPool;
import org.example.webbase.validator.AuthValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.example.webbase.constant.Constant.*;

public class AuthValidatorImpl extends BasicValidatorImpl<String> implements AuthValidator {
    private static final AuthValidatorImpl instance = new AuthValidatorImpl();

    private AuthValidatorImpl(){}
    public static AuthValidatorImpl getInstance() {
        return instance;
    }
    @Override
    public boolean isValidEmail(String email) throws ServiceException {
        return true;
    }

    @Override
    public boolean isValidPassword(String password) throws ServiceException {
        return !isEmpty(password) && password.length() >= EIGHT;
    }

    @Override
    public boolean isValidLogin(String username, String password) throws ServiceException {
        return isValidPassword(password) && isValidUsername(username);
    }

    @Override
    public boolean isValidUser(String data) throws ServiceException, DaoException {
        return false;
    }

    @Override
    public boolean isValidUsername(String username) {
        return !isEmpty(username) && username.length() >= EIGHT;
    }


}
