package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.entity.User;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.DaoException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.example.webbase.constant.PagesConstants.*;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession(true);
        try {
            if (userService.authenticate(username, password)) {
                User user = userService.getUserInfo(username);

                session.setAttribute(USER, user);
                session.setAttribute(USERNAME, user.getUsername());
                session.setAttribute(EMAIL, user.getEmail());
                session.setAttribute(STATUS, user.getStatus());
                session.setAttribute(IMAGE_PATH, user.getProfilePic());
                page = MAIN_MENU;
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

}
