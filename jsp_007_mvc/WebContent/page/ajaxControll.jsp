<%@page import="com.yc.jsp.util.DbHelper"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%!
	public boolean usernameExists(String name) throws Exception{
		String sql = String.format("select 1 from users where username = '%s'" , name);
		return DbHelper.findSingleObject(sql) != null;
	}
%>
<%
	//处理异步请求
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("username");
	//System.out.println("name ==> " + name );
	//out.println("我进来 了， 我是异步响应!!!");
	// json : 对象或map 使用{},  集合或数组使用[]
	// 属性名， 方法， 字符串类型的属性值, 使用双引号引起	
	
	out.println("{\"isCheck\":" + usernameExists(name) + "}");
	out.flush();

%>