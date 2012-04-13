package com.jike.mobile.browser.extension;

import java.util.List;

import com.jike.mobile.browser.model.Category;
import com.jike.mobile.browser.model.Item;
import com.jike.mobile.browser.model.UploadFile;

public interface ExtensionService {
	
	public static final int SUCCESS = 100;
	public static final int ERROR = 101;
	public static final int NONE = 102;

	public Integer itemAdd(Item item, UploadFile ext, UploadFile icon, UploadFile largeIcon);

	public void itemModify(Item item, UploadFile ext, UploadFile icon, UploadFile largeIcon);
	
	public boolean validateCategory(Item item);
	
	public Item findItemById(int itemId);
	
	public List<Category> findAllCategories();

	public void delete(int itemId);

	public List<Item> getListDesc(Integer page, Integer length);
	
	public Integer categoryAdd(Category category);
	
	public void categoryModify(Category category);
	
	public void categoryDelete(Category category);
	
	public List<Category> categoryGetListDesc(Integer page, Integer length);
	
	public Category categoryFindById(Integer categoryId);

	public List<Item> findItemByCategory(Category category);

}
