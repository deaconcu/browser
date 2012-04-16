package com.jike.mobile.browser.dao;

import java.util.List;

import com.jike.mobile.browser.model.AppboxCategory;

public interface AppboxCategoryDao extends BaseDao<AppboxCategory> {

	/**
	 * 查找除了root外的全部目录
	 * @return
	 */
	List<AppboxCategory> findAllWithoutRoot();

	/**
	 * 按页查找除了root外的目录
	 * @param page
	 * @param page_size
	 * @param string
	 * @param b
	 * @return
	 */
	List<AppboxCategory> findByPageWithoutRootOrderByProperty(int page, int page_size, String string, boolean b);

}
