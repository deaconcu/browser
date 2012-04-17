package com.jike.mobile.browser.appbox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;
import com.jike.mobile.browser.model.UploadFile;
import com.jike.mobile.browser.sys.ServerConfig;
import com.jike.mobile.browser.util.Message;
import com.jike.mobile.browser.util.ServiceException;
import com.jike.mobile.browser.util.Validate;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class AppboxItemAction extends ActionSupport {
	
	private static final long serialVersionUID = 5447588905866456204L;
	private Logger log = LoggerFactory.getLogger(AppboxItemAction.class);
	
	// add, modify, detail
	private AppboxItem appboxItem;
	private List<AppboxCategory> listCategory;
	
	// modify, delete, detail
	private int appboxItemId;
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	private File img;
	private String imgContentType;
	private String imgFileName;
	
	// json
	private String appboxItemIdString;
	private Integer[] appboxItemIds;
	private Long lastUpdateTime;
	
	// list
	private List<AppboxItem> listItem;
	
	// list
	private int page;
	
	// inject
	private AppboxService appboxService;
	
	// error
	private Message msg = new Message();
	
	// return 
	private String url;
	
	// action methods
	
	public InputStream getItems() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		JSONArray root = new JSONArray();
		for(AppboxItem appboxItem : listItem) {
			JSONObject item = new JSONObject();
			item.put("id", appboxItem.getId());
			item.put("title", appboxItem.getTitle());
			item.put("url", appboxItem.getUrl());
			item.put("imgUrl", appboxItem.getImgUrl());
			root.add(item);
		}
	    byte[] json = root.toString().getBytes();
		return new ByteArrayInputStream(json);
	}

	@InputConfig(resultName=ERROR)
	public String add() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			try {
				listCategory = appboxService.findCategoryAll();
				if(listCategory.size() == 0) {
					addActionError(getText("appbox.create.category.first"));
					return ERROR;
				}
				return INPUT;
			} catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
				return ERROR;
			}
		}
		else if(method.equals("POST")) {
			appboxItem.setPostTime(System.currentTimeMillis());
			try{
				UploadFile imgFile = new UploadFile(img, imgContentType, imgFileName);
				appboxItemId = appboxService.addItem(appboxItem, imgFile);
				addActionMessage(getText("operation.success"));
				url = ServletActionContext.getServletContext().getContextPath() + "/appbox/get_item.do?appboxItemId=" + appboxItemId;
				return SUCCESS;
			} catch (ServiceException se) {
				addActionError(getText(se.getMessage()));
				return ERROR;
			} catch(RuntimeException re) {
				addActionError(getText("operation.failed"));
				return ERROR;
			}
		}
		addActionError(getText("operation.unsupported"));
		return ERROR;
	}
	
	@InputConfig(resultName=ERROR)
	public String modify() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			try {
				listCategory = appboxService.findCategoryAll();
				if(listCategory.size() == 0) {
					addActionError(getText("appbox.category.not.exist"));
					return ERROR;
				}
				appboxItem = appboxService.findItemById(appboxItemId);
				return INPUT;
			} catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
				return ERROR;
			}
		}
		else if(method.equals("POST")) {
			try {
				UploadFile imgFile = null;
				if(img != null) imgFile = new UploadFile(img, imgContentType, imgFileName);
				appboxService.updateItem(appboxItem, imgFile);
				addActionMessage(getText("operation.success"));
				url = ServletActionContext.getServletContext().getContextPath() + "/appbox/get_item.do?appboxItemId=" + appboxItem.getId();
				return SUCCESS;
			} catch (ServiceException se) {
				addActionError(getText(se.getMessage()));
				return ERROR;
			} catch (RuntimeException re) {
				re.printStackTrace();
				addActionError(getText("operation.failed"));
				return ERROR;
			}
		}
		addActionError(getText("operation.unsupported"));
		return ERROR;	
	}
	
	@InputConfig(resultName=ERROR)
	public String delete() {
		try {
			appboxService.deleteItem(appboxItemId);
			addActionMessage(getText("operation.success"));
			url = ServletActionContext.getServletContext().getContextPath() + "/appbox/get_item_list.do";
			return SUCCESS;
		} catch (ServiceException se) {
			addActionError(getText(se.getMessage()));
			return ERROR;
		} catch (RuntimeException re) {
			re.printStackTrace();
			addActionError(getText("operation.failed"));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String list() {
		try {
			listItem = appboxService.findItemByPageDesc(page, Integer.parseInt(ServerConfig.get("appbox_item_list_page_size")));
			return SUCCESS;
		} catch (RuntimeException re) {
			addActionError(getText("operation.failed"));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String detail() {
		try {
			appboxItem = appboxService.findItemById(appboxItemId);
			if(appboxItem == null) {
				addActionError(getText("object.is.not.exist"));
				return ERROR;
			}
			return SUCCESS;
		} catch (RuntimeException re) {
			addActionError(getText("operation.failed"));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String match() {
		try {
			int statue = appboxService.match(appboxItemId);
			if(statue == 0) {
				addActionMessage(getText("match.success"));
				url = ServletActionContext.getServletContext().getContextPath() + "/appbox/get_item.do?appboxItemId=" + appboxItemId;
				return SUCCESS;
			}
			else if(statue == -1) {
				addActionError(getText("match.falied"));
				url = ServletActionContext.getServletContext().getContextPath() + "/appbox/get_item.do?appboxItemId=" + appboxItemId;
				return SUCCESS;
			}
			else {
				addActionMessage(getText("match.partly.success"));
				url = ServletActionContext.getServletContext().getContextPath() + "/appbox/get_item.do?appboxItemId=" + appboxItemId;
				return SUCCESS;
			}
		} catch (RuntimeException re) {
			addActionError(getText(re.getMessage()));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String json() {
		try {
			listItem = appboxService.findItemByIdsAndTime(appboxItemIds, lastUpdateTime, 0);
			return SUCCESS;
		}catch (RuntimeException re) {
			addActionError(getText(re.getMessage()));
			return ERROR;
		}
	}
	
	
	// validate methods
	
	public void validateAdd() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("POST")) {
			if(img == null) addActionError(getText("input.object.is.null"));
			addValidateError(Validate.appboxItemWithoutId(appboxItem, msg));
		}
	}
	
	public void validateModify() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			addValidateError(Validate.id(appboxItemId, msg));
		}
		else if(method.equals("POST")) {
			addValidateError(Validate.appboxItemAll(appboxItem, msg));
		}
	}
	
	public void validateDelete() {
		addValidateError(Validate.id(appboxItemId, msg));
	}
	
	public void validateList() {
		page = Validate.page(page);
		clearErrors();
	}
	
	public void validateDetail() {
		addValidateError(Validate.id(appboxItemId, msg));
	}
	
	public void validateMatch() {
		addValidateError(Validate.id(appboxItemId, msg));
	}
	
	public void validateJson() {
		if(appboxItemIdString == null || "".equals(appboxItemIdString)) {
			addActionError(getText("appbox.input.is.empty"));
			return; 
		}
		if(lastUpdateTime == null ) lastUpdateTime = 0L;
		
		String[] idsInString = appboxItemIdString.split(",");
		List<Integer> idsInList = new ArrayList<Integer>();
		for(String idInString : idsInString) {
			idInString = idInString.trim();
			if(idInString.equals("")) continue;
			try{
				int id = Integer.parseInt(idInString);
				idsInList.add(id);
			} catch (NumberFormatException e) {
				log.info("appboxItemIdString parseInt error: " + idInString);
				addActionError(getText("appbox.input.is.invalid"));
			}
		}
		appboxItemIds = idsInList.toArray(new Integer[0]);
		if(appboxItemIds.length == 0) addActionError(getText("appbox.input.is.invalid"));
	}
	
	public void addValidateError(boolean result) {
		if(result == false) {
			addActionError(getText(msg.getValue())); 
		}
	}
	
	// setter & getter

	public AppboxItem getAppboxItem() {
		return appboxItem;
	}

	public void setAppboxItem(AppboxItem appboxItem) {
		this.appboxItem = appboxItem;
	}

	public List<AppboxCategory> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<AppboxCategory> listCategory) {
		this.listCategory = listCategory;
	}

	public int getAppboxItemId() {
		return appboxItemId;
	}

	public void setAppboxItemId(int appboxItemId) {
		this.appboxItemId = appboxItemId;
	}

	public List<AppboxItem> getListItem() {
		return listItem;
	}

	public void setListItem(List<AppboxItem> listItem) {
		this.listItem = listItem;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public AppboxService getAppboxService() {
		return appboxService;
	}

	public void setAppboxService(AppboxService appboxService) {
		this.appboxService = appboxService;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppboxItemIdString() {
		return appboxItemIdString;
	}

	public void setAppboxItemIdString(String appboxItemIdString) {
		this.appboxItemIdString = appboxItemIdString;
	}

	public Integer[] getAppboxItemIds() {
		return appboxItemIds;
	}

	public void setAppboxItemIds(Integer[] appboxItemIds) {
		this.appboxItemIds = appboxItemIds;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	
}



















