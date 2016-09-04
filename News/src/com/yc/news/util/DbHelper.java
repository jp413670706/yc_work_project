package com.yc.news.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbHelper {
	private static DataSource dataSource;
	
	static{
		//加载驱动，整个系统中只需要加载一次到JVM中即可
		try {
			//加载 数据库连接的数据连接属性数据
			//Properties prop = new Properties();
			//prop.load(DbHelper.class.getClassLoader().getResourceAsStream("db.properties"));	
			//dataSource = BasicDataSourceFactory.createDataSource(prop); //创建数据源
			Context context = new  InitialContext();  //创建一个上下文件对象
			
			//java:comp/env/ 这是调用tomcat命名目录接口资源的前缀
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/jspJNDI");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取数据库连接对象
	public static Connection getConn(){
		Connection con = null;
		if (dataSource != null){
			try {
				con = dataSource.getConnection();   //在数据源中取到一个连接
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		return con;
	}
	
	//关闭连接对象
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn ){
		//关闭结果集对象
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭语句对象
		if(null!=stmt){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭数据库连接对象
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn ){
		//关闭结果集对象
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭语句对象
		if(null!=pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//关闭数据库连接对象
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 设置参数的方法
	 * @param pstmt 预编译对象
	 * @param params 传入的设置值的集合
	 * @throws SQLException 
	 */
	private static void setParams(PreparedStatement pstmt,List<Object> params) throws SQLException{
		if(null!=params&&params.size()>0){
			for(int i=0;i<params.size();i++){
				pstmt.setString(i+1, params.get(i).toString());//params存储值的顺序与 ?的顺序一样
			}
		}
	}
	
	/**
	 * 增删改
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static int doUpdate(String sql,List<Object> params) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result =-1;
		
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			setParams(pstmt, params);
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	public static int doUpdate(List<String> sqls,List<List<Object>> params) throws SQLException{
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result =-1;
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			if(null!= sqls && sqls.size()>0){
//				循环sql语句
				for(int i=0;i<sqls.size();i++){
					String sql=sqls.get(i);
					pstmt =conn.prepareStatement(sql);
//					给当前sql语句设置
					setParams(pstmt, params.get(i));
					result +=pstmt.executeUpdate();
				}
			}
//		提交事物
			conn.commit();
		} catch (Exception e) {
//			 回滚事物
			result=0;
			conn.rollback();
		} finally {
//			回复现场
			conn.setAutoCommit(true);
			closeAll(null, pstmt, conn);
		}
		return result;
	}
	
	public static int updateImg(String sql,int id ,File file) throws FileNotFoundException{
		FileInputStream in = new FileInputStream(file);
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setBinaryStream(1, in,(int)file.length());
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			closeAll(null, pstmt, conn);
		}
		
		return result;
	}
	
	/**
	 * 查询多条记录
	 * @param sql
	 * @param params
	 * @return 
	 * @throws SQLException 
	 */
	public static List<Map<String , Object>> findMultiObject(String sql,List<Object> params) throws SQLException{
		List<Map<String , Object>> list =new ArrayList<Map<String ,Object>>();
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Map<String, Object> map =null;
		try {
			conn =getConn();
			pstmt =conn.prepareStatement(sql);
			setParams(pstmt, params);
			rs =pstmt.executeQuery();
			List<String > columnNames =getAllColumnNames(rs);//获取结果中的所有列表
			while(rs.next()){
				map = new HashMap<String,Object>();
				for(String cn:columnNames){//循环列名，将列表作用map的键，根据列表获取到每个列的值
					map.put(cn, rs.getObject(cn));
				}
				list.add(map);
			}
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return list;
	}
	
	/**
	 * 查询单条记录   select * from 表名 where id = 1
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static Map<String,Object> findSingleObject(String sql,List<Object> params) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Object> map = null;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = getAllColumnNames(rs); //获取结果集中的所有列表
			while(rs.next()){
				map = new HashMap<String,Object>();
				for(String cn: columnNames){	//循环列名，将列表作用Map的键，根据列表获取到每个列的值
					map.put(cn, rs.getObject(cn));
				}
			}
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return map;
	}
	
	/**
	 * 根据结果集对象获取所有的列名 存在一个list集合中 jdbc2.0取元数据
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static List<String> getAllColumnNames(ResultSet rs ) throws SQLException{
		List<String> columnNames =new ArrayList<String>();
		if(null !=rs){
			for(int i=0 ;i<rs.getMetaData().getColumnCount();i++){
				columnNames.add(rs.getMetaData().getColumnName(i+1));
			}
		}
		return columnNames;
	}
}
