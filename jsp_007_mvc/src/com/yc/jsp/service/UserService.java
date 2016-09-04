package com.yc.jsp.service;

import com.yc.jsp.entity.UserBean;

/**
 * 用户的业务处理层
 * @author jp
 *
 */
public interface UserService {
	
	/**
	 * 登录操作
	 * @param name 用户名
	 * @param password 密码
	 * @return 登录结果
	 */
	boolean login(String name, String password);

	boolean addUser(String name, String password, String uploadPath);

	UserBean getUserBean(int currPage, int pageSize);
}
