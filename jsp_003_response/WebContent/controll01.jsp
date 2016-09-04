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
		//模拟登录
		//账号、密码都等于admin时，成功
		//if("admin".equals(name) && "admin".equals(password)){

		if (name.intern() == "admin" && password.intern() == "admin") {
			//登录 成功， 转发到success.jsp页面
			request.getRequestDispatcher("success.jsp").forward(request,
					response);
		} else {
			//登录  失败， 转发到index.jsp页面
			/* String errorMsg = "账号或密码错误！！！";
			request.getRequestDispatcher("index.jsp?errorMsg=" + errorMsg)
					.forward(request, response); */
					
			request.setAttribute("errorMsg", "账号或密码错误！！！");  //把传递数据给request对象存储
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	%>
</body>
</html>