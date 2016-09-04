package com.yc.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.ioc.service.FactoryService;

public class Cilent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		FactoryService factoryService = (FactoryService) factory.getBean("factoryService");
		factoryService.madeSomeThing();
		
		
	}

}
