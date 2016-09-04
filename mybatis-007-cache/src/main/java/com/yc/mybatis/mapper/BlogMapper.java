package com.yc.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.yc.mybatis.entity.Blog;
import com.yc.mybatis.entity.BlogBean;
/**
 * 映射接口: 与映射文件对应的接口
 * @author Administrator
 */
public interface BlogMapper {
	//与映射文件中的sql语句映射
	Blog getBlogById(int id);
	
	//插入操作
	int insertBlog(Blog blog);
	
	//修改操作
	int  updateBlog(Blog blog);
	
	//删除操作
	int  delBlog(int id);
	
	//读取操作
	List<Blog> findAllBlogs();
	
	//模糊查询
	List<Blog> findBlogsByLikeTitle(String likeTitle);
	
	List<Blog> findBlogsByLikeTitle02(Blog blog);
	
	
	//分页查询
	List<Blog> findPartBlogsByPage(Map<String, Object> params);
	
	BlogBean findPartBlogsByPage02(BlogBean bb);
	
	BlogBean findPartBlogsByPage03(BlogBean bb);
	
	void findPartBlogsByPage04(BlogBean bb);
}
