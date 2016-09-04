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
	request username  ==> ${requestScope.username }<br><br>

	session username  ==> ${sessionScope.username }<br><br>	
	
	request user  ==> ${requestScope.user.username }<br><br>
	
	session user  ==> ${sessionScope.user.username }<br><br>
	
	request abc  ==> ${requestScope.abc.username }<br><br>
	
	session abc  ==> ${sessionScope.abc.username }<br><br>
</body>
