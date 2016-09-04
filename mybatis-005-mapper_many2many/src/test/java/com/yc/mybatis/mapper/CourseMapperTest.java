package com.yc.mybatis.mapper;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.mybatis.entity.Course;
import com.yc.mybatis.util.MybatisUtil;

public class CourseMapperTest {

	@Test
	public void testGetCourseById() {
		SqlSession session = MybatisUtil.getSession();
		CourseMapper cm = session.getMapper(CourseMapper.class);
		
		Course cs=  cm.getCourseById(1);
		System.out.println(cs);
		assertNotNull(cs);
	}

}
