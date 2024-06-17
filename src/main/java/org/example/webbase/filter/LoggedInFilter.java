package org.example.webbase.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import static org.example.webbase.constant.Constant.MAIN_MENU;
import static org.example.webbase.constant.Constant.USERNAME;


@WebFilter("/login")
public class LoggedInFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session.getAttribute(USERNAME) == null) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(MAIN_MENU);
            dispatcher.forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
