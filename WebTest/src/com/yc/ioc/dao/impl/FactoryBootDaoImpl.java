package com.yc.ioc.dao.impl;

import com.yc.ioc.dao.FactoryDao;

public class FactoryBootDaoImpl implements FactoryDao {

	@Override
	public void saveUser() {
		System.out.println("你制造了一个 【机器人】");
	}

}
