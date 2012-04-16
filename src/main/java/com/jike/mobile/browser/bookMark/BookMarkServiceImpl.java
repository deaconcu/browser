package com.jike.mobile.browser.bookMark;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.jike.mobile.browser.dao.BookMarkDao;
import com.jike.mobile.browser.model.BookMark;
import com.jike.mobile.browser.util.ServiceException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BookMarkServiceImpl implements BookMarkService {
	Logger log = LoggerFactory.getLogger(BookMarkServiceImpl.class);

	BookMarkDao bookMarkDao;

	@Override
	public void upload(String content) {
		// TODO Auto-generated method stub
		try {
			JSONObject contentJO = JSONObject.fromObject(content);
			// 取出用户id/密码等操作
			String userId = contentJO.getString("userId");
			String pass = contentJO.getString("password");
			Integer code = Integer.valueOf(contentJO.getString("code"));
			if(code == null){
				throw new Exception("code not fit");
			}
			// 这里的从jsonarray转换成string没有进行测试
			// TODO
			String bookMarkFolders = contentJO.getJSONArray("bookMarkFolders")
					.toString();

			BookMark bookMark = new BookMark();
			bookMark.setUserId(userId);
			bookMark.setBookMarkFolders(bookMarkFolders);

			// TODO 区分是第一次上传还是第二次上传
			firstUpload(userId);
		} catch (Exception e) {
			log.error(e.toString());
			throw new ServiceException("", e);
		}
	}

	private boolean firstUpload(String userId) {
		List<BookMark> bookMarkList = bookMarkDao.findByProperty("userId",
				userId);
		if (bookMarkList.isEmpty())
			return true;
		else
			return false;
	}

	private boolean add(BookMark bookMark) {
		try {
			// TODO 怎样判断插入数据成功
			bookMarkDao.save(bookMark);
			return true;
		} catch (DataAccessException dse) {
			log.error(dse.toString());
			throw new ServiceException("DataAccessException", dse);
		}
	}

	private boolean update(BookMark bookMark) {
		try {
			bookMarkDao.update(bookMark);
			return true;
		} catch (DataAccessException dse) {
			log.error(dse.toString());
			throw new ServiceException("DataAccessException", dse);
		}
	}

	/**
	 * @return the bookMarkDao
	 */
	public BookMarkDao getBookMarkDao() {
		return bookMarkDao;
	}

	/**
	 * @param bookMarkDao
	 *            the bookMarkDao to set
	 */
	public void setBookMarkDao(BookMarkDao bookMarkDao) {
		this.bookMarkDao = bookMarkDao;
	}

}
