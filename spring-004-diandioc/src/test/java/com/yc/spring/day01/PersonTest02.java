package com.yc.spring.day01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest02 {

	@Test  
	public void testPerson() {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		Person person = (Person) cxt.getBean("person");
		System.out.println(person);
	}
	
	@Test  
	public void testPerson02() {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		Person person = (Person) cxt.getBean("person02");
		System.out.println(person);
	}

	@Test  
	public void testPerson03() {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		Person person = (Person) cxt.getBean("person03");
		System.out.println(person);
	}

}
