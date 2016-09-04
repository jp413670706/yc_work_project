package com.yc.spring.service.impl;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.service.Cashier;

public class BookShopCashierTest {
	
	private Cashier bookShopCashier;
	@Before
	public void setUp() throws Exception {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		bookShopCashier = (Cashier) cxt.getBean("bookShopCashier");
	}

	@Test
	public void testCheckout() {
		bookShopCashier.checkout(Arrays.asList("yc10000", "yc10001"), "admin");
	}

}
