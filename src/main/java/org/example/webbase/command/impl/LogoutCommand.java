package org.example.webbase.command.impl;

import org.example.webbase.command.Command;

import javax.servlet.http.HttpServletRequest;

import static org.example.webbase.constant.Constant.LOGIN_PAGE;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return LOGIN_PAGE;
    }
}
