package com.yc.springmvc.web.handler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.springmvc.entity.User;

@Controller
@RequestMapping("/user" )
/*
 * @SessionAttributes 在session作用域中存储数据
 * value 别名
 * name session绑定的key
 * types session绑定的value的数据类型的类的类对象
 * 
 */
@SessionAttributes(value={"user"}, types={String.class})
public class UserHandler {
	
	
	@ModelAttribute
	public void getModel(Map<String, Object> map){
		map.put("user", new User());
		map.put("username", "小明");
		map.put("abc", new User("zhangsan", "abc", 34));
	}
	
	@RequestMapping(value="/register")  //使用参数Map/ModelMap进行数据传参, 默认作用域是request
	
	//如果使用@SessionAttributes, 请求参数的对象，不会自己创建对象，会去session去查询对象， 找到就使用此对象， 没有就抛出异常
	//去session去查询对象时， 默认是根据类名首字母小写去查询, 
		//也可以通过@ModelAttribute（xxx）指定的xxx去查询 ， 没有查询到不会抛出异常, 
	
	public String register(User user){
		System.out.println("=======> 请求处理的参数对象是 user: " + user );	
		//return "success";
		//return "redirect:/page/success.jsp";   //重定向 使用"redirect:"为前缀, 后面内容是重定向页面的路径
		return "forward:/page/success.jsp";   //转发 使用"redirect:"为前缀, 后面内容是重定向页面的路径
		
	}
}
