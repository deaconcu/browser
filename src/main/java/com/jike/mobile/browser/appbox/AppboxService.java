package com.jike.mobile.browser.appbox;

import java.util.List;

import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;
import com.jike.mobile.browser.model.UploadFile;


public interface AppboxService {
	
	/**
	 * 以下为分类相关service
	 */

	/**
	 * 添加appbox分类
	 * @param appboxCategory
	 * @param imgFile 
	 * @return
	 */
	public Integer addCategory(AppboxCategory appboxCategory, UploadFile imgFile);
	
	/**
	 * 删除分类
	 * @param appboxCategory
	 */
	public void deleteCategory(AppboxCategory appboxCategory);
	
	/**
	 * 更新appbox分类
	 * @param appboxCategory
	 * @param imgFile 
	 */
	public void updateCategory(AppboxCategory appboxCategory, UploadFile imgFile);
	
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
	 * @param img 
	 * @return
	 */
	public Integer addItem(AppboxItem appboxItem, UploadFile img);
	
	public void deleteItem(int appboxItemId);

	public void updateItem(AppboxItem appboxItem, UploadFile imgFile);

	public AppboxItem findItemById(int appboxItemId);

	

	public List<AppboxItem> findItemByPageDesc(int page, int page_size);
	
	public int match(int appboxItemId);
	
	public int match(AppboxItem appboxItem);

	public List<AppboxItem> findItemAll();

	public List<AppboxItem> findItemByIdsAndTime(Integer[] ids, long lastUpdateTime, int statue);
	
	public List<AppboxCategory> findCategoryAllWithItem(Long lastUpdateTime);
	
	/**
	 * set the item to be displayed on the page
	 * @param appboxItemId
	 * @param isDefault
	 */
	public void setItemDefaultById(int appboxItemId, short isDefault);
	
	/**
	 * find storage path of image by imgUrl
	 * @param size
	 * @param imgUrl
	 * @return
	 */
	public String convertImgUrl(int size, String imgUrl);

	

}
