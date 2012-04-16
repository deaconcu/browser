package com.jike.mobile.browser.model;

/**
 * SysInfo entity. @author MyEclipse Persistence Tools
 */

public class SysInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1895241964772725766L;
	private Integer id;
	private long appboxRefreshTime;
	private long extensionRefreshTime;

	// Constructors

	/** default constructor */
	public SysInfo() {
	}

	/** full constructor */
	public SysInfo(long appboxRefreshTime, long extensionRefreshTime) {
		this.appboxRefreshTime = appboxRefreshTime;
		this.extensionRefreshTime = extensionRefreshTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}