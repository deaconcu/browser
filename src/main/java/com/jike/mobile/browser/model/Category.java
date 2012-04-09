package com.jike.mobile.browser.model;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4912078322739759681L;
	
	private Integer id;
	private String name;

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

}