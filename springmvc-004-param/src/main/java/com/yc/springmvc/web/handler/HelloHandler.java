package com.yc.springmvc.web.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello" )
public class HelloHandler {
	
	/*
	 * @RequestParam:
	 * 	name表示与请求参数的key绑定
	 * 	value表示别名 ：请求参数的key别名
	 *  required 表示必须有值
	 *  defaultvalue 表示没 有取到值时，默认的值
	 *  
	 *  注意： 基本类型如果没 有值会抛出异常, 1.改为包装类， 2.设置默认值
	 */
	
	@RequestMapping("/hello" )
	public String hello01(@RequestParam(value="name", name="name")String name, 
			@RequestParam(value="age", defaultValue="0")int age){
		System.out.println("=======> 请求处理的参数是 name: " + name + ", age:" + age);
		return "success";
	}
	
	@RequestMapping("/hello02")
	public String hello02(@RequestHeader(name="Connection")String con){
		System.out.println("=======> 请求头处理的参数是 Connection: " + con );
		return "success";
	}
	
	@RequestMapping("/hello03")
	public String hello03(@CookieValue("JSESSIONID") String sessionId){
		System.out.println("=======> 请求头处理的参数是 JSESSIONID: " + sessionId );
		return "success";
	}
	  
	
}
