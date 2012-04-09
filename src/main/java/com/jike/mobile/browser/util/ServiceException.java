package com.jike.mobile.browser.util;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 328274484977651484L;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String s) {
		super(s);
	}
	
	public ServiceException(String s, Throwable cause) {
		super(s, cause);
	}
}
