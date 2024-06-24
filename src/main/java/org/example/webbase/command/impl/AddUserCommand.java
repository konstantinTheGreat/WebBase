package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;
import static org.example.webbase.constant.PagesConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer userVerificationCode = Integer.valueOf(request.getParameter(VERIFICATION_CODE));
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        Integer verificationCode = (Integer) session.getAttribute(VERIFICATION_CODE);
        String username = (String) session.getAttribute(USERNAME);
        String password = (String) session.getAttribute(PASSWORD);
        String email = (String) session.getAttribute(EMAIL);
        try {
            if (userVerificationCode.equals(verificationCode) && userService.signUp(username, password, email, verificationCode)) {
                page = SUCCESS_PAGE;
            } else {
                request.setAttribute(VERIFICATION_ERROR, INCORRECT_CODE);
                page = VERIFICATION_PAGE;
            }
            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
                throw new CommandException(e);
        }
        return page;
    }
}
