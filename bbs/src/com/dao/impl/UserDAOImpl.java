package com.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.dao.UserDAO;
import com.po.User;
@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {
	public UserDAOImpl(){
		this.thisClass = User.class;
	}
	
	/*
	 * 根据用户名查找用户
	 * 传入username用户名
	 * 用户名不存在将会抛出异常
	 * (non-Javadoc)
	 * @see com.dao.UserDAO#findByUsername(java.lang.String)
	 */
	public User findByUsername(String username) throws Exception {
		return (User)this.getSession().createQuery("from User where username=?")
				.setString(0, username).list().get(0);
	}

	/*检查用户名是否存在,存在返回true,不存在返回false
	 * (non-Javadoc)
	 * @see com.dao.UserDAO#checkUsername(java.lang.String)
	 */
	public boolean checkUsername(String username) {
		Query query = this.getSession().createQuery("select count(*) from User where username=?");
		query.setString(0, username);
		int flag = ((Number)query.uniqueResult()).intValue();
		if(flag>0)return true;
		else return false;
	}
	
}
