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
	<form action="controll.jsp" method="post">
		<%
			request.setCharacterEncoding("utf-8");
		
			//模拟实现自动登录
			//取出cookie中的数据
			Cookie[] cs = request.getCookies();  //取到所有的cookie数据
			
			//cookie中的数据 以key :value的形式存储， 并且值的类型都是String
			String name = null;
			String  password = null;
			if(cs != null){
				for(Cookie c : cs){
					if(c.getName().intern() == "username"){
						name = c.getValue();
					}
					if(c.getName().intern() == "password"){
						password = c.getValue();
					}
				}
			}
			
			//判断cookie中是保存了正确的账号和密码
			if (name != null && password != null && name.intern() == "admin" && password.intern() == "admin"){
				response.sendRedirect("success.jsp");
				return;
			}
			
			
			//第一次请求是没有cookie
		
		%>
		<%-- <label><%=request.getParameter("errorMsg") %></label><br><br> --%>
		
		<%--通过属性名，在request的对象中取出存储的数据 --%>
		 <label><%=request.getAttribute("errorMsg") == null ? "&nbsp;" : request.getAttribute("errorMsg")%></label><br><br> 
		账号：<input name="username" value="" 
		required="required" placeholder="请输入用户名"/><br><br>
	
		密码：<input type="password" name="password" value="" 
		required="required" placeholder="请输入密码"/><br><br>
		
		<input id="btn" type="submit"  value="登录"/>
	
	</form>
</body>
</html>