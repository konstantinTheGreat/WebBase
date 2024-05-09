package org.example.webbase.controller.listener;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServletContextListenerImpl implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

}