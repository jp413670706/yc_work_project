package com.jy.mybatisnote.entities;

public class Courses {
	private int courseId;
	private String courseName;
	private int userId;

	public Courses() {
	}

	public Courses(int courseId, String courseName, int userId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "\nCourses [courseId=" + courseId + ", courseName=" + courseName + ", userId=" + userId + "]";
	}

}
