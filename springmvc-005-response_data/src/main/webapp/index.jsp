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


<form action="user/register" method="post" style="width:200px; margin:20px auto;">
	<fieldset>
		<legend>用户注册</legend>
		用户名：<input type="text" name="username" value="admin"/><br><br>
		密码：<input type="password" name="password" value="12345"/><br><br>
		年龄：<input type="text" name="age" value="23"/><br><br>
		省份：<input type="text" name="address.province" value="湖南省"/><br><br>
		城市：<input type="text" name="address.city" value="衡阳市"/><br><br>
		<button>注册</button>
	</fieldset>
</form>
</body>
</html>
