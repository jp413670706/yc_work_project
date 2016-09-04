package com.jy.mybatisnote.mapper;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import com.jy.mybatisnote.entities.Users;
import com.jy.mybatisnote.utils.MyBatisUtil;

public class UsersMapperTest {
	
	@Test
	public void testAdd() {
		SqlSession session = MyBatisUtil.getAutoTransactionSession();
		UsersMapper um = session.getMapper(UsersMapper.class);
		Users user = new Users("cici","女");
		int result = um.add(user);
		System.out.println("结果==》" + result);
		session.close();
	}
	
	@Test
	public void testFindCourseByTid(){
		SqlSession session = MyBatisUtil.getSession();
		UsersMapper um = session.getMapper(UsersMapper.class);
		Users results = um.findUserById2(1001);
		LogManager.getLogger().debug("结果是==>" + results);
		assertNotNull(results);
	}
	
	//动态sql测试
	@Test
	public void testFindUserByTid(){
		SqlSession session = MyBatisUtil.getSession();
		UsersMapper um = session.getMapper(UsersMapper.class);
		Users results = um.findUserById3(1001);
		LogManager.getLogger().debug("结果是==>" + results);
		assertNotNull(results);
	}

}
