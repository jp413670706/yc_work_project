package com.yc.spring.service.impl;

import com.yc.spring.dao.BookShopDao;
import com.yc.spring.exception.AccountException;
import com.yc.spring.exception.BookStockException;
import com.yc.spring.service.BookShopService;

public class BookShopServiceImpl implements BookShopService {

	private BookShopDao bookShopDao;

	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	@Override
	public void purchase(String isbn, String username) {
		// 模拟超时

		// 1.取到书的价格
		int price = bookShopDao.getBookPriceByISBN(isbn);

		// 2.修改书的库存
		try {
			bookShopDao.updateBookStock(isbn);
		} catch (Exception e) {
			throw new BookStockException("库存不足!!!");
		}
	/*	
		try {
			Thread.sleep(3000); // 模拟超时
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
		
		// 3.修账户的余额
		try {
			bookShopDao.updateAccount(username, price);
		} catch (Exception e) {
			throw new AccountException("余额不足!!!");
		}
		
		
	}
}
