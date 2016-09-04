<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="/springmvc-009-data_vaildate/">
<meta charset="UTF-8">
<title>修改员工信息</title>
<style type="text/css">
*{padding:0px; margin:0px;}
#empForm{width:300px; margin:80px auto;}
#empForm fieldset{padding:30px;}

#empForm button {float:right; margin-right:30px;}
</style>
</head>
<body>
	
	<form id="empForm" action="emp/emp" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="hidden" name="id" value="${emp.id }">
		<fieldset>
			<legend>添加员工</legend>
			姓名：
			<input name="lastName" value="${emp.lastName }" required="required" ><br><br>
			邮箱：
			<input type="email" name="email" value="${emp.email}" required="required"><br><br>
			性别：
			<c:choose>
				<c:when test="${emp.gender eq 'male'}">
					<input type="radio" name="gender" value="male" checked="checked">男
					<input type="radio" name="gender" value="female">女<br><br>
				 </c:when>
				 <c:otherwise>
				 	<input type="radio" name="gender" value="male" >男
					<input type="radio" name="gender" value="female" checked="checked">女<br><br>
				 </c:otherwise>
			</c:choose>
			
			部门：
			<select name="department.id" required="required">
				<c:forEach items="${depts }" var="dept">
					<c:choose>
						<c:when test="${dept.id eq emp.department.id}">
							<option value="${dept.id }" selected="selected">${dept.departmentName }</option>
						 </c:when>
						 <c:otherwise>
						 	<option value="${dept.id }">${dept.departmentName }</option>
						 </c:otherwise>
					</c:choose>
				</c:forEach>
			</select><br><br>
			<button>修 改</button>
		</fieldset>
	</form>
</body>
</html>