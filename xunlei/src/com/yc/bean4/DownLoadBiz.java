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
	 * 1. 取出下载地址和线程线
2. 到数据库查一下，是否有这个下载记录. 
3. 没有， 先在数据库记录一下当前初始的记录， 然后才开始下载
   有下载记录, 区分是否线程条件一样. 
	 * @param ip
	 * @param threadSize
	 * @return
	 * @throws SQLException 
	 */
	public Map<Integer, Long>   find(   String ip,   int threadSize  ) throws SQLException{
		
		Map<Integer, Long> map=new HashMap<Integer, Long>();
		
		/*//1. 先查询数据库，取出当前这个  ip地址的下载记录
		String sql="select * from downloadfile where downpath=? ";
		List<Object> params=new ArrayList<Object>();
		params.add(   ip );
		List<Map<String,String>> list=db.doFind(sql, params);
		
		if(   list.size()==threadSize){
			//证明原来有记录，是断点续传的情况, 那就将    原来的数据取出存到   map中去
			for(   Map<String,String> data: list){
				int threadid=Integer.parseInt(  data.get("threadid")  );
				long downlength=Long.parseLong(    data.get("downlength")  );
				map.put(threadid, downlength);
			}
		}else{
			//证明原来没有记录或是线程数不正确，只能当成没有记录的情况.    则将数据库中的原记录删除掉，再重新的添加记录
			sql="delete from downloadfile where downpath=?";
			params=new ArrayList<Object>();
			params.add(   ip );
			db.doUpdate(sql, params);
			
			//再重新添加新的记录
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
