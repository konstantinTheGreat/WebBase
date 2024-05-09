package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;
import org.example.webbase.exception.ServiceException;
import org.example.webbase.service.UserService;
import org.example.webbase.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Integer userVerificationCode = Integer.valueOf(request.getParameter("verificationCode"));
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        Integer verificationCode = (Integer) session.getAttribute("verificationCode");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String email = (String) session.getAttribute("email");
        try {
                if(userService.signUp(username, password, email, verificationCode) && userVerificationCode.equals(verificationCode)) {
                    page = "pages/auth/success.jsp";
                } else {
                    request.setAttribute("verif_error", "Incorrect code");
                    page = "pages/auth/verification.jsp";
                }
                session.setAttribute("current_page", page);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        return page;
    }
}
