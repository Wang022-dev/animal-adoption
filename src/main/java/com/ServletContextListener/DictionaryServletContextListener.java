package com.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DictionaryServletContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(DictionaryServletContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("----------服务器停止----------");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("----------当前答辩版本跳过旧字典初始化监听----------");
    }
}
