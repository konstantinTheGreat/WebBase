package org.example.webbase.service;

import org.example.webbase.entity.User;
import org.example.webbase.exception.DaoException;
import org.example.webbase.exception.ServiceException;

import javax.servlet.http.Part;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean signUp(String login, String password, String email, Integer codeVerification) throws ServiceException;
    boolean changePassword(String oldPassword, String newPassword, String username) throws ServiceException;
    boolean userDelete(String username, String password) throws ServiceException;
    boolean uploadFile(String pathOfFile, String username) throws ServiceException;

    User getUserInfo (String username) throws ServiceException, DaoException;
    Integer verification(String userEmail) throws ServiceException; //change to String

}
