package org.example.webbase.dao.impl;

import org.apache.logging.log4j.core.net.MimeMessageBuilder;
import org.apache.logging.log4j.message.Message;
import org.example.webbase.dao.BaseDao;
import org.example.webbase.dao.UserDao;
import org.example.webbase.entity.User;
import org.example.webbase.exception.DaoException;
import org.example.webbase.pool.ConnectionPool;
import org.example.webbase.pool.EmailSender;
import org.example.webbase.util.codeGenerator;
import org.intellij.lang.annotations.Language;

import javax.mail.Session;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.List;


public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Language("SQL")
    private static final String SELECT_PASSWORD = "SELECT password FROM Users WHERE username = ?";
    @Language("SQL")
    private static final String ADD_USER = "INSERT INTO users (username, password, email, verification_code) VALUES (?, ?, ?, ?)";
    @Language("SQL")
    private static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";
    private static UserDaoImpl instance = new UserDaoImpl();

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
        throw new UnsupportedOperationException("Delete not supported");
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
                match = password.equals(passFromDb);
            }
        } catch (SQLException e) {
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

                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, email);
                statement.setInt(4, verificationCode);

                int newRows = statement.executeUpdate();

                if (newRows > 0) {
                    return true;
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return false;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String username) throws DaoException {
        if (!authenticate(username, oldPassword)) {             //checking old password
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

                return rowsAffected > 0;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public Integer verification(String userMail) throws DaoException {
        EmailSender sendEmail = EmailSender.getInstance();
        int verificationCode = codeGenerator.generate();
        sendEmail.sendEmail(userMail, verificationCode);
        return verificationCode;
    }
}
