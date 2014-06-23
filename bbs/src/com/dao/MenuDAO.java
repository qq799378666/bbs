package com.dao;

import java.util.List;

import com.po.Menu;
import com.po.User;

public interface MenuDAO extends BaseDAO<Menu> {
	public List<Menu> getListByUser(User user);
}
