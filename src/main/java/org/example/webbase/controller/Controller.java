package org.example.webbase.controller;


import org.example.webbase.command.Command;
import org.example.webbase.command.CommandType;
import org.example.webbase.exception.CommandException;


import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static org.example.webbase.constant.PagesConstants.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do", "/login", "/change_language"})
@MultipartConfig
public class Controller extends HttpServlet {
    public void init() {

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        createSessionIdCookie(request, response);
        createLanguageCookie(request, response);
        removeCookies(request, response);

        request.getRequestDispatcher((String)session.getAttribute(PAGE)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String commandStr = request.getParameter(COMMAND);
        Command command = CommandType.define(commandStr);
        String page;

        try {
            page = command.execute(request);
            if (request.getSession(false) == null) {
                request.getRequestDispatcher(page).forward(request, response);;
                return;
            }
            session.setAttribute(PAGE, page);
            response.sendRedirect(CONTROLLER);
        } catch (CommandException e) {
            request.setAttribute(ERROR_MESSAGE, e.getCause());
            request.getRequestDispatcher(ERROR_500_PAGE).forward(request, response);
        }

    }

    private boolean cookieIsPresent(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void createSessionIdCookie(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!cookieIsPresent(request, request.getParameter(SESSION_ID))) {
            Cookie sessionIdCookie = new Cookie(SESSION_ID, session.getId());
            sessionIdCookie.setMaxAge(3600);
            sessionIdCookie.setPath(SLASH);
            response.addCookie(sessionIdCookie);
        }
    }

    private void createLanguageCookie(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (request.getParameter(CHANGE_LANGUAGE) != null) {
            Cookie languageCookie = new Cookie(LANGUAGE, (String)session.getAttribute(LANG));
            languageCookie.setMaxAge(3600);
            languageCookie.setPath(SLASH);
            response.addCookie(languageCookie);
        }
    }
    private void removeCookies(HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter(LOGOUT) != null) {
            Cookie sessionIdCookie = new Cookie(SESSION_ID, EMPTY_STRING);
            Cookie languageCookie = new Cookie(LANGUAGE, EMPTY_STRING);
            sessionIdCookie.setMaxAge(0);
            sessionIdCookie.setPath(SLASH);
            languageCookie.setMaxAge(0);
            languageCookie.setPath(SLASH);
            response.addCookie(sessionIdCookie);
            response.addCookie(languageCookie);
        }
    }
    public void destroy() {

    }
}