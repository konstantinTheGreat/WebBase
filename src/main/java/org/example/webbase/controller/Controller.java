package org.example.webbase.controller;

import org.example.webbase.command.Command;
import org.example.webbase.command.CommandType;
import org.example.webbase.exception.CommandException;
import org.example.webbase.pool.ConnectionPool;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static org.example.webbase.constants.Constants.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter(COMMAND);
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
            //response.sendRedirect(page);
        } catch (CommandException e) {
            ///response.sendError(500);
            //throw new ServletException(e);
            request.setAttribute(ERROR_MESSAGE, e.getCause());
            request.getRequestDispatcher(ERROR_500_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}