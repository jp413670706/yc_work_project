package com.yc.jsp.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.jsp.util.DbHelper;
import com.yc.jsp.util.Encrypt;

public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 3261276555502805699L;

	/*
	@Override  //可以处理任何请求方式
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("这是servlet, 在这里写java业务算处理太爽~！！！");
		HttpSession session = request.getSession(); //取到session对象
		//验证码处理
		Object rCode = session.getAttribute("rCode"); //取到自动生成的验证码
		String inputCode = request.getParameter("imageCode"); //输入的验证码

		if (!inputCode.equals(rCode)) { //判断验证是否正确
			request.setAttribute("errorMsg", "验证错误！！！"); //把传递数据给request对象存储
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			if (login(name, password)) { //登录处理
				//重定向
				session.setAttribute("user", name); //保存登录用户信息
				response.sendRedirect("../manage.jsp");
			} else {
				request.setAttribute("errorMsg", "账号或密码错误！！！"); //把传递数据给request对象存储
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	@Override  //只处理get请求
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是get....................");
		doPost(req, resp);
	}

	@Override //只处理post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("这是servlet, 在这里写java业务算处理太爽~！！！");
		HttpSession session = request.getSession(); //取到session对象
		//验证码处理
		Object rCode = session.getAttribute("rCode"); //取到自动生成的验证码
		String inputCode = request.getParameter("imageCode"); //输入的验证码

		if (!inputCode.equals(rCode)) { //判断验证是否正确
			request.setAttribute("errorMsg", "验证错误！！！"); //把传递数据给request对象存储
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			if (login(name, password)) { //登录处理
				//重定向
				session.setAttribute("user", name); //保存登录用户信息
				response.sendRedirect("../manage.jsp");
			} else {
				request.setAttribute("errorMsg", "账号或密码错误！！！"); //把传递数据给request对象存储
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean login(String name, String password) throws Exception {
		String sql = "select 1 from users where username=? and password=?";
		
		return DbHelper.findSingleObject(sql, Arrays.asList(new Object[] { name, Encrypt.md5AndSha(password)})) != null;
}

	

}
