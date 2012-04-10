package com.jike.mobile.browser.common.crawler;

public class crawlerException extends Exception {

	private static final long serialVersionUID = 8023433198422490579L;

	public crawlerException() {
		super();
	}
	
	public crawlerException(String s) {
		super(s);
	}
	
	public crawlerException(Throwable cause) {
		super(cause);
	}
	
	public crawlerException(String s, Throwable cause) {
		super(s, cause);
	}
}
