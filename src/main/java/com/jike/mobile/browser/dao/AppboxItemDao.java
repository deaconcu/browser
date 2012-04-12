package com.jike.mobile.browser.dao;

import java.util.List;

import com.jike.mobile.browser.model.AppboxItem;

public interface AppboxItemDao extends BaseDao<AppboxItem>{
	public List<AppboxItem> findItemByIds(Integer[] ids);
}
