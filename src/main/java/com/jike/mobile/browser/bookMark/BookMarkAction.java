package com.jike.mobile.browser.bookMark;

import com.jike.mobile.browser.model.BookMark;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	
	public void validateUpload(){
		
	}
	
	public void validateDownload(){
		
	}
	
	public void validateContent(boolean flag){
		if(content == null){
			addActionError(getText("input.content.is.null"));
		}
		else{
			JSONObject contentJO = JSONObject.fromObject(content);
			String code = contentJO.getString("code");
			if(flag){//true => upload
				if(code.equals("300")){
					//upload
				}
				else{
					//download
					addActionError(getText("operation.conflict"));
				}
			}
			else{
				
			}
		}
	}
}
