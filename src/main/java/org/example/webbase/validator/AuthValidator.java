package org.example.webbase.validator;

import org.example.webbase.exception.DaoException;
import org.example.webbase.exception.ServiceException;

public interface AuthValidator extends BasicValidator<String> {
    boolean isValidEmail(String data) throws ServiceException;
    boolean isValidPassword(String data) throws ServiceException;
    boolean isValidLogin(String username, String password) throws ServiceException;
    boolean isValidUser(String data) throws ServiceException, DaoException;
    boolean isValidUsername(String username) throws ServiceException;
}
