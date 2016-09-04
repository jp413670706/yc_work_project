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
 * ��װ�����ݿ���ʵĲ���
 */
public class DBHelper {
	// ʹ�þ�̬���������, һ����Ŀһ��
	static {
		try {
			Class.forName(MyProperties.getInstance().getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������ݿ�����.
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

	// ��ɾ��: sql: insert into ���� values(?,?,?) params: {"1","xxx",33} => 3
	// list.size()
	// int�ķ��ر�ʾ��Ӱ�������
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
				//���еĲ������������ַ����ڴ���,   ����������������;Ͳ���  (    photo, text  )
				//TODO:  ���������п���Ҫ����Ҫ������������� 
				pstmt.setString(i + 1, params.get(i) + "");
			}
		}
	}
	
	
	
	
	
	
	/**
	 * ������Ķ�������ִ��
	 * @param sqls :û��?�����
	 * @return
	 */
	public int doUpdate(List<String> sqls) {
		Connection con = getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(   false);    //  �����Զ��ύģʽΪfalse
			if(  sqls!=null&&sqls.size()>0){
				for( int i=0;i<sqls.size();i++){
					pstmt = con.prepareStatement(    sqls.get(i));
					result = pstmt.executeUpdate();
				}
			}
			con.commit();           //�ύ�����Ľ��
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();    //���쳣�ˣ����gun����, �ָ�ԭ��������
			} catch (SQLException e1) {
				e1.printStackTrace();
			}    
		} finally {
			try {
				con.setAutoCommit(  true );     //�ָ��ֳ�
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeAll(null, pstmt, con);
		}
		return result;
	}
	
	
	/**
	 * ������Ķ�������ִ��
	 * @param sqls :û��?�����
	 * @return
	 */
	public int doUpdate(List<String> sqls,  List<List<Object>>  params) {
		Connection con = getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con.setAutoCommit(   false);    //  �����Զ��ύģʽΪfalse
			if(  sqls!=null&&sqls.size()>0){
				for( int i=0;i<sqls.size();i++){
					pstmt = con.prepareStatement(    sqls.get(i));
					if(   params!=null&&params.size()>0&&  params.get(i)!=null ){
						List<Object> ll=params.get(i);   //ȡ��ÿ������Ӧ�Ĳ����б�
						
						doParams(   pstmt,ll);   //    pstmt��ÿһ�����ѭ������һ��,   ll�����pstmt��Ӧ�Ĳ����ļ���
					}
					result = pstmt.executeUpdate();
				}
			}
			con.commit();           //�ύ�����Ľ��
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();    //���쳣�ˣ����gun����, �ָ�ԭ��������
			} catch (SQLException e1) {
				e1.printStackTrace();
			}    
		} finally {
			closeAll(null, pstmt, con);
			try {
				con.setAutoCommit(  true );     //�ָ��ֳ�
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * �ۺϺ�����ѯ
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
			// System.out.println("����:"+ cs[i] );
		}
		// //////////���������ȡ����ѯ���������
		while (rs.next()) {
			Map<String, String> map = new HashMap<String, String>(); // ÿѭ��
																		// rs.next()һ�Σ�����һ�����ݣ�һ�����ݿ��Է���һ��
																		// map��
			// map�ļ��������� ֵ������е�ֵ
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
