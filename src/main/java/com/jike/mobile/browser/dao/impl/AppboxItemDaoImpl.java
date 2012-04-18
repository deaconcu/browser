package com.jike.mobile.browser.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.jike.mobile.browser.dao.AppboxItemDao;
import com.jike.mobile.browser.model.AppboxItem;

public class AppboxItemDaoImpl extends BaseDaoImpl<AppboxItem> implements AppboxItemDao{
	private Logger log = LoggerFactory.getLogger(AppboxItemDaoImpl.class);

	@Override
	public List<AppboxItem> findItemByIdsAndTime(final Integer[] ids, final long lastUpdateTime, final int statue) {
		try {
			@SuppressWarnings("unchecked")
			List<AppboxItem> list = getHibernateTemplate().executeFind(new HibernateCallback<List<AppboxItem>>() {
				public List<AppboxItem> doInHibernate(Session session)
				throws HibernateException, SQLException {
					String s = "from AppboxItem as item where item.id in (";
					for(int i = 0; i < ids.length - 1; i++) s += "?, ";
					s += "?) and matchTime > ? and matchStatue = ?";
					
					Object[] params = new Object[ids.length + 2];
					for(int i = 0; i < ids.length; i++) {
						params[i] = ids[i]; 
					}
					params[ids.length] = lastUpdateTime;
					params[ids.length + 1] = statue;
					return getHibernateTemplate().find(s, (Object[])params);
				}
			});
			return list;
		} catch (RuntimeException re) {
			log.error("find Item By Ids And Time Failed", re);
			throw re;
		}
	}

	@Override
	public void updateDefault(int appboxItemId, short isDefault) {
		// TODO Auto-generated method stub
		
	}
}
