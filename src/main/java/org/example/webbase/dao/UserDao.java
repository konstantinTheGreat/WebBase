package org.example.webbase.dao;

import org.example.webbase.entity.User;
import org.example.webbase.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
    boolean signUp(String login, String password, String email, int verificationCode) throws DaoException;
    boolean changePassword(String oldPassword, String newPassword, String username) throws DaoException;
    Integer verification(String userMail) throws DaoException;
    boolean userExists(String username, String password) throws DaoException;
    boolean deleteUser(String username, String password) throws DaoException;
    boolean uploadFile(String pathName, String username) throws DaoException;
}
