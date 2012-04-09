package com.jike.mobile.browser.appbox;

import java.util.List;

import com.jike.mobile.browser.commom.crawler.CrawlerMatcher;
import com.jike.mobile.browser.commom.crawler.crawlerException;
import com.jike.mobile.browser.dao.AppboxCategoryDao;
import com.jike.mobile.browser.dao.AppboxItemDao;
import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;
import com.jike.mobile.browser.util.ServiceException;

public class AppboxServiceImpl implements AppboxService{
	
	// inject
	AppboxCategoryDao appboxCategoryDao;
	AppboxItemDao appboxItemDao;

	public AppboxItemDao getAppboxItemDao() {
		return appboxItemDao;
	}

	public void setAppboxItemDao(AppboxItemDao appboxItemDao) {
		this.appboxItemDao = appboxItemDao;
	}

	public AppboxCategoryDao getAppboxCategoryDao() {
		return appboxCategoryDao;
	}

	public void setAppboxCategoryDao(AppboxCategoryDao appboxCategoryDao) {
		this.appboxCategoryDao = appboxCategoryDao;
	}

	@Override
	public void addCategory(AppboxCategory appboxCategory) {
		appboxCategoryDao.save(appboxCategory);	
	}

	@Override
	public AppboxCategory findCategoryById(int appboxCategoryId) {
		return appboxCategoryDao.findById(appboxCategoryId);
	}

	@Override
	public void updateCategory(AppboxCategory appboxCategory) {
		appboxCategoryDao.update(appboxCategory);	
	}

	@Override
	public List<AppboxCategory> listCategoryByPage(int page, int page_size) {
		return appboxCategoryDao.findByPage(page, page_size);
	}

	@Override
	public void deleteCategory(AppboxCategory appboxCategory) {
		appboxCategoryDao.delete(appboxCategory);
	}

	@Override
	public List<AppboxCategory> findCategoryAll() {
		return appboxCategoryDao.findAll();
	}

	@Override
	public void addItem(AppboxItem appboxItem) {
		AppboxCategory appboxCategory = findCategoryById(appboxItem.getAppboxCategory().getId());
		if(appboxCategory == null) throw new ServiceException("appbox.category.is.not.exist");
		appboxItemDao.save(appboxItem);
	}

	@Override
	public void updateItem(AppboxItem appboxItem) {
		AppboxCategory appboxCategory = findCategoryById(appboxItem.getAppboxCategory().getId());
		if(appboxCategory == null) throw new ServiceException("appbox.category.is.not.exist");
		appboxItemDao.update(appboxItem);
	}

	@Override
	public AppboxItem findItemById(int appboxItemId) {
		return appboxItemDao.findById(appboxItemId);
	}

	@Override
	public void deleteItem(int appboxItemId) {
		AppboxItem appboxItem = appboxItemDao.findById(appboxItemId);
		if(appboxItem == null) throw new ServiceException("appbox.item.is.not.exist");
		appboxItemDao.delete(appboxItem);
	}

	@Override
	public List<AppboxItem> findItemByPage(int page, int page_size) {
		return appboxItemDao.findByPage(page, page_size);
	}
	
	@Override
	public List<AppboxItem> findItemAll() {
		return appboxItemDao.findAll();
	}
	
	public AppboxItem match(int appboxItemId, boolean check) {
		AppboxItem appboxItem = null;
		if(check) {
			appboxItem = appboxItemDao.findById(appboxItemId);
			if(appboxItem == null) throw new ServiceException("appbox.item.is.not.exist");
		}

		CrawlerMatcher cm = new CrawlerMatcher();
		try {
			cm.setUrl(appboxItem.getSource());
			String[] regexs = {appboxItem.getTitleRegex(), appboxItem.getUrlRegex(), appboxItem.getPicRegex()};
			
			cm.setRegexs(regexs);
			cm.execute();
			
			appboxItem.setTitle((cm.getResult())[0]);
			appboxItem.setUrl((cm.getResult())[1]);
			appboxItem.setImgUrl((cm.getResult())[2]);
			
			appboxItemDao.update(appboxItem);
			return appboxItem;
		} catch (crawlerException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	
}
















