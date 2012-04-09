package com.jike.mobile.browser.extension;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jike.mobile.browser.model.Category;
import com.jike.mobile.browser.util.ServerConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class CatAction extends ActionSupport {

	private static final long serialVersionUID = -4423336504706740452L;
	
	// for add, modify, delete
	Category category;
	Integer categoryId;
	
	// for getList
    Integer page;
    List<Category> list;
    
    // for inject
    ExtensionService extensionService;
	
    @InputConfig(resultName=ERROR)
	public String add() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			return INPUT;
		}
		else if(method.equals("POST")) {
			try {
				extensionService.categoryAdd(category);
				addActionMessage(getText("operation.success"));
				return SUCCESS;
			}
			catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
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
				category = extensionService.categoryFindById(categoryId);
				if(category == null) {
					addActionError(getText("record.is.not.exist"));
					return ERROR;
				}
				return INPUT;
			}
			catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
			}
		}
		else if(method.equals("POST")) {
			try {
				extensionService.categoryModify(category);
				addActionMessage(getText("operation.success"));
				return SUCCESS;
			}
			catch (RuntimeException re) {
				addActionError(getText("operation.failed"));
				return ERROR;
			}
		}
		return NONE;
	}
	
    @InputConfig(resultName=ERROR)
	public String delete() {
		try {
			Category category = extensionService.categoryFindById(categoryId);
			if(category == null) {
				addActionError(getText("record.is.not.exist"));
				return ERROR;
			}
			else {
				extensionService.categoryDelete(category);
				addActionMessage(getText("operation.success"));
				return SUCCESS;
			}	
		}
		catch (RuntimeException re) {
			System.out.println(re.getCause().getCause());
			addActionError(getText("operation.failed"));
			return ERROR;
		}
	}
	
    @InputConfig(resultName=ERROR)
	public String list() {
		try {
			Integer length = Integer.parseInt(ServerConfig.get("category_list_page_size"));
			list = extensionService.categoryGetList(page, length);
			return SUCCESS;
		}
		catch (RuntimeException re) {
			addActionError(getText("operation.failed"));
			return ERROR;
		}
	}
	
	public void validateAdd() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("POST")) {
			validateCategoryWithoutId();
		}
	}

	public void validateModify() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) validateCategoryId();
		else if(method.equals("POST")) validateCategory();
	}
	
	public void validateDelete() {
		validateCategoryId();
	}
	
	public void validateList() {
		validatePage();
	}
	
	public void validateCategoryWithoutId() {
		if(category == null) addActionError(getText("input.argus.is.empty"));
		else if(category.getName() == null || category.getName().equals("")){
			addActionError(getText("input.argus.is.empty"));
		}
	}
	
	public void validateCategory() {
		validateCategoryWithoutId();
		if(category.getId() == null || category.getId() <= 0) {
			addActionError(getText("input.argus.is.empty"));
		}
	}
	
	public void validateCategoryId() {
		if(categoryId == null) addActionError(getText("input.argus.is.empty"));
		else if(categoryId <= 0) addActionError(getText("input.argus.is.invalid"));	
	}
	
	public void validatePage() {
		if(page == null || page <= 0) {
			clearErrors();
			page = 1;
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<Category> getList() {
		return list;
	}

	public void setList(List<Category> list) {
		this.list = list;
	}

	public ExtensionService getExtensionService() {
		return extensionService;
	}

	public void setExtensionService(ExtensionService extensionService) {
		this.extensionService = extensionService;
	}
	
	
}



























