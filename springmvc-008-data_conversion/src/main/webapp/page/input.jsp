<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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
		<label style="color:red;"><f:errors path="birthday"/></label>
		<fieldset>
			<legend>添加员工</legend>
			<!-- 输入值：姓名，邮箱，性别，部门  
				如：yc, yc@126.com, male, 101
			
			员工信息：<input name="emp" value="yc, yc@126.com, male, 101" required="required"><br><br>
			-->
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
			出生：
			<input type="date" name="birthday" value="2016-10-8" required="required"><br><br>
			工资：
			<input name="salary" value="14,523.9" required="required"><br><br>
			<button>提 交</button>
		</fieldset>
	</form>
</body>
</html>