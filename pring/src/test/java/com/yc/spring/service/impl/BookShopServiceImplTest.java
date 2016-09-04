package com.yc.spring.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.service.BookShopService;

public class BookShopServiceImplTest {
	private BookShopService bookShopService;
	@Before
	public void setUp() throws Exception {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		bookShopService = (BookShopService) cxt.getBean("bookShopService");
	}

	@Test
	public void testPurchase() {
		bookShopService.purchase("yc10001", "yc");
	}

}
