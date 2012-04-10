package com.jike.mobile.browser.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T> {
	
	public Serializable save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public void deleteAll(Collection<T> entities);
	
	public T findById(Serializable id);
	
	public List<T> findByExample(T entity);
	
	public List<T> findByProperty(String propertyName, Object value);
	
	public List<T> findByPage(int page, int length);
	
	public List<T> findAll();

	List<T> findByPageOrderByProperty(int page, int length, String propertyName, boolean isDesc);
	
}