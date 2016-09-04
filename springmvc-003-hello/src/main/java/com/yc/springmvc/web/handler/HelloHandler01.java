package com.yc.springmvc.web.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloHandler01 {
	
	/*
	 * @RequestMapping : ����ӳ�䴦��, �������η���(������Դ)�� ��(������Դ��ǰ׺)
	 * 	
	 * ������Դͨ��: *(��ʾ����һ��Ŀ¼) ?(��ʾ����һ���ַ�) **��ʾ�༶Ŀ¼
	 * 
	 * params �������������������Լ��:  ����Լ������������ʾ�� ��������־���No matching handler method found for servlet request
	 * param=value    ��ʾ����ĳֵ
	 * param!=value    ��ʾ������
	 *  
	 * headers: ������������ͷ������Լ����paramsһ��
	 * 
	 */
	
	@RequestMapping("/hello")
	public String hello01(){
		System.out.println("=======> springmvc���ʹ��������.....");
		return "success";
	}
	
	@RequestMapping("/**/hello02")
	public String hello02(){
		System.out.println("=======> springmvc���ʹ��������.....");
		return "success";
	}
	
	
	@RequestMapping(value="/hello03", params={"name=yc", "age"})
	public String hello03(String name, int age ){
		System.out.println("=======> ������� name :" + name + ", age : " + age);
		return "success";
	}
	
	@RequestMapping(value="/hello04", headers={"Connection=close"})
	public String hello04(String connection){
		System.out.println("=======> ����ͷ���� connection :" + connection );
		return "success";
	}
	
	@RequestMapping(value="/hello05", method=RequestMethod.DELETE)
	public String hello05(){
		System.out.println("=======> ���󷽷�������....");
		return "redirect:../page/success.html";
	}
}
