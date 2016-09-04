package com.yc.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.yc.mybatis.entity.Course;

public interface CourseMapper {
	@Select("select * from course")
	Course getCourseById(int id);
}
