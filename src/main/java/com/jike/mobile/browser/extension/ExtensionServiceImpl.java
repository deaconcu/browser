package com.jike.mobile.browser.extension;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.jike.mobile.browser.dao.CategoryDao;
import com.jike.mobile.browser.dao.ItemDao;
import com.jike.mobile.browser.model.Category;
import com.jike.mobile.browser.model.Item;
import com.jike.mobile.browser.model.UploadFile;
import com.jike.mobile.browser.util.ServerConfig;
import com.jike.mobile.browser.util.ServiceException;

public class ExtensionServiceImpl implements ExtensionService{
	
	Logger log = LoggerFactory.getLogger(ExtensionServiceImpl.class);
	
	ItemDao itemDao;
	CategoryDao categoryDao;

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public Integer itemAdd(Item item, UploadFile ext, UploadFile icon, UploadFile largeIcon) {
		if(!prepareAndUpload(item, ext, icon, largeIcon)) throw new ServiceException("fileUploadException");	
		try {
			item.setPostTime(System.currentTimeMillis());
			return (Integer)(itemDao.save(item));
		}
		catch (DataAccessException dse) {
			log.error(dse.toString());
			throw new ServiceException("DataAccessException", dse);
		}
	}

	@Override
	public void itemModify(Item item, UploadFile ext, UploadFile icon, UploadFile largeIcon) {
		if(!prepareAndUpload(item, ext, icon, largeIcon)) throw new ServiceException("fileUploadException");	
		
		try {
			itemDao.update(item);
		}
		catch (DataAccessException dse) {
			log.error(dse.toString());
			throw new ServiceException("DataAccessException", dse);
		}
	}
	
	@Override
	public void delete(int itemId) {
		try {
			Item item = new Item(itemId);
			itemDao.delete(item);
		}
		catch (DataAccessException dse) {
			if(dse.getCause().getClass().getSimpleName().equals("StaleStateException")) throw new ServiceException("cannot find object");
			else {
				log.error(dse.toString());
				throw new ServiceException("DataAccessException", dse);
			}
		}
	}

	@Override
	public List<Item> getListDesc(Integer page, Integer length) {
		return itemDao.findByPageOrderByProperty(page, length, "id", true);
	}
	
	@Override
	public Item findItemById(int itemId) {
		return itemDao.findById(itemId);
	}
	
	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}
	
	@Override
	public boolean validateCategory(Item item) {
		if(categoryDao.findById(item.getCategory().getId()) != null) return true;
		return false;
	}
	
	@Override
	public Integer categoryAdd(Category category) {
		try {
			category.setPostTime(System.currentTimeMillis());
			return (Integer)(categoryDao.save(category));
		}
		catch (DataAccessException dse) {
			throw new ServiceException("DataAccessException", dse);
		}
		
	}

	@Override
	public void categoryModify(Category category) {
		try {
			categoryDao.update(category);
		}
		catch (DataAccessException dse) {
			throw new ServiceException("DataAccessException", dse);
		}
	}

	@Override
	public void categoryDelete(Category category) {
		try {
			List<Item> list = itemDao.findByProperty("category", category);
			if(list.size() > 0) {
				throw new ServiceException("extension.category.is.not.empty");
			}
			categoryDao.delete(category);
		}
		catch (DataAccessException dse) {
			throw new ServiceException("DataAccessException", dse);
		}
	}

	@Override
	public List<Category> categoryGetListDesc(Integer page, Integer length) {
		try {
			return categoryDao.findByPageOrderByProperty(page, length, "id", true);
		}
		catch (DataAccessException dse) {
			throw new ServiceException("DataAccessException", dse);
		}
	}

	@Override
	public Category categoryFindById(Integer categoryId) {
		try {
			return categoryDao.findById(categoryId);
		}
		catch (DataAccessException dse) {
			throw new ServiceException("DataAccessException", dse);
		}
	}
	
	private boolean prepareAndUpload(Item item, UploadFile ext, UploadFile icon, UploadFile largeIcon) {
		//检查item是否合法
		if(!item.validate()) {
			log.error("action check item fields error");
			return false;
		}
		
		//计算上传路径
		String outputPath = "";
		try {
			Calendar calendar = Calendar.getInstance();
			outputPath = ServerConfig.get("file_save_path") + File.separator
					+ calendar.get(Calendar.YEAR) + File.separator
					+ calendar.get(Calendar.MONTH) + File.separator
					+ calendar.get(Calendar.DAY_OF_MONTH) + File.separator
					+ calendar.get(Calendar.HOUR_OF_DAY) + File.separator;
			File file = new File(ServerConfig.get("real_root_path") + outputPath);
			file.mkdirs();
			log.info(file.toString());
		} catch (Exception e) {
			log.error("can't create output path");
			return false;
		}
		
		if(ext.validate()) {
			String filePath = "";
			try {
				ext.upload(ServerConfig.get("real_root_path") + outputPath);
			} catch (IOException e) {
				log.error("upload ext failed");
				return false;
			}
			filePath = outputPath + ext.getFileName();
			item.setUrl(filePath);
			item.setSizeInByte((int)ext.getPath().length());
		}
		
		if(icon.validate()) {
			String filePath = "";
			try {
				icon.upload(ServerConfig.get("real_root_path") + outputPath);
			} catch (IOException e) {
				log.error("upload icon failed");
				return false;
			}
			filePath = outputPath + icon.getFileName();
			item.setIconUrl(filePath);
			item.setSizeInByte((int)icon.getPath().length());
		}
		
		if(largeIcon.validate()) {
			String filePath = "";
			try {
				largeIcon.upload(ServerConfig.get("real_root_path") + outputPath);
			} catch (IOException e) {
				log.error("upload largeIcon failed");
				return false;
			}
			filePath = outputPath + largeIcon.getFileName();
			item.setLargeIconUrl(filePath);
			item.setSizeInByte((int)largeIcon.getPath().length());
		}
		
		return true;
	}

	


	
}
