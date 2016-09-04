package com.yc.spring.service;

import java.util.List;

public interface Cashier {
	void checkout(List<String> isbns, String username);
}
