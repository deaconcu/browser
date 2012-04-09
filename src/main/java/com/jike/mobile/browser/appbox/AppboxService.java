package com.jike.mobile.browser.appbox;

import java.util.List;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;

public interface AppboxService {

	public void addCategory(AppboxCategory appboxCategory);
	
	public AppboxCategory findCategoryById(int appboxCategoryId);

	public void updateCategory(AppboxCategory appboxCategory);

	public List<AppboxCategory> listCategoryByPage(int page, int page_size);

	public void deleteCategory(AppboxCategory appboxCategory);

	public List<AppboxCategory> findCategoryAll();

	public void addItem(AppboxItem appboxItem);

	public void updateItem(AppboxItem appboxItem);

	public AppboxItem findItemById(int appboxItemId);

	public void deleteItem(int appboxItemId);

	public List<AppboxItem> findItemByPage(int page, int page_size);
	
	public AppboxItem match(int appboxItemId, boolean check);

	public List<AppboxItem> findItemAll();
	
}
