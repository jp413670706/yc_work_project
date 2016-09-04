<%@page import="com.yc.jsp.util.Encrypt"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.yc.jsp.util.DbHelper"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%!public boolean login(String name, String password) throws Exception {
		String sql = "select 1 from users where username=? and password=?";
		
		return DbHelper.findSingleObject(sql, Arrays.asList(new Object[] { name, Encrypt.md5AndSha(password)})) != null;
}%>

<%
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

	if (login(name, password)) { //登录处理
		//重定向
		session.setAttribute("user", name); //保存登录用户信息
		response.sendRedirect("manage.jsp");
	} else {
		request.setAttribute("errorMsg", "账号或密码错误！！！"); //把传递数据给request对象存储
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
%>
