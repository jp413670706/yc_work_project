<%@page import="com.yc.news.util.DbHelper"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--声明--%>
<%!
public boolean findUser(String username, String password) throws Exception {
	String sql = "select 1 from news_users where uname = ? and upwd = ?";
	return DbHelper.findSingleObject(sql, Arrays.asList(new Object[]{username, password})) != null;
}

%>

<%--小脚本--%>
<%
	if(session.getAttribute("loginUser") == null){  //判断是否是登录
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(findUser(username, password)){
			session.setAttribute("loginUser", username);
			out.println(true); 
		}else{
			out.println(false);
		}
	}else{
		session.removeAttribute("loginUser");
		out.println(true); 
	}
	
	out.flush();
%>