package com.yc.springmvc.web.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloHandler01 {
	
	/*
	 * @RequestMapping : 请求映射处理, 可以修饰方法(请求资源)， 类(请求资源的前缀)
	 * 	
	 * 请求资源通配: *(表示任意一级目录) ?(表示任意一个字符) **表示多级目录
	 * 
	 * params ：可以配制请求参数的约定:  满足约定可以正常显示， 不满足出现警告No matching handler method found for servlet request
	 * param=value    表示等于某值
	 * param!=value    表示不等于
	 *  
	 * headers: 可以配制请求头参数的约定与params一致
	 * 
	 */
	
	@RequestMapping("/hello")
	public String hello01(){
		System.out.println("=======> springmvc框架使用起来了.....");
		return "success";
	}
	
	@RequestMapping("/**/hello02")
	public String hello02(){
		System.out.println("=======> springmvc框架使用起来了.....");
		return "success";
	}
	
	
	@RequestMapping(value="/hello03", params={"name=yc", "age"})
	public String hello03(String name, int age ){
		System.out.println("=======> 请求参数 name :" + name + ", age : " + age);
		return "success";
	}
	
	@RequestMapping(value="/hello04", headers={"Connection=close"})
	public String hello04(String connection){
		System.out.println("=======> 请求头参数 connection :" + connection );
		return "success";
	}
	
	@RequestMapping(value="/hello05", method=RequestMethod.DELETE)
	public String hello05(){
		System.out.println("=======> 请求方法进来了....");
		return "redirect:../page/success.html";
	}
}
