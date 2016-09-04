package com.yc.mybatis.util;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import com.yc.mybatis.entity.Blog;

public class MybatisUtilTest {

	@Test
	public void testGetConn() {
		MybatisUtil mu = new MybatisUtil();
		Connection con = mu.getConn();
		assertNotNull("数据库连接失败!!!", con);
	}

	
	@Test
	public void testGetBlog(){
		MybatisUtil mu = new MybatisUtil();
		Blog blog = mu.getBlog(1);
		System.out.println("===>" + blog);
		assertNotNull(blog);
	}
}
