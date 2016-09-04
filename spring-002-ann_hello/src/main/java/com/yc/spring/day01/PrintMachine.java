package com.yc.spring.day01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("pm")
public class PrintMachine {
	@Autowired
	@Qualifier("bwi")
	private Ink ink;
	
	@Autowired
	@Qualifier("a4")
	private Paper paper;

	public void print(){
		System.out.println(String.format("正在%s纸上进行%s打印内容...", paper.getSize(), ink.getColor()));
	}
}
