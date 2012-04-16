package com.jike.mobile.browser.bookMark;

import com.jike.mobile.browser.model.BookMark;
import com.opensymphony.xwork2.ActionSupport;


public class BookMarkAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 979445081802076936L;

	private BookMarkService bookMarkService;
	
	private String content;

	
	public String upload(){
		try{
			bookMarkService.upload(content);
		    return SUCCESS;
		    
		}
		catch(RuntimeException re){
			re.printStackTrace();
			return ERROR;
		}
	}
	/*
	 public String download(){
	 	
	 }
	 */
	
	
	/**
	 * @return the bookMarkService
	 */
	public BookMarkService getBookMarkService() {
		return bookMarkService;
	}

	/**
	 * @param bookMarkService the bookMarkService to set
	 */
	public void setBookMarkService(BookMarkService bookMarkService) {
		this.bookMarkService = bookMarkService;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

	
	
}
