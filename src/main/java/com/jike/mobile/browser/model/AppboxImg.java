package com.jike.mobile.browser.model;

/**
 * AppboxImg entity. @author MyEclipse Persistence Tools
 */

public class AppboxImg implements java.io.Serializable {

	// Fields

	private Integer id;
	private String imgUrl;
	private String originPath;
	private String tinyPath;
	private String middlePath;

	// Constructors

	/** default constructor */
	public AppboxImg() {
	}

	/** full constructor */
	public AppboxImg(String imgUrl, String originPath, String tinyPath,
			String middlePath) {
		this.imgUrl = imgUrl;
		this.originPath = originPath;
		this.tinyPath = tinyPath;
		this.middlePath = middlePath;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOriginPath() {
		return this.originPath;
	}

	public void setOriginPath(String originPath) {
		this.originPath = originPath;
	}

	public String getTinyPath() {
		return this.tinyPath;
	}

	public void setTinyPath(String tinyPath) {
		this.tinyPath = tinyPath;
	}

	public String getMiddlePath() {
		return this.middlePath;
	}

	public void setMiddlePath(String middlePath) {
		this.middlePath = middlePath;
	}

}