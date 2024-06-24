package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.example.webbase.constant.PagesConstants.*;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(USERNAME);
        String password = (String) session.getAttribute(PASSWORD);
        try {
            if(userService.userDelete(username, password)) {
                page = SUCCESS_PAGE;
            } else {
                page = PROFILE_PAGE;
            }
            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

}
