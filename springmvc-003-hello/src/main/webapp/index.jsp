<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringMVC</title>
</head>
<body>
<h2>Hello World!</h2>

<form action="hello/hello05" method="post">
	<button>测试POST请求</button>
</form>

<form action="hello/hello05" method="post">
	<input type="hidden" name="_method" value="PUT">
	<button>测试PUT请求</button>
</form>

<form action="hello/hello05" method="post">
	<input type="hidden" name="_method" value="DELETE">
	<button>测试DELETE请求</button>
</form>
</body>
</html>
