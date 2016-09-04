<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加用户</title>
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

#pic{
	width:100px;
	height:100px;
	border:1px solid gray
}
</style>
</head>
<body>
	<form action="addControll.jsp" method="post" enctype="multipart/form-data">
		<%--通过属性名，在request的对象中取出存储的数据 --%>
		<label><%=request.getAttribute("errorMsg") == null ? "&nbsp;" : request.getAttribute("errorMsg")%></label><br>
		<br> <input name="username" value="admin" required="required"
			placeholder="请输入用户名" /><br>
		<br> <input type="password" name="password" value="a"
			required="required" placeholder="请输入密码" /><br>
		<br> <input type="file" name="pic" 
			required="required" onchange="changePic(this)"/><br>
			<img  id="pic" alt="" src="image/not_pic.jpg" ><br><br>
		<button>添&nbsp;加</button>

	</form>

	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		function changePic(obj){
			var picURl = window.URL.createObjectURL(obj.files[0]);  //根据图片资源创建一个图片路径
			$("#pic").attr("src", picURl);
		}
	</script>
</body>
</html>