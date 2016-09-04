package com.yc.mybatis.entity;

import java.util.List;

public class Teacher {
	private int id;
	private String name;
	private String gender;
	private String researcharea;
	private String title;
	private List<Student> stus;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getResearcharea() {
		return researcharea;
	}

	public void setResearcharea(String researcharea) {
		this.researcharea = researcharea;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Student> getStus() {
		return stus;
	}

	public void setStus(List<Student> stus) {
		this.stus = stus;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + 
				", gender=" + gender + ", researcharea=" + 
				researcharea + ", title=" + title + ", stus=" + 
				stus + "]";
	}

}
