package com.jike.mobile.browser.appbox;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.jike.mobile.browser.common.crawler.CrawlerMatcher;
import com.jike.mobile.browser.common.crawler.crawlerException;
import com.jike.mobile.browser.dao.AppboxCategoryDao;
import com.jike.mobile.browser.dao.AppboxItemDao;
import com.jike.mobile.browser.model.AppboxCategory;
import com.jike.mobile.browser.model.AppboxItem;
import com.jike.mobile.browser.model.UploadFile;
import com.jike.mobile.browser.sys.ServerConfig;
import com.jike.mobile.browser.sys.SysInfoService;
import com.jike.mobile.browser.util.ImageResizer;
import com.jike.mobile.browser.util.ServiceException;

public class AppboxServiceImpl implements AppboxService{
	
	Logger log = LoggerFactory.getLogger(AppboxServiceImpl.class);
	
	// inject
	AppboxCategoryDao appboxCategoryDao;
	AppboxItemDao appboxItemDao;
	
	private SysInfoService sysInfoService;

	// setter & getter
	
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


	public SysInfoService getSysInfoService() {
		return sysInfoService;
	}

	public void setSysInfoService(SysInfoService sysInfoService) {
		this.sysInfoService = sysInfoService;
	}
	
	// category service
	
	@Override
	public Integer addCategory(AppboxCategory appboxCategory, UploadFile file) {
		prepareCategoryForNew(appboxCategory, file);
		return (Integer)appboxCategoryDao.save(appboxCategory);
	}

	@Override
	public AppboxCategory findCategoryById(int appboxCategoryId) {
		if(appboxCategoryId == 0) throw new ServiceException("root_category_is_forbidden");
		return appboxCategoryDao.findById(appboxCategoryId);
	}

	@Override
	public void updateCategory(AppboxCategory appboxCategory, UploadFile uploadFile) {
		if(appboxCategory.getId() == 0) throw new ServiceException("root_category_is_forbidden");
		prepareCategoryForModify(appboxCategory, uploadFile);
		appboxCategoryDao.update(appboxCategory);

	}

	@Override
	public List<AppboxCategory> listCategoryByPageDesc(int page, int page_size) {
		return appboxCategoryDao.findByPageOrderByProperty(page, page_size, "id", true);
	}

	@Override
	public void deleteCategory(AppboxCategory appboxCategory) {
		if(appboxCategory.getId() == 0) throw new ServiceException("root_category_is_forbidden");
		List<AppboxItem> list = appboxItemDao.findByProperty("appboxCategory", appboxCategory);
		if(list.size() > 0) {
			throw new ServiceException("appbox.category.is.not.empty");
		}
		appboxCategoryDao.delete(appboxCategory);
		updateRootTime();
	}

	@Override
	public List<AppboxCategory> findCategoryAll() {
		return appboxCategoryDao.findAllWithoutRoot();
	}
	
	/**
	 * 查询除root外的其他分类，实现方法
	 * 
	 */
	@Override
	public List<AppboxCategory> categorySelectAllWithoutRoot() {
		return appboxCategoryDao.findAllWithoutRoot();
	}
	

	/**
	 * 
	 * 
	 */
	@Override
	public List<AppboxCategory> listCategoryWithoutRootByPageDesc(int page, int page_size) {
		return appboxCategoryDao.findByPageWithoutRootOrderByProperty(page, page_size, "id", true);
	}

	@Override
	public Integer addItem(AppboxItem appboxItem, UploadFile img) {
		AppboxCategory appboxCategory = findCategoryById(appboxItem.getAppboxCategory().getId());
		if(appboxCategory == null) throw new ServiceException("appbox.category.is.not.exist");
		
		appboxItem.setPostTime(System.currentTimeMillis());
		appboxItem.setMatchTime(0L);
		appboxItem.setMatchStatue(-1);
		
		appboxItem.setImg(uploadImg(img));
		
		Integer id = (Integer)appboxItemDao.save(appboxItem);
		updateRootTime();
		return id;
	}

	@Override
	public void updateItem(AppboxItem appboxItem, UploadFile img) {
		AppboxCategory appboxCategory = findCategoryById(appboxItem.getAppboxCategory().getId());
		if(appboxCategory == null) throw new ServiceException("appbox.category.is.not.exist");
		
		if(img != null) appboxItem.setImg(uploadImg(img));
		
		appboxItemDao.update(appboxItem);
		updateRootTime();
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
		updateRootTime();
	}

	@Override
	public List<AppboxItem> findItemByPageDesc(int page, int page_size) {
		return appboxItemDao.findByPageOrderByProperty(page, page_size, "id", true);
	}
	
	@Override
	public List<AppboxItem> findItemAll() {
		return appboxItemDao.findAll();
	}
	
	@Override
	public int match(int appboxItemId) {
		AppboxItem appboxItem = appboxItemDao.findById(appboxItemId);
		if(appboxItem == null) throw new ServiceException("appbox.item.is.not.exist");
		return match(appboxItem);
	}
	
	@Override
	public List<AppboxItem> findItemByIdsAndTime(Integer[] ids, long lastUpdateTime, int statue) {
		return appboxItemDao.findItemByIdsAndTime(ids, lastUpdateTime, statue);
	}

	@Override
	public List<AppboxCategory> findCategoryAllWithItem(Long lastUpdateTime) {
		AppboxCategory appboxCategory = appboxCategoryDao.findById(0); System.out.println(appboxCategory.getModifyTime());
		if(appboxCategory.getModifyTime() < lastUpdateTime) return null;
		return appboxCategoryDao.findAllWithoutRoot();
	}
	


	@Override
	public int match(AppboxItem appboxItem) {
		
		CrawlerMatcher cm = new CrawlerMatcher();
		try {
			cm.setUrl(appboxItem.getSource());
			String[] regexs = {appboxItem.getTitleRegex(), appboxItem.getUrlRegex(), appboxItem.getPicRegex()};
			
			cm.setRegexs(regexs);
			cm.setCharSet(appboxItem.getCharSet());
			cm.execute();
			
			int isUpdate = 0;
			if(cm.getResult()[0] == null || !cm.getResult()[0].equals(appboxItem.getTitle())){
				appboxItem.setTitle(cm.getResult()[0]);
				isUpdate = 1;
			}
			if(cm.getResult()[1] == null || !cm.getResult()[1].equals(appboxItem.getUrl())){
				appboxItem.setUrl(completionUrl(appboxItem.getSource(), cm.getResult()[1]));
				isUpdate = 1;
			}
			if(cm.getResult()[2] == null){
				
			}
			else if(!cm.getResult()[2].equals(appboxItem.getImgUrl())){
				//TODO processing image
				String completeUrl = completionUrl(appboxItem.getSource(), cm.getResult()[2]);
				fileStore(appboxItem, completeUrl);
				isUpdate = 1;
			}
			
			if(isUpdate == 1) {
				appboxItem.setMatchTime(System.currentTimeMillis());
			}
			
			int statue;
			
			if(cm.getResult()[0] == null && cm.getResult()[1] == null && cm.getResult()[2] == null) statue = -1;
			else if(cm.getResult()[0] != null && cm.getResult()[1] != null && cm.getResult()[2] != null) statue = 0;
			else statue = 1;
			
			appboxItem.setMatchStatue(statue);
			
			appboxItemDao.update(appboxItem);
			return statue;
			
		} catch (crawlerException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * store and scale new image, delete old image file
	 * 
	 * @param appboxItem
	 * @param url
	 */
	private void fileStore(AppboxItem appboxItem ,String url){
		//计算文件名前缀
		String fileNamePrefix = url.substring(0, url.length()-3).replaceAll("http://", "").replaceAll("\\.", "").replaceAll("/", "");
		
		// 计算上传路径
		String outputPath = "";
		try {
			Calendar calendar = Calendar.getInstance();
			outputPath = ServerConfig.get("file_save_path") + File.separator
					+ calendar.get(Calendar.YEAR) + File.separator
					+ calendar.get(Calendar.MONTH) + File.separator
					+ calendar.get(Calendar.DAY_OF_MONTH) + File.separator
					+ calendar.get(Calendar.HOUR_OF_DAY) + File.separator;
					File file = new File(ServerConfig.get("real_root_path")
					+ outputPath);
			file.mkdirs();
			log.info(file.toString());
		} catch (Exception e) {
				log.error("can't create output path");
				return;
		}
		
		String filePath = "";
		try {
			filePath = ServerConfig.get("real_root_path") + outputPath + fileNamePrefix;
			//TODO
			
			//TODO delete old image
			File origFile = new File(ServerConfig.get("real_root_path") + appboxItem.getImgUrl()+".jpg");
			//System.out.println(ServerConfig.get("real_root_path") + appboxItem.getImgUrl()+".jpg");
			if(origFile.exists()){
				origFile.delete();
			}
			origFile = new File(ServerConfig.get("real_root_path") +  appboxItem.getImgUrl() + "0.jpg");
			if(origFile.exists()){
				origFile.delete();
			}
			origFile = new File(ServerConfig.get("real_root_path") + appboxItem.getImgUrl() + "1.jpg");
			if(origFile.exists()){
				origFile.delete();
			}
			
			BufferedImage originalImage = ImageIO.read(new URL(url));
			//set image file storage path
			//TODO 
			ImageIO.write(originalImage, "jpg", new File(filePath+".jpg"));
			//start to scale the image
			ImageResizer imageResizer = new ImageResizer();
			imageResizer.setWidth(110);
			imageResizer.setHeight((int)(110*originalImage.getHeight()/originalImage.getWidth()));
			imageResizer.setOriginalImage(originalImage);
			imageResizer.execute();
			BufferedImage resizedImage = imageResizer.getResizedImage();
			ImageIO.write(resizedImage, "jpg", new File(filePath + "0.jpg"));
			
			imageResizer = new ImageResizer();
			imageResizer.setWidth(210);
			imageResizer.setHeight((int)(210*originalImage.getHeight()/originalImage.getWidth()));
			imageResizer.setOriginalImage(originalImage);
			imageResizer.execute(); 
			resizedImage = imageResizer.getResizedImage();
			ImageIO.write(resizedImage, "jpg", new File(filePath + "1.jpg"));
			
			//key step
			appboxItem.setImgUrl(outputPath + fileNamePrefix);
		} catch (IOException e) {
			log.error("fetch image file failed");
			return;
		}
		
		return;
	}
	

	/**
	 * 将相对路径补全为绝对路径
	 * 
	 * @return
	 */
	private String completionUrl(String source, String url) {
		if (url == null || "".equals(url.trim())) {
			return "";
		}
		if (url.startsWith("http://")) {// 已经是绝对路径
			return url;
		}
		if (source == null || "".equals(source.trim())) {
			return url;
		}

		if (source.indexOf("?") != -1) {//去掉源url的参数列表
			source = source.substring(0, source.indexOf("?"));	
		}
		
		// 以下逻辑处理“/”
		if (source.endsWith("/") && url.startsWith("/")) {
			return source.substring(0, source.length() - 1) + url;
		}
		if (!source.endsWith("/") && !url.startsWith("/")) {
			return source + "/" + url;
		}
		return source + url;
	}

	
	/**
	 * 更新顶级类的被更新时间
	 * 
	 */
	private void updateRootTime() {
		try {
			AppboxCategory root = appboxCategoryDao.findById(0);
			root.setModifyTime(System.currentTimeMillis());
			appboxCategoryDao.update(root);
		} catch(RuntimeException re) {
			log.error("root Time update error");
			throw new ServiceException("Internal Exception", re);
		}
	}

	/**
	 * 为新建的category准备数据
	 * 1. 设置提交时间
	 * 2. 设置更新时间
	 * 3. 更新根目录的更新时间
	 * @param appboxCategory
	 */
	private void prepareCategoryForNew(AppboxCategory appboxCategory, UploadFile uploadFile) {
		appboxCategory.setPostTime(System.currentTimeMillis());
		appboxCategory.setModifyTime(System.currentTimeMillis());
		updateRootTime();
		appboxCategory.setImg(uploadImg(uploadFile));
	}
	
	/**
	 * 为修改的category准备数据
	 * 1. 设置更新时间
	 * 2. 更新根目录的更新时间
	 * @param appboxCategory
	 * @param uploadFile 
	 */
	private void prepareCategoryForModify(AppboxCategory appboxCategory, UploadFile uploadFile) {
		appboxCategory.setModifyTime(System.currentTimeMillis());
		updateRootTime();
		if(uploadFile != null) appboxCategory.setImg(uploadImg(uploadFile));
	}
	
	private String uploadImg(UploadFile uploadFile) {
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
			throw new ServiceException("file.upload.failed");
		}
		
		if(uploadFile.validate()) {
			String filePath = "";
			String fileName = uploadFile.getFileName() + "_" + System.currentTimeMillis();
			try {
				uploadFile.upload(ServerConfig.get("real_root_path") + outputPath, fileName);
			} catch (IOException e) {
				log.error("UploadFile upload excute error");
				throw new ServiceException("file.upload.failed");
			}
			filePath = outputPath + fileName;
			return filePath;
		}
		else {
			log.error("uploadFile validate failed");
			throw new ServiceException("file.upload.failed");
		}
	}

	@Override
	public void setItemDefaultById(int appboxItemId, short isDefault) {
		try{
			AppboxItem appboxItem = appboxItemDao.findById(appboxItemId);
			if(appboxItem == null){
				//throw new DataAccessException("input id is wrong");
				throw new RuntimeException("input id is wrong");
			}
			appboxItem.setIsDefault(isDefault);
			appboxItemDao.update(appboxItem);
		}
		catch(DataAccessException dse){
			log.error(dse.toString());
			throw new ServiceException("DataAccessException", dse);
		}
		
	}
}
















