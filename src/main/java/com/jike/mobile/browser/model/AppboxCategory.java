package com.jike.mobile.browser.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

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
	private Long postTime;
	private Long modifyTime;
	
	
	private Set<AppboxItem> itemList = new HashSet<AppboxItem>();

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
	
	@JSON(serialize=false)
	public Integer getRoot() {
		return this.root;
	}

	public void setRoot(Integer root) {
		this.root = root;
	}

	@JSON(serialize=false)
	public Long getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Long postTime) {
		this.postTime = postTime;
	}
	
	@JSON(serialize=false)
	public String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd / HH:mm");
		return format.format(new Date(postTime));
	}
	
	public Set<AppboxItem> getItemList() {
		return itemList;
	}

	public void setItemList(Set<AppboxItem> itemList) {
		this.itemList = itemList;
	}

	@JSON(serialize=false)
	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	

}