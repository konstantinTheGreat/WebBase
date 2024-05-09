package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.DaoException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.pool.ConnectionPool;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;
import org.intellij.lang.annotations.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.webbase.constants.Constants.*;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String email;

        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession(true);
        try {
            if (userService.authenticate(login, password)) {
                email = getEmail(login);
                request.setAttribute(USERNAME, login);
                session.setAttribute(USERNAME, login);
                session.setAttribute(EMAIL, email);

                page = PROFILE_PAGE;
            } else {
                request.setAttribute(LOGIN_ERROR, INCORRECT_LOGIN_MESSAGE);
                page = LOGIN_PAGE;
            }
            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException | DaoException e) {
            throw new CommandException(e);
        }
        return page;
    }


    private String getEmail(String username) throws DaoException {
        String email = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_EMAIL_SQL)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                email = resultSet.getString(EMAIL);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return email;
    }
}
