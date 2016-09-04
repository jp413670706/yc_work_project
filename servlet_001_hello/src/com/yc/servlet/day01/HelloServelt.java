package com.yc.servlet.day01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServelt extends HttpServlet {
	private static final long serialVersionUID = -3718295922835090849L;
	
	////请求处理实例化方法
	public HelloServelt() {
		System.out.println("-------------------HelloServelt()实例化方法-----------------");
	}

	@Override  //请求处理方法
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------------service()请求处理方法-----------------");
		
		response.setCharacterEncoding("utf-8");  //设置响应页面的字符编码集
		response.setContentType("text/html; charset=UTF-8");  //设置响应页面 内容的字符编码集
		
		PrintWriter out = response.getWriter();  //取到数据输出流
		  out.write("\r\n");
	      out.write("<!DOCTYPE html>\r\n");
	      out.write("<html>\r\n");
	      out.write("<head>\r\n");
	      out.write("<meta charset=\"UTF-8\">\r\n");
	      out.write("<title>servlet学习</title>\r\n");
	      out.write("</head>\r\n");
	      out.write("<body>\r\n");
	      out.write("\t<a href=\"hello\">测试servlet的请求</a>\r\n");
	      out.println("<h1>我这是servlet响应数据....</h1>");
	      out.write("</body>\r\n");
	      out.write("</html>");
		out.flush();
		out.close();
	}

	@Override  //请求处理销毁方法
	public void destroy() {
		System.out.println("-------------------destroy()销毁方法-----------------");
	}

	@Override //请求处理初始方法方法
	public void init() throws ServletException {
		System.out.println("-------------------init()初始方法方法-----------------");
	}
	
	
	
}
