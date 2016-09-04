package com.yc.fav.util;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// 指明使用spring的测试框架来进行单元测试
@ContextConfiguration("classpath:spring.xml")
// 使用的spring的配制文件
public class ConnTest {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void testConn() {
		Connection con = sqlSessionFactory.openSession().getConnection();
		assertNotNull(con);
	}

}
