package com.jy.mybatisnote.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * 根据输入条件动态地构建SQL语句。
 * MyBatis提供了各种注解如@InsertProvider,@UpdateProvider,@DeleteProvider和@SelectProvider
 * @author jy
 *
 */
public class UsersDynaSqlProvider {
	public String findUserById(int id){
		return "select * from users where id="+id;
	}

	
	/**
	 * 这里我们使用了@SelectProvider来指定了一个类，及其内部的方法，用来提供需要执行的SQL语句。
	 * 但是使用字符串拼接的方法唉构建SQL语句是非常困难的，并且容易出错。
	 * 所以MyBaits提供了一个SQL工具类不使用字符串拼接的方式，简化构造动态SQL语句。
	 * @param id
	 * @return
	 */
	/*public String findUserById(final int id)  
	{  
		return new SQL()  
		{  
			{  
				SELECT("id , name, sex");  
				FROM("users");  
				WHERE("id=" + id);  
			}  
		} .toString();  
	}  */
}
