package com.jike.mobile.browser.extension;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jike.mobile.browser.model.Category;
import com.jike.mobile.browser.model.Item;
import com.jike.mobile.browser.model.UploadFile;
import com.jike.mobile.browser.sys.ServerConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ItemAction extends ActionSupport {

	private static final long serialVersionUID = -8287972012824043673L;
	
	//fields
	
	//add, modify, delete
	private Item item;
	private Integer itemId;
	private List<Category> categories;
	
	private File ext;
	private String extContentType;
	private String extFileName;
	
	private File icon;
	private String iconContentType;
	private String iconFileName;
	
	private File largeIcon;
	private String largeIconContentType;
	private String largeIconFileName;
	
	//getList getJson 
	//get
	private Integer page;
	
	//set
	private List<Item> itemList;

	//inject
	private ExtensionService extensionService;
	
	//return
	private String url;
	
	//download
	private String field, file;
	
	//Methods

	@InputConfig(resultName=ERROR)
	public String add() {
		if(ServletActionContext.getRequest().getMethod().equals("GET")) {
			try {
				categories = extensionService.findAllCategories();
			}
			catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
				return ERROR;
			}
			return INPUT;
		}
		else if(ServletActionContext.getRequest().getMethod().equals("POST")){
			UploadFile extFile = new UploadFile(ext, extContentType, extFileName);
			UploadFile iconFile = new UploadFile(icon, iconContentType, iconFileName);
			
			//值有可能为空
			UploadFile largeIconFile = new UploadFile(largeIcon, largeIconContentType, largeIconFileName);
			try {
				itemId = extensionService.itemAdd(item, extFile, iconFile, largeIconFile);
			}
			catch (RuntimeException re) {
				addActionError(getText("file.upload.failed")); 
				return ERROR;
			}
			addActionMessage(getText("file.upload.success")); 
			url = "get_item.do?itemId=" + itemId;
			return SUCCESS;
		}
		return NONE;
	}
	
	@InputConfig(resultName=ERROR)
	public String modify() {	
		if(ServletActionContext.getRequest().getMethod().equals("GET")) {
			try {
				item = extensionService.findItemById(itemId);
				categories = extensionService.findAllCategories();
			}
			catch (RuntimeException re) {
				addActionError(getText("input.item.id.is.wrong"));
				return ERROR;
			}
			return INPUT;
		}
		else if(ServletActionContext.getRequest().getMethod().equals("POST")){
			//值有可能为空
			UploadFile extFile = new UploadFile(ext, extContentType, extFileName);
			UploadFile iconFile = new UploadFile(icon, iconContentType, iconFileName);
			UploadFile largeIconFile = new UploadFile(largeIcon, largeIconContentType, largeIconFileName);
			try {
				extensionService.itemModify(item, extFile, iconFile, largeIconFile);
			}
			catch (RuntimeException re) {
				addActionError(getText("file.upload.failed"));
				return ERROR;
			}
			addActionMessage(getText("file.upload.success")); 
			url = "get_item.do?itemId=" + item.getId();
			return SUCCESS;
		}
		return NONE;
	}
	
	@InputConfig(resultName=ERROR)
	public String delete() {
		try {
			extensionService.delete(itemId);
		}
		catch(RuntimeException re) {
			addActionError(getText("file.delete.failed")); return ERROR;
		}
		
		addActionMessage(getText("file.delete.success"));
		url = "get_item_list.do";
		return SUCCESS;
	}
	
	@InputConfig(resultName=ERROR)
	public String list() {
		try {
			int length = Integer.parseInt(ServerConfig.get("item_list_page_size"));
			itemList = extensionService.getListDesc(page, length);
		}
		catch (RuntimeException re) {
			addActionError(getText("list.fetch.failed")); return ERROR;
		}
		return SUCCESS;
	}
	
	@InputConfig(resultName=ERROR)
	public String detail() {
		try {
			item = extensionService.findItemById(itemId);
			if(item == null) {
				addActionError(getText("item.can.not.find"));
				return ERROR;
			}
			return SUCCESS;
		}
		catch (RuntimeException re) {
			addActionError(getText("input.item.id.is.wrong"));
			return ERROR;
		}
		
	}
	
	public String json() {
		return NONE;
	}
	
	
	public InputStream getTargetFile() {
		if("url".equals(field)) {
			String fileName = new File(item.getUrl()).getName();
			file = fileName.substring(0, fileName.lastIndexOf("_"));
			return ServletActionContext.getServletContext().getResourceAsStream("/" + item.getUrl());
		}
		if("iconUrl".equals(field)) {
			String fileName = new File(item.getIconUrl()).getName();
			file = fileName.substring(0, fileName.lastIndexOf("_"));
			System.out.println("/" + item.getIconUrl());
			return ServletActionContext.getServletContext().getResourceAsStream("/" + item.getIconUrl());
		}
		else {
			String fileName = new File(item.getLargeIconUrl()).getName();
			file = fileName.substring(0, fileName.lastIndexOf("_"));
			return ServletActionContext.getServletContext().getResourceAsStream("/" + item.getLargeIconUrl());
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String download() {
		
		try {
			item = extensionService.findItemById(itemId);
			if(item == null) {
				addActionError(getText("item.can.not.find"));
				return ERROR;
			}
			return SUCCESS;
		}
		catch (RuntimeException re) {
			addActionError(getText("input.item.id.is.wrong"));
			return ERROR;
		}	
	}

	public void validateAdd() {
		if(ServletActionContext.getRequest().getMethod().equals("POST")) {
			validateItem();
			validateExt();
			validateIcon();
		}
	}
	
	public void validateModify() {
		
		if(ServletActionContext.getRequest().getMethod().equals("POST")) {
			validateItem();
		}
		else if(ServletActionContext.getRequest().getMethod().equals("GET")) {
			validateItemId();
		}
	}
	
	public void validateDelete() {
		validateItemId();
	}
	
	public void validateList() {
		validatePage();
	}
	
	public void validateDetail() {
		validateItemId();
	}
	
	public void validateDownload() {
		if(!"url".equals(field) && !"iconUrl".equals(field) && !"largeIconUrl".equals(field)) {
			addActionError(getText("input.param.is.invalid"));
		}
		validateItemId();
	}
	
	public void validateItem() {
		if(item == null)
			addActionError(getText("input.item.all.is.empty"));
			
		else {
			if(item.getName() == null || item.getName().trim().equals(""))
				addActionError(getText("input.item.name.is.empty"));		
			if(item.getVersionName() == null || item.getVersionName().trim().equals(""))
				addActionError(getText("input.item.versionName.is.empty"));			
			if(item.getVersion() == null || item.getVersion().trim().equals(""))
				addActionError(getText("input.item.version.is.empty"));			
			if(item.getMainPageUrl() == null || item.getMainPageUrl().trim().equals(""))
				addActionError(getText("input.item.mainPageUrl.is.empty"));			
			if(item.getPackageName() == null || item.getPackageName().trim().equals(""))
				addActionError(getText("input.item.PackageName.is.empty"));
			if(item.getCategory() == null || item.getCategory().getId() == null)
				addActionError(getText("input.Category.is.empty"));
			else if(!extensionService.validateCategory(item))
				addActionError(getText("input.Category.is.not.exist"));
		}
	}
	
	public void validateItemId() {
		if(itemId == null) addActionError(getText("input.item.id.is.empty"));	
		else if(itemId <= 0) addActionError(getText("input.item.id.is.minus"));	
	}
	
	//补文件大小和类型检测
	public void validateExt() {
		if(ext == null) addActionError(getText("input.ext.is.empty"));
	}
	
	//补文件大小和类型检测
	public void validateIcon() {
		if(icon == null) addActionError(getText("input.icon.is.empty"));
	}
	
	public void validatePage() {
		if(page == null || page <= 0) page = 1;	
	}
	
	//setter & getter

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public File getExt() {
		return ext;
	}

	public void setExt(File ext) {
		this.ext = ext;
	}

	public String getExtContentType() {
		return extContentType;
	}

	public void setExtContentType(String extContentType) {
		this.extContentType = extContentType;
	}

	public String getExtFileName() {
		return extFileName;
	}

	public void setExtFileName(String extFileName) {
		this.extFileName = extFileName;
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

	public File getLargeIcon() {
		return largeIcon;
	}

	public void setLargeIcon(File largeIcon) {
		this.largeIcon = largeIcon;
	}

	public String getLargeIconContentType() {
		return largeIconContentType;
	}

	public void setLargeIconContentType(String largeIconContentType) {
		this.largeIconContentType = largeIconContentType;
	}

	public String getLargeIconFileName() {
		return largeIconFileName;
	}

	public void setLargeIconFileName(String largeIconFileName) {
		this.largeIconFileName = largeIconFileName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public ExtensionService getExtensionService() {
		return extensionService;
	}

	public void setExtensionService(ExtensionService extensionService) {
		this.extensionService = extensionService;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	
	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
