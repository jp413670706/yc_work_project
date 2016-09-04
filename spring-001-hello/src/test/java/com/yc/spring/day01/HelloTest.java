package com.yc.spring.day01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {

	@Test  //正常使用
	public void testSayHello() {
		Hello hello = new Hello();
		hello.setWho("小紅");
		String result = hello.sayHello("小明");
		assertEquals("小紅对小明说：你好吗？",result);
	}
	
	@Test  //使用spring
	public void testSayHello02() {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");  //通过spring的配制文件创建， spring容器对象
		/*Hello hello = (Hello) cxt.getBean("hello");  //在容器中， 通过bean的id取到spring容器的对象
		String result = hello.sayHello("小明");
		assertEquals("小黑对小明说：你好吗？",result);*/
	}

}
