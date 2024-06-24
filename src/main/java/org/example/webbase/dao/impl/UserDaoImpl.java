package org.example.webbase.dao.impl;

import org.example.webbase.dao.BaseDao;
import org.example.webbase.dao.UserDao;
import org.example.webbase.entity.User;
import org.example.webbase.exception.DaoException;
import org.example.webbase.pool.connectionPoolImpl.ConnectionPoolProxy;
import org.example.webbase.util.EmailSender;
import org.example.webbase.util.CodeGenerator;
import org.example.webbase.util.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.List;

import static org.example.webbase.constant.PagesConstants.*;
import static org.example.webbase.constant.SqlQueryConstants.*;


public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static final UserDaoImpl instance = new UserDaoImpl();

    private static final PasswordHasher passwordHasher = PasswordHasher.getInstance();

    private static final ConnectionPoolProxy connectionPoolProxy = new ConnectionPoolProxy();
    private UserDaoImpl() {
    }


    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) {
        return false;
    }
    @Override
    public boolean delete(User user) {
        return false;
    }
    @Override
    public boolean deleteUser(String username, String password) throws DaoException {
        Connection connection = connectionPoolProxy.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setString(1, username);
            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e ) {
            throw new DaoException(e);
        } finally {
            connectionPoolProxy.releaseConnection(connection);
        }
        return false;
    }


    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean authenticate(String username, String password) throws DaoException {

        Connection connection = connectionPoolProxy.getConnection();

        boolean match = false;
        try {
             PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String passFromDb;
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
                String salt = resultSet.getString(2);
                String hashedPassword = passwordHasher.hashPassword(password, salt);
                match = hashedPassword.equals(passFromDb);
            }

        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new DaoException(e);
        } finally {
            connectionPoolProxy.releaseConnection(connection);
        }

        return match;
    }

    @Override
    public boolean signUp(String username, String password, String email, int verificationCode) throws DaoException {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            throw new DaoException();
        } else {
            Connection connection = connectionPoolProxy.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(ADD_USER);
                String salt = passwordHasher.generateSalt();
                String hashedPassword = passwordHasher.hashPassword(password, salt);

                statement.setString(1, username);
                statement.setString(2, hashedPassword);
                statement.setString(3, email);
                statement.setInt(4, verificationCode);
                statement.setString(5, salt);
                statement.setString(6, USER);

                int newRows = statement.executeUpdate();
                if (newRows > 0) {
                    return true;
                }
            } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e ) {
                throw new DaoException(e);
            } finally {
                connectionPoolProxy.releaseConnection(connection);
            }

        }
        return false;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String username) throws DaoException {
        if (!authenticate(username, oldPassword)) {
            throw new DaoException();
        }
        if (oldPassword.isEmpty() || newPassword.isEmpty() || username.isEmpty()) {
            throw new DaoException();
        } else {
            Connection connection = connectionPoolProxy.getConnection();
            try {
                String salt = passwordHasher.generateSalt();
                String hashedPassword = passwordHasher.hashPassword(newPassword, salt);
                PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD);
                statement.setString(1, hashedPassword);
                statement.setString(2, salt);
                statement.setString(3, username);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new DaoException(e);
            } finally {
                connectionPoolProxy.releaseConnection(connection);
            }
        }
    }

    @Override
    public Integer verification(String userMail) throws DaoException {
        EmailSender sendEmail = EmailSender.getInstance();
        int verificationCode = CodeGenerator.generate();
        sendEmail.sendEmail(userMail, verificationCode);
        return verificationCode;
    }


    @Override
    public boolean userExists(String username, String password) throws DaoException {
        Connection connection = connectionPoolProxy.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_USER);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String usernameFromDB = "";
            String passwordFromDB = "";
            if (resultSet.next()) {
                usernameFromDB = resultSet.getString(1);
                passwordFromDB = resultSet.getString(2);
            }
            if (usernameFromDB.equals(username) && passwordFromDB.equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPoolProxy.releaseConnection(connection);
        }

        return false;
    }

    @Override
    public boolean uploadFile(String pathName, String username) throws DaoException {
        Connection connection = connectionPoolProxy.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPLOAD_FILE_SQL);
            statement.setString(1, pathName);
            statement.setString(2, username);
            int rowsAffected = statement.executeUpdate();
            if(rowsAffected <= 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPoolProxy.releaseConnection(connection);
        }

        return true;
    }

    @Override
    public User getUserInfo(String username) throws DaoException {
        Connection connection = connectionPoolProxy.getConnection();
        User newUser = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_USER_INFO);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newUser.setUserID(resultSet.getInt(1));
                newUser.setUsername(resultSet.getString(2));
                newUser.setPassword(resultSet.getString(3));
                newUser.setEmail(resultSet.getString(4));
                newUser.setVerification_code(resultSet.getInt(5));
                newUser.setSalt(resultSet.getString(6));
                newUser.setProfilePic(resultSet.getString(7));
                newUser.setStatus(resultSet.getString(8));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPoolProxy.releaseConnection(connection);
        }

        return newUser;

    }


}
