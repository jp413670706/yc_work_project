<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringMVC</title>
</head>
<body>
	<h1>springmvc响应成功!!!</h1>
	request my user  ==> ${requestScope.my.username }<br><br>
	
	request user user ==> ${requestScope.user.username }<br><br>
	
	session my user  ==> ${sessionScope.my.username }<br><br>
	
	session user user ==> ${sessionScope.user.username }<br><br>
</body>
