package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dao.MenuDAO;
import com.po.Menu;
import com.po.User;

@Repository("menuDAO")
public class MenuDAOImpl extends BaseDAOImpl<Menu> implements MenuDAO {
	public MenuDAOImpl(){
		this.thisClass = Menu.class;
	}

	/*
	 * 还未完成的一个方法(non-Javadoc)
	 * @see com.dao.MenuDAO#getListByUser(com.po.User)
	 */
	@Override
	public List<Menu> getListByUser(User user) {
		Query query = this.getSession().createQuery("from Menu where ");
		
		// TODO Auto-generated method stub
		return null;
	}

}
