package org.example.webbase.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.webbase.constant.PagesConstants.MAIN_MENU;


@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("redirectToPrevious") != null) {
            session.removeAttribute("redirectToPrevious");
            RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_MENU);
            dispatcher.forward(request, response);
        }
    }
}