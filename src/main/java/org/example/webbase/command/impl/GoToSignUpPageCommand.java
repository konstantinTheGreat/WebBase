package org.example.webbase.command.impl;

import org.example.webbase.command.Command;

import javax.servlet.http.HttpServletRequest;

import static org.example.webbase.constant.PagesConstants.SIGNUP_PAGE;

public class GoToSignUpPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return SIGNUP_PAGE;
    }
}
