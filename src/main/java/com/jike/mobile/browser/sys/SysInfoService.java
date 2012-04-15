package com.jike.mobile.browser.sys;

public interface SysInfoService {
	
	public String get(String key);
	
	public void set(String key, String value);
	
	public void set(String key, String value, boolean persistence);
}
