package com.jike.mobile.browser.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.jike.mobile.browser.dao.AppboxCategoryDao;
import com.jike.mobile.browser.model.AppboxCategory;

public class AppboxCategoryDaoImpl extends BaseDaoImpl<AppboxCategory> implements AppboxCategoryDao{

	Logger log = LoggerFactory.getLogger(AppboxCategoryDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AppboxCategory> findAllWithoutRoot() {
		try {
			String queryString = "from AppboxCategory as f where f.id > 0";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
	
	@Override
	public List<AppboxCategory> findByPageWithoutRootOrderByProperty(int page, final int page_size, final String propertyName, final boolean isDesc) {
		final int offset = (page - 1) * page_size;
		@SuppressWarnings("unchecked")
		List<AppboxCategory> list = getHibernateTemplate().executeFind(new HibernateCallback<List<AppboxCategory>>() {
			public List<AppboxCategory> doInHibernate(Session session)
			throws HibernateException, SQLException {
				String desc = "";
				if(isDesc) desc = " desc";
				String s = "select f from AppboxCategory as f where f.id > 0 order by " + propertyName + desc;
				Query query = session.createQuery(s);
				query.setFirstResult(offset);
				query.setMaxResults(page_size);
				List<AppboxCategory> list = query.list();
				return list;
			}
		});
		return list;
	}

	
	
}
