package com.jy.mybatisnote.entities;

import java.util.List;

public class Users {
	private int id;
	private String name;
	private String sex;
	private List<Courses> courses;
	
	public Users() {
	}

	public Users(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}
	
	public Users(int id, String name, String sex) {
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", sex=" + sex + ", courses=" + courses + "]";
	}
}
