package org.example.webbase.dao.impl;

import org.example.webbase.dao.BaseDao;
import org.example.webbase.dao.UserDao;
import org.example.webbase.entity.User;
import org.example.webbase.exception.DaoException;
import org.example.webbase.pool.ConnectionPool;
import org.example.webbase.util.EmailSender;
import org.example.webbase.util.CodeGenerator;
import org.example.webbase.util.PasswordHasher;
import org.intellij.lang.annotations.Language;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.List;

import static org.example.webbase.constant.Constant.*;


public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static final UserDaoImpl instance = new UserDaoImpl();

    private static final PasswordHasher passwordHasher = PasswordHasher.getInstance();

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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setString(1, username);
            int rowsAffected = statement.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
            if(rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e ) {
            throw new DaoException(e);
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

        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String passFromDb;
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
                String salt = resultSet.getString(2);
                String hashedPassword = passwordHasher.hashPassword(password, salt);
                match = hashedPassword.equals(passFromDb);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new DaoException(e);
        }

        return match;
    }

    @Override
    public boolean signUp(String username, String password, String email, int verificationCode) throws DaoException {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            throw new DaoException();
        } else {
            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
                String salt = passwordHasher.generateSalt();
                String hashedPassword = passwordHasher.hashPassword(password, salt);

                statement.setString(1, username);
                statement.setString(2, hashedPassword);
                statement.setString(3, email);
                statement.setInt(4, verificationCode);
                statement.setString(5, salt);

                int newRows = statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
                if (newRows > 0) {
                    return true;
                }
            } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e ) {
                throw new DaoException(e);
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
            try (Connection connection = ConnectionPool.getInstance().getConnection();

                 PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD)) {
                statement.setString(1, newPassword);
                statement.setString(2, username);
                int rowsAffected = statement.executeUpdate();
                ConnectionPool.getInstance().releaseConnection(connection);
                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new DaoException(e);
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
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_USER)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            String usernameFromDB = "";
            String passwordFromDB = "";
            if (resultSet.next()) {
                usernameFromDB = resultSet.getString(1);
                passwordFromDB = resultSet.getString(2);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            if (usernameFromDB.equals(username) && passwordFromDB.equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return false;
    }

    @Override
    public boolean uploadFile(String pathName, String username) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPLOAD_FILE_SQL)){
            statement.setString(1, pathName);
            statement.setString(2, username);
            int rowsAffected = statement.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
            if(rowsAffected <= 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return true;
    }

}
