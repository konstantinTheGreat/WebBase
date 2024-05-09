package org.example.webbase.service;

import org.example.webbase.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean signUp(String login, String password, String email, Integer codeVerification) throws ServiceException;
    boolean changePassword(String oldPassword, String newPassword, String username) throws ServiceException;
    Integer verification(String userEmail) throws ServiceException;

}
