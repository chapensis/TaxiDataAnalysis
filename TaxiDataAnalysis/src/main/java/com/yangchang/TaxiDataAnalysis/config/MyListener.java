package com.yangchang.TaxiDataAnalysis.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("MyListener contextInitialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("MyListener contextDestroyed...");
    }
}
