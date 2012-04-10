package com.jike.mobile.browser.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * AppboxItem entity. @author MyEclipse Persistence Tools
 */

public class AppboxItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8514374967831436910L;
	private Integer id;
	private String name;
	private String title;
	private String url;
	private String imgUrl;
	private Long postTime;
	private String source;
	private String charSet;
	private String titleRegex;
	private String urlRegex;
	private String picRegex;
	private AppboxCategory appboxCategory;

	// Constructors

	/** default constructor */
	public AppboxItem() {
	}

	/** minimal constructor */
	public AppboxItem(String name, long postTime, String source,
			String titleRegex, String urlRegex, String picRegex,
			AppboxCategory appboxCategory) {
		this.name = name;
		this.postTime = postTime;
		this.source = source;
		this.titleRegex = titleRegex;
		this.urlRegex = urlRegex;
		this.picRegex = picRegex;
		this.appboxCategory = appboxCategory;
	}

	/** full constructor */
	public AppboxItem(String name, String title, String url, String imgUrl,
			long postTime, String source, String titleRegex, String urlRegex,
			String picRegex, AppboxCategory appboxCategory) {
		this.name = name;
		this.title = title;
		this.url = url;
		this.imgUrl = imgUrl;
		this.postTime = postTime;
		this.source = source;
		this.titleRegex = titleRegex;
		this.urlRegex = urlRegex;
		this.picRegex = picRegex;
		this.appboxCategory = appboxCategory;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Long postTime) {
		this.postTime = postTime;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitleRegex() {
		return this.titleRegex;
	}

	public void setTitleRegex(String titleRegex) {
		this.titleRegex = titleRegex;
	}

	public String getUrlRegex() {
		return this.urlRegex;
	}

	public void setUrlRegex(String urlRegex) {
		this.urlRegex = urlRegex;
	}

	public String getPicRegex() {
		return this.picRegex;
	}

	public void setPicRegex(String picRegex) {
		this.picRegex = picRegex;
	}

	public AppboxCategory getAppboxCategory() {
		return appboxCategory;
	}

	public void setAppboxCategory(AppboxCategory appboxCategory) {
		this.appboxCategory = appboxCategory;
	}
	
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd / HH:mm");
		return format.format(new Date(postTime));
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
}