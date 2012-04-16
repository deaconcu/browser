package com.jike.mobile.browser.appbox;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.sys.ServerConfig;
import com.jike.mobile.browser.util.Message;
import com.jike.mobile.browser.util.ServiceException;
import com.jike.mobile.browser.util.Validate;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class AppboxCategoryAction extends ActionSupport{

	private static final long serialVersionUID = -6009038958648343102L;
	
	Logger log = LoggerFactory.getLogger(AppboxCategory.class);
	
	// add, modify, delete
	private AppboxCategory appboxCategory;
	private int appboxCategoryId;
	
	// list
	private int page;
	private List<AppboxCategory> list;
	
	// inject
	private AppboxService appboxService;
	
	// validate
	private Message msg = new Message();
	
	// return 
	private String url;

	@InputConfig(resultName=ERROR)
	public String add() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			return INPUT;
		}
		else if(method.equals("POST")) {
			try {
				appboxCategory.setRoot(0);
				appboxCategory.setPostTime(System.currentTimeMillis());
				appboxService.addCategory(appboxCategory);
				addActionMessage(getText("operation.success"));
				url = "list_cat.do";
				return SUCCESS;
			}
			catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
				//log.error(re.toString());
				re.printStackTrace();
				return ERROR;
			}
		}
		return NONE;
	}
	
	@InputConfig(resultName=ERROR)
	public String modify() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			try {
				appboxCategory = appboxService.findCategoryById(appboxCategoryId);
				return INPUT;
			} catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
				log.error(re.toString());
				return ERROR;
			}
		}
		else if(method.equals("POST")) {
			try {
				appboxService.updateCategory(appboxCategory);
				addActionMessage(getText("operation.success"));
				url = "list_cat.do";
				return SUCCESS;
			} catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
				log.error(re.toString());
				return ERROR;
			}
		}
		return NONE;
	}
	
	@InputConfig(resultName=ERROR)
	public String list() {
		int page_size = Integer.parseInt(ServerConfig.get("appbox_category_list_page_size"));
		try {
			list = appboxService.listCategoryWithoutRootByPageDesc(page, page_size);
			return SUCCESS;
		} catch (RuntimeException re) {
			addActionError(getText("operation.failed"));
			log.error(re.toString());
			return ERROR;
		}
	}
	
	@InputConfig(resultName=ERROR)
	public String delete() {
		try {
			appboxCategory = appboxService.findCategoryById(appboxCategoryId);
			if(appboxCategory == null) {
				addActionError(getText("object.is.not.exist"));
				return ERROR;
			}
			appboxService.deleteCategory(appboxCategory);
			addActionMessage(getText("operation.success"));
			url = "list_cat.do";
			return SUCCESS;
		}catch(ServiceException se) {
			addActionError(getText(se.getMessage()));
			return ERROR;
		}
		catch (RuntimeException re) {
			addActionError(getText("operation.failed"));
			log.error(re.toString());
			return ERROR;
		}
	}
	
	public String jsonAll() {
		try {
			list = appboxService.findCategoryAllWithItem();
		}catch (RuntimeException re) {
			addActionError(getText(re.getMessage()));
			return ERROR;
		}
		return SUCCESS;
	}
	
	public void validateAdd() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("POST")) {
			addValidateError(Validate.appboxCategoryWithoutId(appboxCategory, msg));
		}
	}
	
	public void validateModify() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			addValidateError(Validate.id(appboxCategoryId, msg));
		}
		else if(method.equals("POST")) {
			addValidateError(Validate.appboxCategoryAll(appboxCategory, msg));
		}
	}
	
	public void validateList() {
		page = Validate.page(page);
		clearErrors();
	}
	
	public void validateDelete() {
		addValidateError(Validate.id(appboxCategoryId, msg));
	}
	
	public void addValidateError(boolean result) {
		if(result == false) {
			addActionError(getText(msg.getValue())); 
		}
	}

	public AppboxCategory getAppboxCategory() {
		return appboxCategory;
	}

	public void setAppboxCategory(AppboxCategory appboxCategory) {
		this.appboxCategory = appboxCategory;
	}

	public int getAppboxCategoryId() {
		return appboxCategoryId;
	}

	public void setAppboxCategoryId(int appboxCategoryId) {
		this.appboxCategoryId = appboxCategoryId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<AppboxCategory> getList() {
		return list;
	}

	public void setList(List<AppboxCategory> list) {
		this.list = list;
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
}
