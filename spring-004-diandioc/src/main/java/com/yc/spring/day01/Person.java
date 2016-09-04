package com.yc.spring.day01;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Person implements BeanPostProcessor{
	private String name;
	private int age;
	private String  gender;
	
	public Person() {
		System.out.println("我是Person构造方法....");
	}

	public Person(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}

	@Override
	public Object postProcessAfterInitialization(Object obj, String arg) throws BeansException {
		System.out.println(obj + " == " + arg);
		Person p = (Person) obj;
		p.setName("上帅可");
		return obj;
	}

	@Override
	public Object postProcessBeforeInitialization(Object obj, String arg) throws BeansException {
		System.out.println(obj + " == " + arg);
		return obj;
	}

	
	
}
