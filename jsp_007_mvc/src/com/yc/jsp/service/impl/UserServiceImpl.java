package com.yc.jsp.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.jsp.dao.UserDao;
import com.yc.jsp.dao.impl.UserDaoImpl;
import com.yc.jsp.entity.UserBean;
import com.yc.jsp.service.UserService;
import com.yc.jsp.util.Encrypt;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}
	
	@Override
	public boolean login(String name, String password) {
		password = Encrypt.md5AndSha(password); //加密操作
		
		try {
			if(userDao.selectUser(name, password) == null){
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addUser(String name, String password, String uploadPath) {
		password = Encrypt.md5AndSha(password);
		if(userDao.insertUser(name, password, uploadPath) == null){
			return false;
		}
		return true;
	}

	@Override
	public UserBean getUserBean(int currPage, int pageSize) {
		int totalPage = userDao.getTotalPage(pageSize);
		if(currPage >=  totalPage){
			currPage = totalPage;
		}
		if(currPage <= 1){
			currPage = 1;
		}
		List<Map<String, Object>> users = userDao.getPartUserInfo(currPage, pageSize);
		return new UserBean(currPage, pageSize, totalPage, users);
	}
}
