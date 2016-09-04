<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录测试</title>
<style type="text/css">
	form{width:200px; margin:80px auto;}
	
	form #btn {float:right; margin-right: 50px;}
	
	form label{color:red;}
</style>
</head>
<body>

	<!-- 使用session做用户登录校验:
			通过查找 在session是否有登录信息， 如果 没有表示没有登录或登录失败， 有表示登录过 
	 -->
	<form action="controll.jsp" method="post">
		
		<%--通过属性名，在request的对象中取出存储的数据 --%>
		 <label><%=request.getAttribute("errorMsg") == null ? "&nbsp;" : request.getAttribute("errorMsg")%>
		 <%=session.getAttribute("errorMsg") == null ? "&nbsp;" : session.getAttribute("errorMsg")%>
		 <%session.removeAttribute("errorMsg"); %>
		 </label><br><br> 
		账号：<input name="username" value="" 
		required="required" placeholder="请输入用户名"/><br><br>
	
		密码：<input type="password" name="password" value="" 
		required="required" placeholder="请输入密码"/><br><br>
		
		<input id="btn" type="submit"  value="登录"/>
	
	</form>
</body>
</html>