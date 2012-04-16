package com.jike.mobile.browser.model;

/**
 * BookMark entity. @author MyEclipse Persistence Tools
 */

public class BookMark implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7810471302314418004L;
	// Fields
	
	private Integer id;
	private String userId;
	private String bookMarkFolders;

	// Constructors

	/** default constructor */
	public BookMark() {
	}

	/** minimal constructor */
	public BookMark(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public BookMark(String userId, String bookMarkFolders) {
		this.userId = userId;
		this.bookMarkFolders = bookMarkFolders;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookMarkFolders() {
		return this.bookMarkFolders;
	}

	public void setBookMarkFolders(String bookMarkFolders) {
		this.bookMarkFolders = bookMarkFolders;
	}

}