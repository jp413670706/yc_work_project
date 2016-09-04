package com.yc.jsp.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {
	Map<String, Object> selectUser(String name, String password) throws SQLException;

	Object insertUser(String name, String password, String uploadPath);

	int getTotalPage(int pageSize);

	List<Map<String, Object>> getPartUserInfo(int currPage, int pageSize);
}
