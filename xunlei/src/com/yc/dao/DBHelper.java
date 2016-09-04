package com.yc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装了数据库访问的操作
 */
public class DBHelper {
	// 使用静态块加载驱动, 一个项目一次
	static {
		try {
			Class.forName(MyProperties.getInstance().getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获了数据库联接.
	 * 
	 * @return
	 */
	public Connection getCon() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(MyProperties.getInstance()
					.getProperty("url"), MyProperties.getInstance());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 增删改: sql: insert into 表名 values(?,?,?) params: {"1","xxx",33} => 3
	// list.size()
	// int的返回表示受影响的行数
	public int doUpdate(String sql, List<Object> params) {
		Connection con = getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			doParams(   pstmt,   params);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, con);
		}
		return result;
	}
	
	private void doParams(   PreparedStatement pstmt,  List<Object> params ) throws SQLException{
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				//所有的参数都当成了字符串在处理,   如果参数是其它类型就不行  (    photo, text  )
				//TODO:  将来这里有可能要增加要处理的数据类型 
				pstmt.setString(i + 1, params.get(i) + "");
			}
		}
	}
	
	
	
	
	
	
	/**
	 * 带事务的多条语句的执行
	 * @param sqls :没有?的语句
	 * @return
	 */
	public int doUpdate(List<String> sqls) {
		Connection con = getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(   false);    //  设置自动提交模式为false
			if(  sqls!=null&&sqls.size()>0){
				for( int i=0;i<sqls.size();i++){
					pstmt = con.prepareStatement(    sqls.get(i));
					result = pstmt.executeUpdate();
				}
			}
			con.commit();           //提交操作的结果
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();    //出异常了，则回gun数据, 恢复原来的数据
			} catch (SQLException e1) {
				e1.printStackTrace();
			}    
		} finally {
			try {
				con.setAutoCommit(  true );     //恢复现场
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeAll(null, pstmt, con);
		}
		return result;
	}
	
	
	/**
	 * 带事务的多条语句的执行
	 * @param sqls :没有?的语句
	 * @return
	 */
	public int doUpdate(List<String> sqls,  List<List<Object>>  params) {
		Connection con = getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(   false);    //  设置自动提交模式为false
			if(  sqls!=null&&sqls.size()>0){
				for( int i=0;i<sqls.size();i++){
					pstmt = con.prepareStatement(    sqls.get(i));
					if(   params!=null&&params.size()>0&&  params.get(i)!=null ){
						List<Object> ll=params.get(i);   //取出每条语句对应的参数列表
						
						doParams(   pstmt,ll);   //    pstmt是每一条语句循环生成一个,   ll是这个pstmt对应的参数的集合
					}
					result = pstmt.executeUpdate();
				}
			}
			con.commit();           //提交操作的结果
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();    //出异常了，则回gun数据, 恢复原来的数据
			} catch (SQLException e1) {
				e1.printStackTrace();
			}    
		} finally {
			closeAll(null, pstmt, con);
			try {
				con.setAutoCommit(  true );     //恢复现场
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 聚合函数查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public int doSelect(String sql, List<Object> params) {
		int result = 0;
		Connection con = getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			doParams( pstmt,   params);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}

	public List<Map<String, String>> doFind(String sql, List<Object> params)
			throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection con = getCon();
		PreparedStatement pstmt = con.prepareStatement(sql);
		doParams(   pstmt,   params);
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String[] cs = new String[rsmd.getColumnCount()];
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			cs[i] = rsmd.getColumnLabel(i + 1);
			// System.out.println("列名:"+ cs[i] );
		}
		// //////////以上完成了取出查询结果的列名
		while (rs.next()) {
			Map<String, String> map = new HashMap<String, String>(); // 每循环
																		// rs.next()一次，就是一行数据，一行数据可以放在一个
																		// map中
			// map的键是列名， 值是这个列的值
			if (cs != null && cs.length > 0) {
				for (int i = 0; i < cs.length; i++) {
					map.put(cs[i].toLowerCase(), rs.getString(cs[i]));
				}
			}
			list.add(map);
		}
		closeAll(rs, pstmt, con);
		return list;
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
