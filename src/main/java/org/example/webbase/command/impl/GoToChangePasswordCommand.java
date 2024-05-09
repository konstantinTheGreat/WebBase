package org.example.webbase.command.impl;

import org.example.webbase.command.Command;

import javax.servlet.http.HttpServletRequest;

import static org.example.webbase.constants.Constants.CHANGE_PASSWORD_PAGE;

public class GoToChangePasswordCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return CHANGE_PASSWORD_PAGE;
    }
}