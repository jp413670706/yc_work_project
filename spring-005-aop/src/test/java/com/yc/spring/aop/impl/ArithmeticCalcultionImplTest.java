package com.yc.spring.aop.impl;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.yc.spring.aop.ArithmeticCalcultion;
public class ArithmeticCalcultionImplTest {
	public ArithmeticCalcultion ac;
	@Before
	public void setUp(){
		/*ac = new ArithmeticCalcultionLoggingProxy(
				new ArithmeticCalcultionImpl() );*/
		
		ac = new MyDynamicProxy<ArithmeticCalcultion>().getProxyNewInstance(new ArithmeticCalcultionImpl());
	}

	@Test
	public void testAdd() {
		int result = ac.add(12, 45);
		assertEquals(result, 57);
	}

	@Test
	public void testMul() {
		int result = ac.mul(87, 45);
		assertEquals(result, 42);
	}
}