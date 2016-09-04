package com.yc.spring.dao;


public interface BookShopDao {

	int getBookPriceByISBN(String isbn);

	void updateBookStock(String isbn);

	void updateAccount(String username, int price);
}
