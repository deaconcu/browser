package com.jike.mobile.browser.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jike.mobile.browser.sys.ServerConfig;

public class ServerConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0){
		ServerConfig.init(arg0.getServletContext());
	}
}
