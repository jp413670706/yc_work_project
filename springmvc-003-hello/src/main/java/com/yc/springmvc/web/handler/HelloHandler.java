package com.yc.springmvc.web.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloHandler {
	
	/*
	 * @PathVariable  : 取到请求资源名{xxx}， 做为一个变量, 通过 @PathVariable(xxx)指定给一个参数值
	 */
	
	@RequestMapping(value="/{name}" )
	public String hello01(@PathVariable("name") String name ){
		System.out.println("=======> 请求处理的参数是: " + name);
		return "success";
	}
	
	
}
