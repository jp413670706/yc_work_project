package com.yc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * ������ļ�д�ɵ���ģ��
   * ��Ϊ��Դ�ļ�ֻ�ö�ȡһ�μ���
 */
public class MyProperties extends Properties {    //ͨ���̳�  MyProperties����   getProperty()   ,   load()
	private static MyProperties instance;

	
	//�����ĺ���: ���췽��˽�л�
	private MyProperties(){
		//����������ȡ  db.properties�ļ�����ɶ�ȡ������Ϣ�Ĳ���
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
