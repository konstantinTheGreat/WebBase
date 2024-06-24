package org.example.webbase.controller.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.webbase.exception.DaoException;
import org.example.webbase.pool.connectionPoolImpl.ConnectionPoolProxy;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    static Logger logger = LogManager.getLogger();
    private final ConnectionPoolProxy connectionPoolProxy = new ConnectionPoolProxy();
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.log(Level.INFO, "---------> contextInitialized :" + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.log(Level.INFO, "---------> contextDestroyed :" + sce.getServletContext().getContextPath());
        connectionPoolProxy.destroyPool();
        connectionPoolProxy.deregisterDriver();
    }

}