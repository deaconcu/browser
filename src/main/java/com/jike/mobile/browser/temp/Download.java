package com.jike.mobile.browser.temp;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Download extends ActionSupport{

	private static final long serialVersionUID = 2146635797049736060L;
	
	String file;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public InputStream getTargetFile() {
		//return ServletActionContext.getServletContext().getResourceAsStream("/home/fengjianglin/aa");
		
		return ServletActionContext.getServletContext().getResourceAsStream("/download/" + file);
	}
	
	public String execute() {
		return SUCCESS;
	}
}

