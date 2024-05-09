package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;
import org.example.webbase.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.example.webbase.constants.Constants.*;

public class ChangePasswordCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String oldPassword = request.getParameter(OLD_PASSWORD);
        String newPassword = request.getParameter(NEW_PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute(USERNAME);
            try {
                if (userService.changePassword(oldPassword, newPassword, username)) {
                    page = SUCCESS_PAGE;
                } else {
                    request.setAttribute(LOGIN_ERROR, INCORRECT_LOGIN_MESSAGE);
                    page = CHANGE_PASSWORD_PAGE;
                }
                session.setAttribute(CURRENT_PAGE, page);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return page;
    }

}
