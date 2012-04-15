package com.jike.mobile.browser.sys;

import javax.servlet.ServletContext;

public class ServerConfig {
	
	// 
	public static void init(ServletContext sc){
		
	}
	
	
	private static SysInfoService sysInfoService;
	
	public void setSysInfoService(SysInfoService sysInfoService) {
		ServerConfig.sysInfoService = sysInfoService;
	}
	
	public static String get(String key) {
		return sysInfoService.get(key);
	}
	
}

















