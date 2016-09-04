package com.yc.ioc.service.impl;

import com.yc.ioc.dao.FactoryDao;
import com.yc.ioc.service.FactoryService;

public class FactoryServiceImpl implements FactoryService {

	private FactoryDao factoryDao;
	public FactoryServiceImpl(FactoryDao factoryDao) {
		super();
		this.factoryDao = factoryDao;
	}
/*
	private String name;
	

	public void setName(String name) {
		this.name = name;
	}*/
   

	@Override
	public void madeSomeThing() {

		 this.factoryDao.saveUser();
		//System.out.println(name);
	}


}
