package com.yc.mybatis.mapper;

import com.yc.mybatis.entity.Student;

public interface StudentMapper {
	Student getStudentById(int id);
	
	Student getStudentById02(int id);
}
