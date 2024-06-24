package org.example.webbase.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;




@WebFilter({"/change_language"})
public class SessionLocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);

        if (req.getParameter("change_language") != null) {
            if(req.getParameter("current_language").equals("en")) {
                session.setAttribute("lang", "ru");
            } else {
                session.setAttribute("lang", "en");
            }
        }

        chain.doFilter(req, response);


    }

    @Override
    public void destroy() {

    }
}
