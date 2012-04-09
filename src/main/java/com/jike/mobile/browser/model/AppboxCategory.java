package com.jike.mobile.browser.model;

/**
 * AppboxCategory entity. @author MyEclipse Persistence Tools
 */

public class AppboxCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940534534647339608L;
	private Integer id;
	private String name;
	private Integer root;
	private long postTime;

	// Constructors

	/** default constructor */
	public AppboxCategory() {
	}

	/** full constructor */
	public AppboxCategory(String name, Integer root, long postTime) {
		this.name = name;
		this.root = root;
		this.postTime = postTime;
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

	public Integer getRoot() {
		return this.root;
	}

	public void setRoot(Integer root) {
		this.root = root;
	}

	public long getPostTime() {
		return this.postTime;
	}

	public void setPostTime(long postTime) {
		this.postTime = postTime;
	}

}