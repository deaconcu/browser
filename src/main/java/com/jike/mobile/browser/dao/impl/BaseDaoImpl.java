package com.jike.mobile.browser.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jike.mobile.browser.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	
	private Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);
	private Class<T> entityClass;
	private String entityClassSimpleName;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = null; 
        try {
        	Type t = getClass().getGenericSuperclass();
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            this.entityClass = (Class<T>)p[0];
            this.entityClassSimpleName = entityClass.getSimpleName();
        }
        catch (Exception e) {
        	log.error("Dao init failed, without type");
        	throw new RuntimeException("Dao init failed, without type");
        }
	}
	
	@Override
	public Serializable save(T entity) {
		try {
			return getHibernateTemplate().save(entity);
		} catch (RuntimeException re) {
			log.error(entity.getClass() + " entity save failed");
			throw re;
		}
	}

	@Override
	public void update(T entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (RuntimeException re) {
			log.error(entity.getClass() + " entity update failed");
			throw re;
		}
	}

	@Override
	public void delete(T entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch (RuntimeException re) {
			log.error(entity.getClass() + " entity delete failed");
			throw re;
		}
		
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		try {
			getHibernateTemplate().deleteAll(entities);
		} catch (RuntimeException re) {
			log.error("objects delete failed");
			throw re;
		}
		
	}
	
	@Override
	public T findById(Serializable id) {
		try {
			T instance = (T) getHibernateTemplate().get(entityClass, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public List<T> findByExample(T entity) {
		try {
			List<T> results = getHibernateTemplate().findByExample(entity);
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from " + entityClassSimpleName + " as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByPage(int page, final int length) {		
		final int offset = (page - 1) * length;
		List<T> list = getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session)
			throws HibernateException, SQLException {
				String s = "select f from " + entityClassSimpleName + " as f";
				Query query = session.createQuery(s);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List<T> list = query.list();
				return list;
			}
		});
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		try {
			String queryString = "from " + entityClassSimpleName;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}

