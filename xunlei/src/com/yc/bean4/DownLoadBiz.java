package com.yc.bean4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.dao.DBHelper;

public class DownLoadBiz {
	private DBHelper db=new DBHelper();
	/**
	 * 1. ȡ�����ص�ַ���߳���
2. �����ݿ��һ�£��Ƿ���������ؼ�¼. 
3. û�У� �������ݿ��¼һ�µ�ǰ��ʼ�ļ�¼�� Ȼ��ſ�ʼ����
   �����ؼ�¼, �����Ƿ��߳�����һ��. 
	 * @param ip
	 * @param threadSize
	 * @return
	 * @throws SQLException 
	 */
	public Map<Integer, Long>   find(   String ip,   int threadSize  ) throws SQLException{
		
		Map<Integer, Long> map=new HashMap<Integer, Long>();
		
		/*//1. �Ȳ�ѯ���ݿ⣬ȡ����ǰ���  ip��ַ�����ؼ�¼
		String sql="select * from downloadfile where downpath=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(   ip );
		List<Map<String,String>> list=db.doFind(sql, params);
		
		if(   list.size()==threadSize){
			//֤��ԭ���м�¼���Ƕϵ����������, �Ǿͽ�    ԭ��������ȡ���浽   map��ȥ
			for(   Map<String,String> data: list){
				int threadid=Integer.parseInt(  data.get("threadid")  );
				long downlength=Long.parseLong(    data.get("downlength")  );
				map.put(threadid, downlength);
			}
		}else{
			//֤��ԭ��û�м�¼�����߳�������ȷ��ֻ�ܵ���û�м�¼�����.    �����ݿ��е�ԭ��¼ɾ�����������µ���Ӽ�¼
			sql="delete from downloadfile where downpath=?";
			params=new ArrayList<Object>();
			params.add(   ip );
			db.doUpdate(sql, params);
			
			//����������µļ�¼
			for(   int i=0;i<threadSize;i++){
				map.put(new Integer(i),new Long( 0  )   );
				
				sql="insert into downloadfile values(   seq_downloadfile.nextval,  ?,?, 0)";
				params=new ArrayList<Object>();
				params.add(   ip );
				params.add(  i );
				db.doUpdate(sql,params);
			}
		}
		*/
		return map;
	}
	public void record(String path, int threadid, long size) {
	/*	String sql="update downloadfile set downlength=downlength+? where downpath=? and threadid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(size);
		params.add(path);
		params.add(threadid);
		db.doUpdate(sql, params);*/
		
	}
	
	public void delete(  String path){
		/*String sql="delete from downloadfile where downpath=?";
		List<Object> params=new ArrayList<Object>();
		params.add(   path );
		db.doUpdate(sql, params);*/
		
	}
}
