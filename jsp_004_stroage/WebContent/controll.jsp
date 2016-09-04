<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>测试</title>
</head>
<body>
	<%
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("我来过了这里....");

		if (name.intern() == "admin" && password.intern() == "admin") {
			session.setAttribute("name", name);  //向session中存储值
			response.sendRedirect("success.jsp"); 
			//session.setMaxInactiveInterval(15*60);
			//session.invalidate();
		} else {
			request.setAttribute("errorMsg", "账号或密码错误！！！");  //把传递数据给request对象存储
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			out.clear();
			out = pageContext.pushBody();
		}
	%>
</body>
</html>