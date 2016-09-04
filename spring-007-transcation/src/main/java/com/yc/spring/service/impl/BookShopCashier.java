package com.yc.spring.service.impl;

import java.util.List;

import com.yc.spring.service.BookShopService;
import com.yc.spring.service.Cashier;

public class BookShopCashier implements Cashier {
	private BookShopService bookShopService;

	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}

	@Override
	public void checkout(List<String> isbns, String username) {
		for (String isbn : isbns) {
			bookShopService.purchase(isbn, username);
		}
	}

}
