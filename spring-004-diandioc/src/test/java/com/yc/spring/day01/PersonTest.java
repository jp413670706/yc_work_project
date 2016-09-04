package com.yc.spring.day01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {

	@Test  
	public void testPerson() {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		
		//默认情况下spring容器中的对象是单例模式, 修改成原型把bean的属性scope="prototype"
		Person person01 = (Person) cxt.getBean("person");
		Person person02 = (Person) cxt.getBean("person");
		
		//是否是同一个对象: 存储对象的数据空间是否一致
		if (person01 == person02){
			System.out.println("同一个对象");
		}else{
			System.out.println("不同的对象");
		}
	}
	
	@Test  
	public void testPerson02() {
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		
		Person person01 = (Person) cxt.getBean("person");
		System.out.println(person01);
		
		person01 = (Person) cxt.getBean("person");
		System.out.println(person01);
		cxt.close();
		
		
	
	}
}
