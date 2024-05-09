package org.example.webbase.command;

import org.example.webbase.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
    default void refresh(){}
}

