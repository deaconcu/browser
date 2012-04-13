package com.jike.mobile.browser.quickStart;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.jike.mobile.browser.dao.QuickStartIconDao;
import com.jike.mobile.browser.model.QuickStartIcon;
import com.jike.mobile.browser.model.UploadFile;
import com.jike.mobile.browser.util.ServerConfig;
import com.jike.mobile.browser.util.ServiceException;

public class QuickStartServiceImpl implements QuickStartService {
	Logger log = LoggerFactory.getLogger(QuickStartServiceImpl.class);

	QuickStartIconDao quickStartIconDao;

	@Override
	public QuickStartIcon findIconById(Integer iconId) {
		return quickStartIconDao.findById(iconId);
	}
	
	
	
	@Override
	public Integer iconAdd(QuickStartIcon quickStartIcon, UploadFile iconFile) {
		if (!prepareAndUpload(quickStartIcon, iconFile)) {
			throw new ServiceException("fileUploadException");
		}
		try {
			//将输入的url进行parse
			parseUrl(quickStartIcon);
			return (Integer) (quickStartIconDao.save(quickStartIcon));
		} catch (DataAccessException dse) {
			log.error(dse.toString());
			throw new ServiceException("DataAccessException", dse);
		}
	}
	
	
	
	
	@Override
	public void iconModify(QuickStartIcon quickStartIcon, UploadFile iconFile) {
		if (!prepareAndUpload(quickStartIcon, iconFile)) {
			throw new ServiceException("fileUploadException");
		}
		try {
			//将输入的url进行parse
			parseUrl(quickStartIcon);
			quickStartIconDao.update(quickStartIcon);
		} catch (DataAccessException dse) {
			log.error(dse.toString());
			throw new ServiceException("DataAccessException", dse);
		}
	}



	/**
	 * 将mobile.jike.com/xxxxx转换成jike.com
	 * 将mobile.jike.com.cn/xxxx转换成jike.com.cn
	 * @param quickStartIcon
	 */
	private void parseUrl(QuickStartIcon quickStartIcon) {
		String url = quickStartIcon.getWebUrl().trim();
		String domain = url.replaceAll("(\\S+)(/\\S*)", "$1");
		String[] domains = domain.split("\\.");
		int length = domain.length();
		if (length > 2) {
			if (domains[length - 1].equals("cn")) {
				quickStartIcon.setWebUrl(domains[length - 3] + "."
						+ domains[length - 2] + "." + domains[length - 1]);
			} else {
				quickStartIcon.setWebUrl(domains[length - 2] + "."
						+ domains[length - 1]);
			}
		} else {
			quickStartIcon.setWebUrl(domain);
		}
	}

	/**
	 * Upload icon file to web server and store url into quickStartIcon
	 * 
	 * @param quickStartIcon
	 * @param icon
	 * @return
	 */
	private boolean prepareAndUpload(QuickStartIcon quickStartIcon,
			UploadFile icon) {
		// TODO 这里的上传文件的路径 以后需要修改,都是本地地址

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
			return false;
		}

		if (icon.validate()) {
			String filePath = "";
			try {
				icon.upload(ServerConfig.get("real_root_path") + outputPath);
			} catch (IOException e) {
				log.error("upload icon failed");
				return false;
			}
			filePath = outputPath + icon.getFileName();
			// 需要将文件存放路径记录下来
			quickStartIcon.setWebUrl(filePath);
		}

		return true;
	}

	/**
	 * @return the quickStartIconDao
	 */
	public QuickStartIconDao getQuickStartIconDao() {
		return quickStartIconDao;
	}

	/**
	 * @param quickStartIconDao
	 *            the quickStartIconDao to set
	 */
	public void setQuickStartIconDao(QuickStartIconDao quickStartIconDao) {
		this.quickStartIconDao = quickStartIconDao;
	}
	

}
