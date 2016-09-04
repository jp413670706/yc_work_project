package com.yc.vote.web.handler;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yc.vote.entity.User;
import com.yc.vote.service.UserService;

@Controller
@RequestMapping("/user")
public class UserHandler {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, ModelMap map) {
		System.out.println("user login..." + user);
		System.out.println("我测试一下");
		user = userService.login(user);
		//登录结果的页面跳转
		if(user == null){
			map.put("errorMsg", "用户名或密码错误!!!");
			return "login";
		}
		
		if(user.getVuVersion() == 0){
			map.put("errorMsg", "用户名未激活,请激活后再登录!!!");
			return "login";
		}
		return "index";
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String active(String name) {
		System.out.println("active register user...");
		
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(User user) {
		System.out.println("show register page...");
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap map, HttpServletRequest request) {
		System.out.println("regsiter====>" + user);
		
		
		
		if (bindingResult.hasFieldErrors()) {
			map.put("regErrorMsg", "注册失败!!!");

			bindingResult.addError(new ObjectError("confirmPassword", "密码不相同..."));
			return "register";
		}

		if (userService.register(user)) {
			// 成功注册，发送邮件，激活账号
			
			//接收激活用户的连接地址:
				
			; 
			activeAccountMail("激活用户", 
					getSendContent(request, user.getVuUsername()), 
					"studymail_test@163.com", 
					user.getVuEmail());
			return "login";
		}
		map.put("regErrorMsg", "注册失败!!!");
		return "register";
	}

	private String getSendContent(HttpServletRequest request, String username) {
		String activeURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + 
				request.getContextPath() + "/user/active?username=" + username;
		activeURL = String.format("<a href='%s'>%s</a><br><br>如果连接点击无效，请把此连接%s拷贝到地址栏激活...", activeURL, "激活用户", activeURL);
		
		return activeURL;
	}

	@Autowired
	private JavaMailSender mailSender;

	private void activeAccountMail(String subject, String content, String from, String to) {

		try {
			MimeMessage mm = mailSender.createMimeMessage();
			MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
			mmh.setTo(to); // 设置邮件接收者
			mmh.setFrom(from);
			mmh.setSubject(subject);  //设置主题
			mmh.setText(content, true);  //设置内容
			mailSender.send(mm); //发送邮件

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}