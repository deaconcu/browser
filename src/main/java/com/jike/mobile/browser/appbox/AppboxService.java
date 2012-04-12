package com.jike.mobile.browser.appbox;

import java.util.List;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;

public interface AppboxService {

	public Integer addCategory(AppboxCategory appboxCategory);
	
	public AppboxCategory findCategoryById(int appboxCategoryId);

	public void updateCategory(AppboxCategory appboxCategory);

	public List<AppboxCategory> listCategoryByPageDesc(int page, int page_size);

	public void deleteCategory(AppboxCategory appboxCategory);

	public List<AppboxCategory> findCategoryAll();

	public Integer addItem(AppboxItem appboxItem);

	public void updateItem(AppboxItem appboxItem);

	public AppboxItem findItemById(int appboxItemId);

	public void deleteItem(int appboxItemId);

	public List<AppboxItem> findItemByPageDesc(int page, int page_size);
	
	public int match(int appboxItemId);
	
	public int match(AppboxItem appboxItem);

	public List<AppboxItem> findItemAll();

	public List<AppboxItem> findItemByIdsAndTime(Integer[] ids, long lastUpdateTime, int statue);

	public List<AppboxCategory> findCategoryAllWithItem();
	
}
