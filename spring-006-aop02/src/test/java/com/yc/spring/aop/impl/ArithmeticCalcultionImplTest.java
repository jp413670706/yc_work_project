package com.yc.spring.aop.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.aop.ArithmeticCalcultion;

public class ArithmeticCalcultionImplTest{

	private ArithmeticCalcultion ac;
	
	@Before
	public void setUp(){
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		ac = (ArithmeticCalcultion) cxt.getBean("ac"); 
	}
	
	@Test
	public void testAdd() {
		int result = ac.add(1, 2);
		assertEquals(3, result);		
	}

	@Test
	public void testMul() {
		int result = ac.mul(3, 2);
		assertEquals(1, result);	
	}

}
