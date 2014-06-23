package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dao.BaseDAO;
import com.dto.PVUtil;
import com.dto.ParamDTO;
import com.share.SystemContext;
import com.vo.PageVO;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
	protected Class thisClass;
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public  Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public void del(T entity) {
		getSession().delete(entity);
	}
	@Override
	public void delToSql(int id){
		this.getSession().createQuery("delete from "+thisClass.getName()+" where id=?")
		.setInteger(0, id).executeUpdate();
	}
	
	@Override
	public T findById(int id) {
		return (T) getSession().load(thisClass, id);
	}

	/*
	 * 获取页对象PageVO
	 * (non-Javadoc)
	 * @see com.dao.BaseDAO#getPageVO(org.hibernate.criterion.DetachedCriteria)
	 */

	@Override
	public PageVO<T> getPageVO(DetachedCriteria dc) {
		Criteria c = dc.getExecutableCriteria(getSession());

		int total =((Number)c.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		c.setProjection(null);
		
		Integer page = SystemContext.getPage();
		Integer pageSize = SystemContext.getPageSize();
		if(page==null){
			page = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		
		c.setFirstResult((page-1)*pageSize);
		c.setMaxResults(pageSize);
		
		List<T> list = (List<T>)c.list();
		PageVO<T> pv = new PageVO<T>();
		pv.setSuccess(true);
		pv.setTotal(total);
		pv.setRows(list);
		pv.setPage(page);
		pv.setPageSize(pageSize);
		return pv;
	}

	@Override
	public List<T> getAll() {
		return (List<T>)this.getSession().createCriteria(thisClass).list();
	}

}
