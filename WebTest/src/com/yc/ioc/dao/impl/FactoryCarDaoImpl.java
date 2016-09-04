package com.yc.ioc.dao.impl;

import com.yc.ioc.dao.FactoryDao;

public class FactoryCarDaoImpl implements FactoryDao {

	@Override
	public void saveUser() {
		
		System.out.println("你制造了一辆 【保时捷】");
	}

}
