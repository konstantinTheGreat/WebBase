package org.example.webbase.command.impl;

import org.example.webbase.command.Command;

import javax.servlet.http.HttpServletRequest;

import static org.example.webbase.constant.PagesConstants.LOGIN_PAGE;

public class DefaultCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return LOGIN_PAGE;
    }
}
