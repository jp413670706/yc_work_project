<%@page import="com.google.gson.Gson"%>
<%@page import="com.yc.news.util.DbHelper"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--声明--%>
<%!
public List<Map<String, Object>> getAllTopic() throws Exception {
	String sql = "select * from topic";
	return DbHelper.findMultiObject(sql, null);
}

%>

<%--小脚本--%>
<%
	List<Map<String, Object>> topics = getAllTopic();
	Gson gson = new Gson(); // json 与 java对象的转换
	out.println(gson.toJson(topics));  //把java对象转换成json字符串
	out.flush();
%>