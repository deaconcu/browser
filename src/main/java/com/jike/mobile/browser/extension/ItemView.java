package com.jike.mobile.browser.extension;

import java.net.MalformedURLException;

import javax.servlet.ServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.jike.mobile.browser.model.Item;

public class ItemView extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = 667736618985715115L;
	
	String url = "";
	String iconUrl = "";
	String largeIconUrl = "";
	
	
	/**
	 * 按类返回全部item对象的json格式数据时的页面对象
	 * 
	 * @param item
	 * @throws MalformedURLException 
	 */
	public ItemView(Item item) {
		super(item);
		
		ServletRequest request = ServletActionContext.getRequest();
		String path = ServletActionContext.getServletContext().getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path +"/";
		
		if(!"".equals(item.getUrl()))
			url = basePath + "extension/download.do?itemId=" + item.getId() + "&field=url";
		if(!"".equals(item.getIconUrl()))
			iconUrl = basePath + "extension/download.do?itemId=" + item.getId() + "&field=iconUrl";
		if(!"".equals(item.getLargeIconUrl()))
			largeIconUrl = basePath + "extension/download.do?itemId=" + item.getId() + "&field=largeIconUrl";
	}

	@JSON(name="Url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JSON(name="IconUrl")
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	@JSON(name="LargeUrl")
	public String getLargeIconUrl() {
		return largeIconUrl;
	}

	public void setLargeIconUrl(String largeIconUrl) {
		this.largeIconUrl = largeIconUrl;
	}

	
}
