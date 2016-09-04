<!-- jsp 指令-->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp学习</title>
</head>
<body>

	<%-- jsp声明 --%>
	<%!
	 public String showHello(int count) {
		String htmlStr = "";
		for (int i = 1; i <= count; i++) {
			htmlStr += String.format("<h%d>大家好， 我是通过jsp输出的内容</h%d>", i, i);
		}
		return htmlStr;
	}%>

	<!-- jsp小脚本 -->
	<%
		out.println(showHello(4));
	%>
	
	<hr>
	
	<!-- jsp的表达式 -->
	<%=showHello(6)%>
</body>
</html>