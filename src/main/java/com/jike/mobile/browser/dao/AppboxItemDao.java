package com.jike.mobile.browser.dao;

import java.util.List;

import com.jike.mobile.browser.model.AppboxItem;

public interface AppboxItemDao extends BaseDao<AppboxItem>{
	public List<AppboxItem> findItemByIdsAndTime(Integer[] ids, final long lastUpdateTime, int statue);

	public void updateDefault(int appboxItemId, short isDefault);
}
