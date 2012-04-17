package com.jike.mobile.browser.quickStart;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jike.mobile.browser.model.QuickStartIcon;
import com.jike.mobile.browser.model.UploadFile;
import com.jike.mobile.browser.sys.ServerConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class QuickStartIconAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6868072039218812589L;
	
	//
	
	private QuickStartIcon quickStartIcon;
	private Integer iconId;
	
	private File icon;
	private String iconContentType;
	private String iconFileName;
	
	private List<QuickStartIcon> iconList;
	
	private Integer page;
	//inject
	QuickStartService quickStartService;
	
	
	//return
	private String url;
	
	//download
	private String file;
	/**
	 * 上传quickstart icon到服务器,并保存
	 * @return String
	 */
	@InputConfig(resultName=ERROR)
	public String add(){
		if(ServletActionContext.getRequest().getMethod().equals("GET")) {
			return INPUT;
		}
		else if(ServletActionContext.getRequest().getMethod().equals("POST")){
			UploadFile iconFile = new UploadFile(icon, iconContentType, iconFileName);
			try{
				//需要先将上传的url进行parse操作
				iconId = quickStartService.iconAdd(quickStartIcon, iconFile);
			}
			catch(RuntimeException re) {
				addActionError(getText("file.upload.failed")); 
				return ERROR;
			}
			addActionMessage(getText("file.upload.success")); 
			url = "quickStart/get_icon.do?iconId=" + iconId;
			return SUCCESS;
		}
		
		return NONE;
	}

	@InputConfig(resultName=ERROR)
	public String modify(){
		if(ServletActionContext.getRequest().getMethod().equals("GET")){
			quickStartIcon = quickStartService.findIconById(iconId);
		}
		else if(ServletActionContext.getRequest().getMethod().equals("POST")){
			UploadFile iconFile = new UploadFile(icon, iconContentType, iconFileName);
			try{
				quickStartService.iconModify(quickStartIcon, iconFile);
			}
			catch(RuntimeException re){
				addActionError(getText("file.upload.failed"));
				return ERROR;
			}
			addActionMessage(getText("file.upload.success")); 
			url = "quickStart/get_item.do?itemId=" + quickStartIcon.getId();
			return SUCCESS;
		}
		return INPUT;
	}

	@InputConfig(resultName=ERROR)
	public String delete() {
		try {
			quickStartService.delete(iconId);
		}
		catch(RuntimeException re) {
			addActionError(getText("file.delete.failed"));
			return ERROR;
		}
		
		addActionMessage(getText("file.delete.success"));
		url = "quickStart/get_icon_list.do";
		return SUCCESS;
	}
	
	@InputConfig(resultName=ERROR)
	public String detail(){
		try{
			quickStartIcon = quickStartService.findIconById(iconId);
			System.out.println(quickStartIcon.getImgUrl());
			if(quickStartIcon == null) {
				addActionError(getText("icon.can.not.find"));
				return ERROR;
			}
			return SUCCESS;
		}
		catch(RuntimeException re){
			addActionError(getText("input.icon.id.is.wrong"));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String list() {
		try {
			int length = Integer.parseInt(ServerConfig.get("item_list_page_size"));
			iconList = quickStartService.getListDesc(page, length);
		}
		catch (RuntimeException re) {
			addActionError(getText("list.fetch.failed"));
			return ERROR;
		}
		return SUCCESS;
	}
	
	public InputStream getTargetFile() {

	    String fileName = new File(quickStartIcon.getImgUrl()).getName();
		file = fileName.substring(0, fileName.lastIndexOf("_"));
		return ServletActionContext.getServletContext().getResourceAsStream("/" + quickStartIcon.getImgUrl());
	}
	
	@InputConfig(resultName=ERROR)
	public String download() {
		
		try {
			quickStartIcon = quickStartService.findIconById(iconId);
			if(quickStartIcon == null) {
				addActionError(getText("icon.can.not.find"));
				return ERROR;
			}
			return SUCCESS;
		}
		catch (RuntimeException re) {
			addActionError(getText("input.icon.id.is.wrong"));
			return ERROR;
		}	
	}
	
	public void validateAdd(){
		if(ServletActionContext.getRequest().getMethod().equals("POST")) {
			validateIcon();
		}
	}
	
	public void validateModify(){
		if(ServletActionContext.getRequest().getMethod().equals("POST")) {
			validateIcon();
		}
		else if(ServletActionContext.getRequest().getMethod().equals("GET")) {
			validateIconId();
		}
	}
	
	public void validateDelete(){
		validateIconId();
	}
	
	public void validateDetail(){
		validateIconId();
	}
	
	public void validateList(){
		validatePage();
	}
	
	public void validateDownload(){
		validateIconId();
	}
	
	public void validateQuickStartIcon() {
		if(quickStartIcon == null)
			addActionError(getText("input.icon.all.is.empty"));
			
		else {
			if(quickStartIcon.getWebUrl() == null || quickStartIcon.getWebUrl().trim().equals("")){
				addActionError(getText("input.icon.weburl.is.empty"));
			}
		}
	}
	
	public void validateIconId() {
		if(iconId == null){
			addActionError(getText("input.icon.id.is.empty"));	
		}
		else if(iconId <= 0){
			addActionError(getText("input.icon.id.is.minus"));	
		}
	}
	
	//补文件大小和类型检测
	public void validateIcon() {
		if(icon == null){
			addActionError(getText("input.icon.is.empty"));
		}
	}
	
	public void validatePage() {
		if(page == null || page <= 0) page = 1;	
	}
	
	
	/**
	 * @return the icon
	 */
	public File getIcon() {
		return icon;
	}




	/**
	 * @param icon the icon to set
	 */
	public void setIcon(File icon) {
		this.icon = icon;
	}




	/**
	 * @return the iconContentType
	 */
	public String getIconContentType() {
		return iconContentType;
	}




	/**
	 * @param iconContentType the iconContentType to set
	 */
	public void setIconContentType(String iconContentType) {
		this.iconContentType = iconContentType;
	}




	/**
	 * @return the iconFileName
	 */
	public String getIconFileName() {
		return iconFileName;
	}




	/**
	 * @param iconFileName the iconFileName to set
	 */
	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}




	/**
	 * @return the quickStartIcon
	 */
	public QuickStartIcon getQuickStartIcon() {
		return quickStartIcon;
	}




	/**
	 * @param quickStartIcon the quickStartIcon to set
	 */
	public void setQuickStartIcon(QuickStartIcon quickStartIcon) {
		this.quickStartIcon = quickStartIcon;
	}




	/**
	 * @return the iconId
	 */
	public Integer getIconId() {
		return iconId;
	}




	/**
	 * @param iconId the iconId to set
	 */
	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

	/**
	 * @return the iconList
	 */
	public List<QuickStartIcon> getIconList() {
		return iconList;
	}

	/**
	 * @param iconList the iconList to set
	 */
	public void setIconList(List<QuickStartIcon> iconList) {
		this.iconList = iconList;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the quickStartService
	 */
	public QuickStartService getQuickStartService() {
		return quickStartService;
	}

	/**
	 * @param quickStartService the quickStartService to set
	 */
	public void setQuickStartService(QuickStartService quickStartService) {
		this.quickStartService = quickStartService;
	}


}
