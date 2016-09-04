package com.yc.mybatis.mapper;

import java.util.List;

import com.yc.mybatis.entity.Blog;
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
}
