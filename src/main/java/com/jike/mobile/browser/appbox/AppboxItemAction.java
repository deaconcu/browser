package com.jike.mobile.browser.appbox;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;
import com.jike.mobile.browser.util.Message;
import com.jike.mobile.browser.util.ServerConfig;
import com.jike.mobile.browser.util.ServiceException;
import com.jike.mobile.browser.util.Validate;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class AppboxItemAction extends ActionSupport {
	
	private static final long serialVersionUID = 5447588905866456204L;

	// add, modify, detail
	AppboxItem appboxItem;
	List<AppboxCategory> listCategory;
	
	// modify, delete, detail
	int appboxItemId;
	
	// list
	List<AppboxItem> listItem;
	
	// list
	int page;
	
	// inject
	AppboxService appboxService;
	
	// error
	Message msg = new Message();
	
	// action methods
	
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
				appboxService.addItem(appboxItem);
				addActionMessage(getText("operation.success"));
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
				appboxService.updateItem(appboxItem);
				addActionMessage(getText("operation.success"));
				return SUCCESS;
			} catch (ServiceException se) {
				addActionError(getText(se.getMessage()));
				return ERROR;
			} catch (RuntimeException re) {
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
			listItem = appboxService.findItemByPage(page, Integer.parseInt(ServerConfig.get("appbox_item_list_page_size")));
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
			appboxItem = appboxService.match(appboxItemId, true);
			if(appboxItem.getTitle() != null && appboxItem.getUrl() != null && appboxItem.getImgUrl() != null) {
				addActionMessage(getText("match.success"));
				return SUCCESS;
			}
			if(appboxItem.getTitle() == null && appboxItem.getUrl() == null && appboxItem.getImgUrl() == null) {
				addActionError(getText("match.falied"));
				return ERROR;
			}
			else {
				addActionMessage(getText("match.partly.success"));
				return SUCCESS;
			}
		} catch (RuntimeException re) {
			addActionError(getText(re.getMessage()));
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String json() {
		addActionError(getText("operation.unsupported"));
		return ERROR;
	}
	
	// validate methods
	
	public void validateAdd() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("POST")) {
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
}



















