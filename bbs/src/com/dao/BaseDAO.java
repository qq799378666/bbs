package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.dto.PVUtil;
import com.vo.PageVO;

public interface BaseDAO<T> {
	public void save(T entity);
	public void del(T entity);
	public void delToSql(int id);
	public T findById(int id);
	public void update(T entirty);
	public PageVO<T> getPageVO(DetachedCriteria dc);
	public List<T> getAll();
}
