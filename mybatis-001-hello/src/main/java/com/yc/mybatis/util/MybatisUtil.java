package com.yc.mybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;

import com.yc.mybatis.entity.Blog;
import com.yc.mybatis.mapper.BlogMapper;

public class MybatisUtil {
	/**
	 * 获得数据库连接
	 * @return
	 */
	public Connection getConn(){
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream("mybatis.xml");
			LogManager.getLogger().debug("加载mybatis配制文件成功...");
		} catch (IOException e) {
			LogManager.getLogger().error("加载mybatis配制文件失败!!!", e);
		}
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		LogManager.getLogger().debug("根据mybatis配制文件信息构建SqlSessionFactory实例对象成功...");
		
		SqlSession session = factory.openSession();
		LogManager.getLogger().debug("根据SqlSessionFactory生产SqlSession实例对象成功...");
		
		Connection con = session.getConnection(); 
		LogManager.getLogger().debug("根据SqlSession取到Connection实例对象成功...");
		return con;
	}
	
	
		//增改删查
	
	public Blog getBlog(int id){
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream("mybatis.xml");
			LogManager.getLogger().debug("加载mybatis配制文件成功...");
		} catch (IOException e) {
			LogManager.getLogger().error("加载mybatis配制文件失败!!!", e);
		}
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		LogManager.getLogger().debug("根据mybatis配制文件信息构建SqlSessionFactory实例对象成功...");
		
		SqlSession session = factory.openSession();
		LogManager.getLogger().debug("根据SqlSessionFactory生产SqlSession实例对象成功...");
		
		
		BlogMapper blogMapper = session.getMapper(BlogMapper.class);
		LogManager.getLogger().debug("根据映射文件XxxMapper.xml创建出映射接口代理实现类实例对象成功...");
		
		Blog blog = blogMapper.getBlogById(id);
		LogManager.getLogger().debug("根据映射文件XxxMapper.xml中的映射sql语句获得结果成功...");
		return blog;
	}
	
}
