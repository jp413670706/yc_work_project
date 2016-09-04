package com.yc.springmvc.web.handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.springmvc.entity.User;

@Controller
@RequestMapping("/user" )
public class UserHandler {
	
	/*
	 * @RequestParam:
	 * 	name表示与请求参数的key绑定
	 * 	value表示别名 ：请求参数的key别名
	 *  required 表示必须有值
	 *  defaultvalue 表示没 有取到值时，默认的值
	 *  
	 *  注意： 基本类型如果没 有值会抛出异常, 1.改为包装类， 2.设置默认值
	 */
	
	@RequestMapping("/register" )
	public void register(User user, PrintWriter out){
		System.out.println("=======> 请求处理的参数对象是 user: " + user );
		 
		out.println("<h1>i want like play ，OK</h1>");
		out.flush();
		out.close();
	}
	
	@RequestMapping("/register02" )
	public String register02(User user, HttpServletRequest request){
		System.out.println("=======> 请求处理的参数对象是 user: " + user );
		
		String name = request.getParameter("username");
		System.out.println("name ==> " + name);
		request.setAttribute("my", user); //向request存入一个对象
		return "success";
	}
}
