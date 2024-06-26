package org.example.webbase.service.impl;

import org.apache.logging.log4j.Level;
import org.example.webbase.dao.impl.UserDaoImpl;
import org.example.webbase.entity.User;
import org.example.webbase.exception.DaoException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.webbase.validator.impl.AuthValidatorImpl;


public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
    private static final UserServiceImpl instance = new UserServiceImpl();


    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        //validate login, password + md5(шифрование)
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "error while authenticating user");
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean signUp(String username, String password, String email, Integer codeVerification) throws ServiceException {
        AuthValidatorImpl validator = AuthValidatorImpl.getInstance();
        validator.isValidEmail(email);
        validator.isValidPassword(password);

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.signUp(username, password, email, codeVerification);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "error while creating user");
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String username) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.changePassword(oldPassword, newPassword, username);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "error while changing password");
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean userDelete(String username, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            return userDao.deleteUser(username, password);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "error while deleting user");
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer verification(String userEmail) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        int verificationCode;
        try {
            verificationCode = userDao.verification(userEmail);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "error while sending verif code");
            throw new ServiceException(e);
        }
        return verificationCode;
    }

    @Override
    public boolean uploadFile(String pathOfFile, String username) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            return userDao.uploadFile(pathOfFile, username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserInfo(String username) throws ServiceException, DaoException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        return userDao.getUserInfo(username);
    }
}
