package com.jy.mybatisnote.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

import com.jy.mybatisnote.entities.Courses;
import com.jy.mybatisnote.entities.Users;

public interface UsersMapper {
	/*	@Insert("insert into users(id,name,sex) values(#{id},#{name},#{sex})")
	public int add(Users user);*/

	/**
	 * 使用@SelectKey注解来为任意SQL语句来指定主键值，作为主键列的值。
	 * 自动生成序列
	 * statement:执行语句
	 * keyProperty：users里面对应的属性名
	 * resultType：返回的结果
	 * before:在执行插入之前，先给id赋值
	 */
	@Insert("insert into users(id,name,sex) values(#{id},#{name},#{sex})")
	@SelectKey(statement="SELECT SEQ_USERS_ID.NEXTVAL FROM DUAL", keyProperty="id", resultType=int.class, before=true)
	public int add(Users user);

	@Select("select * from users where id=#{id}")
	public Users findUserById1(int id);
	/**
	 * 一对多映射   一个老师对应多个课程
	 * 
	 * 这里我们使用了@Many注解的select属性来指向一个完全限定名称的方法，
	 * 该方法将返回一个List<Courses>对象。
	 * 使用column=”id”，
	 * users表中的id列值将会作为输入参数传递给findCourseByTid()方法。
	 */
	@Select("select * from courses where userid=#{id}")  
	@Results(  
			{  
				@Result(id = true, column = "courseId", property = "courseId"),  
				@Result(column = "courseName", property = "courseName")
			})  
	public List<Courses> findCourseByTid(int id); 
	
	@Select("SELECT id, name,sex FROM users where id=#{id}")  
    @Results(  
    {  
        @Result(id = true, column = "id", property = "id"),  
        @Result(column = "name", property = "name"),  
        @Result(column = "sex", property = "sex"),  
        @Result(property = "courses", column = "id",  
        many = @Many(select = "com.jy.mybatisnote.mapper.UsersMapper.findCourseByTid"))  
    })  
    Users findUserById2(int id);
	
	//动态sql
	@SelectProvider(type=UsersDynaSqlProvider.class, method="findUserById" )  
	public Users findUserById3(int id); 
}
