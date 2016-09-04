package com.yc.mybatis.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.mybatis.entity.Blog;
import com.yc.mybatis.entity.BlogBean;
import com.yc.mybatis.util.MybatisUtil;

public class BlogMapperTest {
	
	@Test
	public void testGetBlogById() {
		//一级缓存： 作用范围在session的生命周期中, 所以也称session级缓存
		BlogMapper bm = MybatisUtil.getSession().getMapper(BlogMapper.class);
		Blog b = bm.getBlogById(1);
		System.out.println("第一次取到的数据 ==>" + b);
		System.out.println("******************");
		b = bm.getBlogById(1);
		System.out.println("第二次取到的数据 ==>" + b);
 }

	@Test
	public void testGetBlogById01() {
		//一级缓存： 作用范围在session的生命周期中, 所以也称session级缓存
		BlogMapper bm = MybatisUtil.getSession().getMapper(BlogMapper.class);
		Blog b = bm.getBlogById(1);
		System.out.println("第一次取到的数据 ==>" + b);
		System.out.println("******************");
		bm = MybatisUtil.getSession().getMapper(BlogMapper.class);
		b = bm.getBlogById(1);
		System.out.println("第二次取到的数据 ==>" + b);
 }
	
	
	@Test //管理一级缓存
	public void testGetBlogById02() {
		//一级缓存： 作用范围在session的生命周期中, 所以也称session级缓存
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		Blog b = bm.getBlogById(1);
		System.out.println("第一次取到的数据 ==>" + b);
		session.clearCache();  //清空一级缓存
		System.out.println("******************");
		b = bm.getBlogById(1);
		System.out.println("第二次取到的数据 ==>" + b);
 }
	
	@Test //使用二级缓存
	public void testGetBlogById03() {
		//1.启动二级缓存 ,在映射文件中 <cache>
		//2.session.commit()把对象数据存入二缓存中
		//3.使用存储对象数据可序列化
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		Blog b = bm.getBlogById(1);
		session.commit();
		System.out.println("第一次取到的数据 ==>" + b);
		session.clearCache();  //清空一级缓存
		System.out.println("******************");
		b = bm.getBlogById(1);
		System.out.println("第二次取到的数据 ==>" + b);
 }
	
	@Test //模糊查询
	public void testFindBlogsByLikeTitle() {
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		List<Blog> bs = bm.findBlogsByLikeTitle("my");
		System.out.println(bs);
	}

	@Test //模糊查询
	public void testFindBlogsByLikeTitle02() {
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		Blog blog = new Blog(0, "my", null);
		List<Blog> bs = bm.findBlogsByLikeTitle02(blog);
		System.out.println(bs);
	}
	
	@Test //分页查询
	public void testFindPartBlogsByPage(){
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pagesize", 10);
		params.put("pagenum", 1);
		
		List<Blog>  blogs = bm.findPartBlogsByPage(params);
		
		System.out.println(blogs);
		assertNotNull(blogs);
	}
	
	@Test //分页查询
	public void testFindPartBlogsByPage02(){
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		
		BlogBean bb= new BlogBean(10, 1);
		
		bb = bm.findPartBlogsByPage02(bb);
		
		System.out.println(bb);
		assertNotNull(bb);
	}
	
	@Test //分页查询
	public void testFindPartBlogsByPage03(){
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		
		BlogBean bb= new BlogBean(10, 1);
		
		bb = bm.findPartBlogsByPage03(bb);
		
		System.out.println(bb);
		assertNotNull(bb);
	}
	
	
	@Test //分页查询
	public void testFindPartBlogsByPage04(){
		SqlSession session = MybatisUtil.getSession();
		BlogMapper bm = session.getMapper(BlogMapper.class);
		
		BlogBean bb= new BlogBean(10, 1);
		
		bm.findPartBlogsByPage04(bb);
		
		System.out.println(bb);
		assertNotNull(bb);
	}
}
