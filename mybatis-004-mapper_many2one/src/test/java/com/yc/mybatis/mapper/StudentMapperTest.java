package com.yc.mybatis.mapper;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.mybatis.entity.Student;
import com.yc.mybatis.util.MybatisUtil;

public class StudentMapperTest {

	@Test
	public void testGetStudentById() {
		SqlSession session = MybatisUtil.getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);
		Student stu = sm.getStudentById(1);
		System.out.println(stu);
		
		assertNotNull(stu);
	}
	
	@Test
	public void testGetStudentById02() {
		SqlSession session = MybatisUtil.getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);
		Student stu = sm.getStudentById02(6);
		System.out.println(stu);
		
		assertNotNull(stu);
	}

}
