<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="/jsp_007_mvc/">
<meta charset="utf-8">
<title>登录测试</title>
<style type="text/css">
form {
	width: 200px;
	margin: 80px auto;
}

form button {
	float: right;
}

form label {
	color: red;
}

form input {
	width: 200px;
}

form a {
	font-size: 13px;
	padding-left: 5px;
}

form a:HOVER, form a:VISITED {
	color: blue
}

form input, form img {
	vertical-align: middle;
}
</style>
</head>
<body>
	<!--"/" 表示是tomcat中发布项目目录webapps下  -->
	<form action="user/login" method="post">
		<%--通过属性名，在request的对象中取出存储的数据 --%>
		<label><%=request.getAttribute("errorMsg") == null ? "&nbsp;" : request.getAttribute("errorMsg")%></label><br>
		<br> <input name="username" value="admin" required="required"
			placeholder="请输入用户名" /><br>
		<br> <input type="password" name="password" value="a"
			required="required" placeholder="请输入密码" /><br>
		<br> <input name="imageCode" required="required"
			placeholder="验证码" /> <img id="imgCode" src="randImg.jpg"
			title="看不清，换一张"><a href="javascript:void(0)" >换一张</a><br>
		<br>

		<button>登&nbsp;录</button>

	</form>
	
	<a href="user/login">我想get请求一下</a>

	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		$("input[name='imageCode']").css("width", "80px");

		$("#imgCode, form a").click(function() {
			$("#imgCode").attr("src", "randImg.jpg?" + new Date().getTime());
		});
	</script>
</body>
</html>