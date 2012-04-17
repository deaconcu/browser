package com.jike.mobile.browser.appbox;

import java.util.List;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;


public interface AppboxService {
	
	/**
	 * 以下为分类相关service
	 */

	/**
	 * 添加appbox分类
	 * @param appboxCategory
	 * @return
	 */
	public Integer addCategory(AppboxCategory appboxCategory);
	
	/**
	 * 删除分类
	 * @param appboxCategory
	 */
	public void deleteCategory(AppboxCategory appboxCategory);
	
	/**
	 * 更新appbox分类
	 * @param appboxCategory
	 */
	public void updateCategory(AppboxCategory appboxCategory);
	
	/**
	 * 按id查找appbox分类
	 * @param appboxCategoryId
	 * @return
	 */
	public AppboxCategory findCategoryById(int appboxCategoryId);
	
	/**
	 * 查找全部分类
	 * 没有在使用
	 * @return
	 */
	public List<AppboxCategory> findCategoryAll();
	
	/**
	 * 按页倒序查找核定数量的分类
	 * 没有在使用
	 * @param page
	 * @param page_size
	 * @return
	 */
	public List<AppboxCategory> listCategoryByPageDesc(int page, int page_size);
	
	
	/**
	 * 查找除了root类以外的其他类别
	 * @return
	 */
	public List<AppboxCategory> categorySelectAllWithoutRoot();

	/**
	 * 按页倒序查找除了root类以外核定数量的分类
	 * @param page
	 * @param page_size
	 * @return
	 */
	public List<AppboxCategory> listCategoryWithoutRootByPageDesc(int page, int page_size);
	
	
	/**
	 * 以下为Item相关service
	 */
	
	/**
	 * @param appboxItem
	 * @return
	 */
	public Integer addItem(AppboxItem appboxItem);
	
	public void deleteItem(int appboxItemId);

	public void updateItem(AppboxItem appboxItem);

	public AppboxItem findItemById(int appboxItemId);

	

	public List<AppboxItem> findItemByPageDesc(int page, int page_size);
	
	public int match(int appboxItemId);
	
	public int match(AppboxItem appboxItem);

	public List<AppboxItem> findItemAll();

	public List<AppboxItem> findItemByIdsAndTime(Integer[] ids, long lastUpdateTime, int statue);

	public List<AppboxCategory> findCategoryAllWithItem(Long lastUpdateTime);

	

}
