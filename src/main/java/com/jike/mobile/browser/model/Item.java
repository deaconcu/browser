package com.jike.mobile.browser.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * Item entity. @author MyEclipse Persistence Tools
 */

public class Item implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -6430513057379948553L;
	
	private Integer id;
	private String name;
	private String versionName;
	private String version;
	private Integer sizeInByte;
	private String url;
	private String iconUrl;
	private String largeIconUrl;
	private String mainPageUrl;
	private String packageName;
	private String description;
	private Long postTime;
	private Category category;

	// Constructors

	/** default constructor */
	
	public Item() {
		
	}
	
	public Item(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.versionName = item.getVersionName();
		this.version = item.getVersion();
		this.sizeInByte = item.getSizeInByte();
		this.url = item.getUrl();
		this.iconUrl = item.getIconUrl();
		this.largeIconUrl = item.getLargeIconUrl();
		this.mainPageUrl = item.getMainPageUrl();
		this.packageName = item.getPackageName();
		this.description = item.getDescription();
		this.category = item.getCategory();
	}

	// Property accessors

	@JSON(name="Id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JSON(name="Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSON(name="VersionName")
	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@JSON(name="Version")
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@JSON(name="SizeInBytes")
	public Integer getSizeInByte() {
		return this.sizeInByte;
	}

	public void setSizeInByte(Integer sizeInByte) {
		this.sizeInByte = sizeInByte;
	}

	@JSON(name="Url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JSON(name="IconUrl")
	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@JSON(name="LargeIconUrl")
	public String getLargeIconUrl() {
		return this.largeIconUrl;
	}

	public void setLargeIconUrl(String largeIconUrl) {
		this.largeIconUrl = largeIconUrl;
	}

	@JSON(name="MainPageUrl")
	public String getMainPageUrl() {
		return this.mainPageUrl;
	}

	public void setMainPageUrl(String mainPageUrl) {
		this.mainPageUrl = mainPageUrl;
	}

	@JSON(name="PackageName")
	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@JSON(serialize=false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@JSON(name="Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JSON(serialize=false)
	public Long getPostTime() {
		return postTime;
	}

	public void setPostTime(Long postTime) {
		this.postTime = postTime;
	}
	
	//保证持久化后逻辑上不出错的最低标准
	public boolean validate() {
		if(name == null || name.trim().equals("") ) return false;

		return true;
	}
	
	@JSON(serialize=false)
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd / HH:mm");
		return format.format(new Date(postTime));
	}
}
















