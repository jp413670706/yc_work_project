package com.yc.jsp.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.jsp.dao.UserDao;
import com.yc.jsp.util.DbHelper;

public class UserDaoImpl implements UserDao {

	@Override
	public Map<String, Object> selectUser(String name, String password) throws SQLException {
		String sql = "select 1 from users where username=? and password=?";
		return DbHelper.findSingleObject(sql, name, password);
	}

	@Override
	public Object insertUser(String name, String password, String uploadPath) {
		String sql = "insert into users values(seq_users.nextval, ?, ?, ?)";
		return DbHelper.doUpdate(sql, name, password, uploadPath);
	}

	@Override
	public int getTotalPage(int pageSize) {
		String sql = String.format("select ceil(count(1)/%d) TotalPage from users", pageSize);
		try {
			return ((BigDecimal)DbHelper.findSingleObject(sql).get("TOTALPAGE")).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> getPartUserInfo(int currPage, int pageSize) {
		String sql = String.format("select * from (select u.*, rownum rn from " +
				"(select * from users order by 1) u where rownum <= %d) where  rn > %d", 
				currPage * pageSize, (currPage - 1)*pageSize);
		try {
			return DbHelper.findMultiObject(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}