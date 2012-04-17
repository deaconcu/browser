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
	
	private String webUrl;
	
	//inject
	QuickStartService quickStartService;
	
	
	//return
	private String url;
	
	//json
	private JsonView jsonView;
	
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
	
	public String json(){
		try{
			quickStartIcon = quickStartService.findIconByWebUrl(webUrl);
			if(quickStartIcon == null){
				addActionError("input.icon.weburl.not.exists");
				return ERROR;
			}
			else{
				jsonView = new JsonView(quickStartIcon);
				return SUCCESS;
			}
		}
		catch(RuntimeException re){
			addActionError(getText("input.icon.weburl.is.wrong"));
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

	public QuickStartIcon getQuickStartIcon() {
		return quickStartIcon;
	}

	public void setQuickStartIcon(QuickStartIcon quickStartIcon) {
		this.quickStartIcon = quickStartIcon;
	}

	public Integer getIconId() {
		return iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

	public File getIcon() {
		return icon;
	}

	public void setIcon(File icon) {
		this.icon = icon;
	}

	public String getIconContentType() {
		return iconContentType;
	}

	public void setIconContentType(String iconContentType) {
		this.iconContentType = iconContentType;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public List<QuickStartIcon> getIconList() {
		return iconList;
	}

	public void setIconList(List<QuickStartIcon> iconList) {
		this.iconList = iconList;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public QuickStartService getQuickStartService() {
		return quickStartService;
	}

	public void setQuickStartService(QuickStartService quickStartService) {
		this.quickStartService = quickStartService;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public JsonView getJsonView() {
		return jsonView;
	}

	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	
}
