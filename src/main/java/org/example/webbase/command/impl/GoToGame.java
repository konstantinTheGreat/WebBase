package org.example.webbase.command.impl;

import org.example.webbase.command.Command;
import org.example.webbase.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static org.example.webbase.constant.PagesConstants.MAIN_GAME;

public class GoToGame implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return MAIN_GAME;
    }
}
