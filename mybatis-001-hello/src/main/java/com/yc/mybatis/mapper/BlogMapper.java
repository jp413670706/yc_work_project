package com.yc.mybatis.mapper;

import com.yc.mybatis.entity.Blog;
/**
 * 映射接口: 与映射文件对应的接口
 * @author Administrator
 *
 */
public interface BlogMapper {
	//与映射文件中的sql语句映射
	Blog getBlogById(int id);
}
