package com.yc.mybatis.entity;

import java.util.List;

public class Course {
	private int id;
	private String coursecode;
	private String coursename;
	private List<Student> stus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public List<Student> getStus() {
		return stus;
	}

	public void setStus(List<Student> stus) {
		this.stus = stus;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", coursecode=" +
	coursecode + ", coursename=" + coursename + 
	", stus=" + stus + "]";
	}

}
