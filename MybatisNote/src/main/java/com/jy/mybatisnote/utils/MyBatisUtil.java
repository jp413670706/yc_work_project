package com.jy.mybatisnote.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;

public class MyBatisUtil {
	private static SqlSessionFactory factory=null;
	static{
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream("MybatisConfiguration.xml");
			LogManager.getLogger().debug("加载Batis配置文件成功...");
			
		} catch (IOException e) {
			LogManager.getLogger().error("加载配置文件失败...",e);
		}
		
		factory = new SqlSessionFactoryBuilder().build(in);
		LogManager.getLogger().debug("根据mybatis配置文件信息构建sqlsessionfactory实例对象成功...");
	}
	
	/**
	 * 获取数据库会话连接，默认手动事务
	 * @return
	 */
	public static SqlSession getSession(){
		SqlSession session = factory.openSession();
		LogManager.getLogger().debug("根据sqlsessionfactory生产手动事务sqlsession实例对象成功");
		return session;
	}
	
	/**
	 * 获取数据库会话连接，自动事务
	 * @return
	 */
	public static SqlSession getAutoTransactionSession(){
		SqlSession session = factory.openSession(true);
		LogManager.getLogger().debug("根据sqlsessionfactory生产自动事务sqlsession实例对象成功");
		return session;
	}
}

