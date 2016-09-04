package com.yc.client;

import com.yc.service.UserService;
import com.yc.service.impl.UserServiceImpl;

public class Client {

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		userService.find();
	}

}
