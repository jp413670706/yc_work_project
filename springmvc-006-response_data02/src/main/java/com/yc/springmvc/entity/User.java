package com.yc.springmvc.entity;

public class User {
	private String username;
	private String password;
	private int age;
	private Address address;
	
	public User() {
	}
	
	public User(String username, String password, int age) {
		this.username = username;
		this.password = password;
		this.age = age;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + 
				password + ", age=" + age + ", address=" + address + "]";
	}

}
