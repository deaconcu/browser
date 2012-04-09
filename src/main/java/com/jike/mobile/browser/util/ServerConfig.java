package com.jike.mobile.browser.util;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConfig {
	private static Logger log = LoggerFactory.getLogger(ServerConfig.class);
	
	private static Properties properties = new Properties();

	public static void init(ServletContext sc){
		try {
			InputStream in = sc.getResourceAsStream(sc.getInitParameter("serverConfigLocation")); 
			properties.load(in);

			properties.setProperty("real_root_path", sc.getRealPath("/"));
			log.info("ServerConfig load Success");
		}
		catch (Exception e) {
			log.error("ServerConfig load Error");
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
}
