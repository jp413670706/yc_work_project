package com.yc.emp.entity;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Employee {

	private Integer id;
	@Length(min=5, max=10)
	private String lastName;
	private String email;
	private String gender;
	private Department department;
	
	// 2016-6-8
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past  //表示时间是将来的时间
	private Date birthday;

	// 14,523.9
	@NumberFormat(pattern="#,###,###.#")
	private float salary;

	public Employee() {
	}

	public Employee(String lastName, String email, String gender, Department department) {
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	public Employee(Integer id, String lastName, String email, String gender, Department department) {
		this(lastName, email, gender, department);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", department=" + department + ", birthday=" + birthday + ", salary=" + salary + "]";
	}

}
