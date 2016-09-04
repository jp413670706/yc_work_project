package com.yc.service.impl;

import com.yc.dao.UserDao;
import com.yc.service.UserService;

public class UserServiceImpl implements UserService, UserDao {
	@Override
	public void find() {
		
		UserDao userDao = new UserServiceImpl();
		userDao.find();
	}
}
