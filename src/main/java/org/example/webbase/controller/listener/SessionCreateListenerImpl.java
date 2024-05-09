package org.example.webbase.controller.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

}