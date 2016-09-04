<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<base href="/springmvc-009-data_vaildate/">
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

	<f:form action="emp/emp" method="post" modelAttribute="emp" id="empForm">
		<fieldset>
			<legend>添加员工</legend>
			姓名：<f:input path="lastName" name="lastName" value="yc"/>
				  <f:errors path="lastName" cssStyle="color:red;"/><br><br>
			邮箱：<f:input path="email" type="email" name="email" value="yc@126.com" /><br><br>
			性别：<f:radiobutton path="gender" value="male" checked="checked"/>男
			<f:radiobutton path="gender" value="female" checked="checked"/>女<br><br>
			部门：
			<f:select path="department.id" name="department.id">
				<c:forEach items="${depts }" var="dept">
					<f:option value="${dept.id }">${dept.departmentName }</f:option>
				</c:forEach>
			</f:select><br><br>
			出生：
			<f:input type="date" path="birthday" name="birthday" value="2016-10-8" />
			<f:errors path="birthday" cssStyle="color:red;"/><br><br>
			工资：
			<f:input path="salary" name="salary" value="14,523.9" /><br><br>
			<button>提 交</button>
		</fieldset>
	</f:form>
</body>
</html>