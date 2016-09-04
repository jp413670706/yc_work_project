<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="/EmpManagerSystem/">
<meta charset="UTF-8">
<title>添加员工信息</title>
<style type="text/css">
*{padding:0px; margin:0px;}
#empForm{width:300px; margin:80px auto;}
#empForm fieldset{padding:30px;}

#empForm button {float:right; margin-right:30px;}
</style>
</head>
<body>
	
	<form id="empForm" action="emp/emp" method="post">
		<fieldset>
			<legend>添加员工</legend>
			姓名：
			<input name="lastName" value="yc" required="required"><br><br>
			邮箱：
			<input type="email" name="email" value="yc@126.com" required="required"><br><br>
			性别：
			<input type="radio" name="gender" value="male" checked="checked">男
			<input type="radio" name="gender" value="female">女<br><br>
			部门：
			<select name="department.id" required="required">
				<c:forEach items="${depts }" var="dept">
					<option value="${dept.id }">${dept.departmentName }</option>
				</c:forEach>
			</select><br><br>
			<button>提 交</button>
		</fieldset>
	</form>
</body>
</html>