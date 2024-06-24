package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;
import org.example.webbase.validator.impl.AuthValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.example.webbase.constant.PagesConstants.*;

public class SendEmailCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        AuthValidatorImpl loginValidator = AuthValidatorImpl.getInstance(); //todo (change email sending, verif code should be created here)

        String page;
        HttpSession session = request.getSession();
        try {
            Integer verificationCode = userService.verification(email);
            if(verificationCode != 0) {
                session.setAttribute(USERNAME, username);
                session.setAttribute(PASSWORD, password);
                session.setAttribute(EMAIL, email);
                session.setAttribute(VERIFICATION_CODE, verificationCode);
                page = VERIFICATION_PAGE;
            } else {
                request.setAttribute(SIGNUP_ERROR, INCORRECT_EMAIL_MESSAGE);
                page = SIGNUP_PAGE;
            }
            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}