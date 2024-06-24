package org.example.webbase.filter;

import org.example.webbase.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


import static org.example.webbase.constant.PagesConstants.MAIN_MENU;
import static org.example.webbase.constant.PagesConstants.USER;
@WebFilter ("/controller")
public class StatusCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        User user = (User) session.getAttribute(USER);
        if (user != null) {
            if (Objects.equals(user.getStatus(), USER)) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(MAIN_MENU);
                dispatcher.forward(httpRequest, httpResponse);
                return;
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
