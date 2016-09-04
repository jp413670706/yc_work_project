package com.yc.spring.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yc.spring.dao.BookShopDao;

public class BookShopDaoImpl implements BookShopDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getBookPriceByISBN(String isbn) {
		String sql = "select price from book where isbn=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{isbn}, int.class);
	}

	@Override
	public void updateBookStock(String isbn) {
		String sql = "update bookstock set stock = stock - 1 where isbn=?";
		jdbcTemplate.update(sql, isbn);
	}

	@Override
	public void updateAccount(String username, int price) {
		String sql = "update account set balance = balance - ? where username=?";
		jdbcTemplate.update(sql, price, username);
	}

}
