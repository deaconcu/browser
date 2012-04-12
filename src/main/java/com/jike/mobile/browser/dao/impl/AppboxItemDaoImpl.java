package com.jike.mobile.browser.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.jike.mobile.browser.dao.AppboxItemDao;
import com.jike.mobile.browser.model.AppboxItem;

public class AppboxItemDaoImpl extends BaseDaoImpl<AppboxItem> implements AppboxItemDao{

	@Override
	public List<AppboxItem> findItemByIds(final Integer[] ids) {
		@SuppressWarnings("unchecked")
		List<AppboxItem> list = getHibernateTemplate().executeFind(new HibernateCallback<List<AppboxItem>>() {
			public List<AppboxItem> doInHibernate(Session session)
			throws HibernateException, SQLException {
				String s = "from AppboxItem as item where item.id in (";
				for(int i = 0; i < ids.length - 1; i++) s += "?, ";
				s += "?)";
				
				return getHibernateTemplate().find(s, (Object[])ids);
			}
		});
		return list;
	}
}
