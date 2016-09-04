package com.yc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 将这个文件写成单例模型
   * 因为资源文件只用读取一次即可
 */
public class MyProperties extends Properties {    //通过继承  MyProperties有了   getProperty()   ,   load()
	private static MyProperties instance;

	
	//单例的核心: 构造方法私有化
	private MyProperties(){
		//在这里来读取  db.properties文件，完成读取配置信息的操作
		InputStream iis=MyProperties.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			super.load(iis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static MyProperties getInstance(){
		if(   instance==null  ){
			instance=new MyProperties();
		}
		return  instance;
	}
	
	
}
