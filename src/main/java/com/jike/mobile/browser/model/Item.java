package com.jike.mobile.browser.model;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		this(0, "", "", "", 0, "", "", "", "", "", null);
	}
	
	public Item(int id) {
		this(id, "", "", "", 0, "", "", "", "", "", null);
	}

	/** constructor - without id*/
	public Item(String name, String versionName, String version, Integer sizeInByte, String url, String iconUrl,
			String largeIconUrl, String mainPageUrl, String packageName, Category category) {
		this(null, "", "", "", 0, "", "", "", "", "", null);
	}

	/** full constructor */
	public Item(Integer id, String name, String versionName, String version, Integer sizeInByte, String url, String iconUrl,
			String largeIconUrl, String mainPageUrl, String packageName, Category category) {
		this.id = id;
		this.name = name;
		this.versionName = versionName;
		this.version = version;
		this.sizeInByte = sizeInByte;
		this.url = url;
		this.iconUrl = iconUrl;
		this.largeIconUrl = largeIconUrl;
		this.mainPageUrl = mainPageUrl;
		this.packageName = packageName;
		this.category = category;
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

	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getSizeInByte() {
		return this.sizeInByte;
	}

	public void setSizeInByte(Integer sizeInByte) {
		this.sizeInByte = sizeInByte;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getLargeIconUrl() {
		return this.largeIconUrl;
	}

	public void setLargeIconUrl(String largeIconUrl) {
		this.largeIconUrl = largeIconUrl;
	}

	public String getMainPageUrl() {
		return this.mainPageUrl;
	}

	public void setMainPageUrl(String mainPageUrl) {
		this.mainPageUrl = mainPageUrl;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
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
	
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd / HH:mm");
		return format.format(new Date(postTime));
	}
}
















