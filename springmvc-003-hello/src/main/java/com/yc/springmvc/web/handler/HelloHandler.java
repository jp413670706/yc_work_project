package com.yc.springmvc.web.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloHandler {
	
	/*
	 * @PathVariable  : ȡ��������Դ��{xxx}�� ��Ϊһ������, ͨ�� @PathVariable(xxx)ָ����һ������ֵ
	 */
	
	@RequestMapping(value="/{name}" )
	public String hello01(@PathVariable("name") String name ){
		System.out.println("=======> ������Ĳ�����: " + name);
		return "success";
	}
	
	
}
