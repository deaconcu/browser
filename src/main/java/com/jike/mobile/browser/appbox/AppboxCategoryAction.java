package com.jike.mobile.browser.appbox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
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

public class AppboxCategoryAction extends ActionSupport{

	private static final long serialVersionUID = -6009038958648343102L;
	
	Logger log = LoggerFactory.getLogger(AppboxCategory.class);
	
	// add, modify, delete
	private AppboxCategory appboxCategory;
	private int appboxCategoryId;
	
	private File img;
	private String imgContentType;
	private String imgFileName;
	
	// list
	private int page;
	private List<AppboxCategory> list;
	
	// json
	private long lastUpdateTime = 0;
	
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
				
				UploadFile imgFile = new UploadFile(img, imgContentType, imgFileName);
				appboxService.addCategory(appboxCategory, imgFile);
				addActionMessage(getText("operation.success"));
				url = ServletActionContext.getServletContext().getContextPath() + "/appbox/list_cat.do";
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
				UploadFile imgFile = null;
				if(img != null) imgFile = new UploadFile(img, imgContentType, imgFileName);
				appboxService.updateCategory(appboxCategory, imgFile);
				addActionMessage(getText("operation.success"));
				url = ServletActionContext.getServletContext().getContextPath() + "/appbox/list_cat.do";
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
			url = ServletActionContext.getServletContext().getContextPath() + "/appbox/list_cat.do";
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
	
	@InputConfig(resultName=ERROR)
	public String jsonAll() {
		try {
			list = appboxService.findCategoryAllWithItem(lastUpdateTime);
		}catch (RuntimeException re) {
			addActionError(getText(re.getMessage()));
			return ERROR;
		}
		return SUCCESS;
	}
	@InputConfig(resultName=ERROR)
	public String jsonDefault(){
		try {
			list = appboxService.findCategoryDefaultWithItem(lastUpdateTime);
		}catch (RuntimeException re) {
			addActionError(getText(re.getMessage()));
			return ERROR;
		}
		return SUCCESS;
	}
	
	public InputStream getRootCategory() {
		if(list == null) return new ByteArrayInputStream("".getBytes());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		JSONArray root = new JSONArray();		
		for(AppboxCategory appboxCategory : list) {
			JSONObject category = new JSONObject();
			category.put("id", appboxCategory.getId());System.out.println(appboxCategory.getId());
			category.put("name", appboxCategory.getName());
			category.put("img", basePath + appboxCategory.getImg());
			JSONArray itemRoot = new JSONArray();
			for(AppboxItem appboxItem : appboxCategory.getItemList()) {
				JSONObject item = new JSONObject();
				item.put("id", appboxItem.getId());
				item.put("name", appboxItem.getName());
				item.put("img", basePath + appboxItem.getImg());
				item.put("desc", appboxItem.getDesc());
				itemRoot.add(item);
			}
			category.put("itemList", itemRoot);
			root.add(category);
		}
		byte[] json = root.toString().getBytes();
		return new ByteArrayInputStream(json);
	}
	
	public InputStream getRootDefaultCategorySuc(){
		if(list == null){
			return new ByteArrayInputStream("".getBytes());
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		JSONArray root = new JSONArray();		
		for(AppboxCategory appboxCategory : list) {
			JSONObject category = new JSONObject();
			category.put("id", appboxCategory.getId());System.out.println(appboxCategory.getId());
			category.put("name", appboxCategory.getName());
			category.put("img", basePath + appboxCategory.getImg());
			JSONArray itemRoot = new JSONArray();
			for(AppboxItem appboxItem : appboxCategory.getItemList()) {
				JSONObject item = new JSONObject();
				item.put("id", appboxItem.getId());
				item.put("name", appboxItem.getName());
				item.put("img", basePath + appboxItem.getImg());
				item.put("desc", appboxItem.getDesc());
				itemRoot.add(item);
			}
			category.put("itemList", itemRoot);
			root.add(category);
		}
		byte[] json = root.toString().getBytes();
		return new ByteArrayInputStream(json);
	}
	
	public InputStream getRootDefaultCategoryFail(){
		return new ByteArrayInputStream("".getBytes());
	}
	
	public void validateAdd() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("POST")) {
			if(img == null) addActionError("input.object.is.null");
			addValidateError(Validate.appboxCategoryWithoutId(appboxCategory, msg));
		}
	}
	
	public void validateModify() {
		String method = ServletActionContext.getRequest().getMethod();
		if(method.equals("GET")) {
			addValidateError(Validate.id(appboxCategoryId, msg));
		}
		else if(method.equals("POST")) {
			if(img == null) addActionError("input.object.is.null");
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

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

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
}
