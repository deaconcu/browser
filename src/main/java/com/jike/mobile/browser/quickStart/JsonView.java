package com.jike.mobile.browser.quickStart;

import com.jike.mobile.browser.model.QuickStartIcon;

public class JsonView {
	private String url;
	private String location;
	
	public JsonView(String url, String location){
		this.url = url;
		this.location = location;
	}
	public JsonView(QuickStartIcon quickStartIcon){
		this.url = quickStartIcon.getWebUrl();
		this.location = quickStartIcon.getImgUrl();
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
