package com.yc.springmvc.web.handler;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yc.springmvc.entity.User;

@Controller
@RequestMapping("/user" )
public class UserHandler {
	
	
	@RequestMapping("/register")  //使用参数Map/ModelMap进行数据传参, 默认作用域是request
	public String register(User user, ModelMap map, HttpSession session){
		System.out.println("=======> 请求处理的参数对象是 user: " + user );		
		map.put("username", user.getUsername());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("user", user);
		return "success";
	}
	
	@RequestMapping("/register02")  //ModelAndView对象封装数据和视图, 然后把对象返回
	public ModelAndView register02(User user){
		System.out.println("=======> 请求处理的参数对象是 user: " + user );		
		ModelAndView mav = new ModelAndView(); //视图输出数据模型对象
		mav.addObject("username", "this is my data");  //数据
		mav.setViewName("success"); //视图
		return mav;
	}
	
	
}
