package com.jike.mobile.browser.model;

/**
 * QuickStartIcon entity. @author MyEclipse Persistence Tools
 */

public class QuickStartIcon implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String webUrl;
	private String imgUrl;

	// Constructors

	/** default constructor */
	public QuickStartIcon() {
	}

	/** full constructor */
	public QuickStartIcon(String webUrl, String imgUrl) {
		this.webUrl = webUrl;
		this.imgUrl = imgUrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebUrl() {
		return this.webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}