package com.jike.mobile.browser.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0){
		ServerConfig.init(arg0.getServletContext());
	}
}
