package com.yc.mybatis.mapper;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yc.mybatis.entity.Teacher;
import com.yc.mybatis.util.MybatisUtil;

public class TeacherMapperTest {

	@Test
	public void testGetTeacherById() {
		SqlSession session =MybatisUtil.getAutoTransactionSession();
		TeacherMapper tm = session.getMapper(TeacherMapper.class);
		Teacher t = tm.getTeacherById(1001);
		System.out.println(t);
		assertNotNull(t);
	}

}
