package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static org.example.webbase.constant.PagesConstants.CURRENT_PAGE;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return request.getParameter(CURRENT_PAGE);
    }
}
