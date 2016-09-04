package com.yc.spring.dao.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.dao.BookShopDao;

public class BookShopDaoImplTest {

	private BookShopDaoImpl bookShopDao;

	@Before
	public void setUp() throws Exception {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		bookShopDao = (BookShopDaoImpl) cxt.getBean("bookShopDao");
	}

	@Test
	public void testGetBookPriceByISBN() {
		int price = bookShopDao.getBookPriceByISBN("yc10000");
		System.out.println("price == > " + price);
		assertEquals(price, 50);
	}

	@Test
	public void testUpdateBookStock() {
		bookShopDao.updateBookStock("yc10000");
	}

	@Test
	public void testUpdateAccount() {
		bookShopDao.updateAccount("yc", 200);
	}
}
