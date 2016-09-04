package com.yc.spring.day01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {

	@Test  //使用spring
	public void testSayHello() {
		  //通过spring的配制文件创建， spring容器对象
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		 //在容器中， 通过bean的id取到spring容器的对象
		Hello hello = (Hello) cxt.getBean("hello3"); 
		//这种是通过bean class值来取到spring容器中的实例对象, 有两个相同类型的实例对象就会出错
		//Hello hello = cxt.getBean(Hello.class);  
		String result = hello.sayHello("小明");
		//assertEquals("小黑对小明说：你好吗？",result);
		System.out.println("===>" + hello);
		
		//调用spring容器中的实例对象时，有两种方式, 1.通过bean 的"id", 2.通过bean的"class" 
	}

}
