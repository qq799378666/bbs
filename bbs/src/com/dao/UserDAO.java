package com.dao;

import com.po.User;

public interface UserDAO extends BaseDAO<User> {
	User findByUsername(String username)throws Exception;
	boolean checkUsername(String username);
}
