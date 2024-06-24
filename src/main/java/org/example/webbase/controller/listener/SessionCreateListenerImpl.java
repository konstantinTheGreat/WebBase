package org.example.webbase.controller.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {

    static Logger logger = LogManager.getLogger();
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.log(Level.INFO, "-------> sessionCreated: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.log(Level.INFO, "-------> sessionDestroyed: " + se.getSession().getId());
    }

}