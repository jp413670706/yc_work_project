<%@page import="java.sql.Connection"%>
<%@page import="com.yc.jsp.util.DbHelper"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Connection con = DbHelper.getConn();
	if (con == null){
		out.println("<h1>数据库连接失败！！！！</h1>");
	}else{
		out.println("<h1>数据库连接成功....</h1>");
	}
%>