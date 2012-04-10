package com.jike.mobile.browser.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4912078322739759681L;
	
	private Integer id;
	private String name;
	private Long postTime;

	// Constructors

	/** default constructor */
	public Category() {}

	public Category(String name) {
		this.name = name;
	}
	
	public Category(Integer id) {
		this.id = id;
	}
	
	public Category(String name, Integer id) {
		this.name = name;
		this.id = id;
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

	public Long getPostTime() {
		return postTime;
	}

	public void setPostTime(Long postTime) {
		this.postTime = postTime;
	}
	
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd / HH:mm");
		return format.format(new Date(postTime));
	}

}