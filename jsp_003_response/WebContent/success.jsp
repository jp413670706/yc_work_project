<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>成功</title>
</head>
<body>
	<%
		//取到所有的cookie数据
		//List<Cookie> cs = Arrays.asList(request.getCookies());  //把数组转换为集合
		Cookie[] cs = request.getCookies();
		
		String name = "";
		
		for(Cookie c : cs){
			if(c.getName().intern() == "username"){
				name = c.getValue();
			}
		}
	%>

	<h1>欢迎<%=name %>， 登录成功....</h1>
</body>
</html>