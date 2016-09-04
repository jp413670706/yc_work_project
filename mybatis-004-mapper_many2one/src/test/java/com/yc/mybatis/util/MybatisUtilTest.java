package com.yc.mybatis.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.mybatis.entity.Blog;
import com.yc.mybatis.mapper.BlogMapper;

public class MybatisUtilTest {

	@Test
	public void testGetConn() {
		SqlSession session = MybatisUtil.getSession();
		Connection con = session.getConnection();
		MybatisUtil.close(session);
		assertNotNull("数据库连接失败!!!", con);
	}

	@Test
	// 查询
	public void testGetBlog() {
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		Blog blog = bm.getBlogById(1);
		System.out.println("===>" + blog);
		MybatisUtil.close(session);
		assertNotNull(blog);
	}

	// 插入

	@Test
	// 查询
	public void testInsertBlog() {
		SqlSession session = MybatisUtil.getSession();
		// 默认启动了事务, 需要手动结束事务(rollback或commit)
		BlogMapper bm = session.getMapper(BlogMapper.class);
		Blog b = new Blog(2, "mybatis好帅", "yc");
		int result = bm.insertBlog(b);
		session.commit(); // 提交
		// session.rollback(); 回滚
		MybatisUtil.close(session);
		assertEquals(1, result);
	}

	@Test
	// 查询
	public void testInsertBlog02() {
		SqlSession session = MybatisUtil.getAutoTransactionSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		Blog b = new Blog(3, "mybatis框架实战", "小明");
		int result = bm.insertBlog(b);
		MybatisUtil.close(session);
		assertEquals(1, result);
	}

	// 修改
	@Test
	public void testUpdateBlog() {
		SqlSession session = MybatisUtil.getAutoTransactionSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		Blog b = new Blog(3, "mybatis框架实战(二)", "小红");
		int result = bm.updateBlog(b);
		MybatisUtil.close(session);
		assertEquals(1, result);
	}

	// 删除
	@Test
	public void testDelBlog() {
		SqlSession session = MybatisUtil.getAutoTransactionSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		int id = 3;
		int result = bm.delBlog(id);
		MybatisUtil.close(session);
		assertEquals(1, result);
	}

	// 读取
	@Test
	public void testFindAllBlogs() {
		SqlSession session = MybatisUtil.getAutoTransactionSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		List<Blog> blogs = bm.findAllBlogs();
		System.out.println(blogs);
		MybatisUtil.close(session);
		assertNotNull(blogs);
	}
}
