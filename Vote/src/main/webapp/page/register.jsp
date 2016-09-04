<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<base href="/Vote/">
<meta charset="utf-8" />
<title>注   册</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="search">
	<!--  
		<form method="get" action="index.html">
			<input type="text" name="keywords" class="input-text" /><input type="submit" name="submit" class="input-button" value="" />
		</form>
	-->	
	</div>
</div>
<div id="register" class="box">
	<h2>新用户注册</h2>
	<div class="content">
	  <%--  <form method="post" action="user/register">
			<dl>
				<!-- <dt>用户ID：</dt>
				<dd><input type="text" class="input-text" name="userId" /></dd> -->
				<dt>用户名：</dt>
				<dd><input type="text" class="input-text" name="vuUsername" /></dd>
				<dt>密码：</dt>
				<dd><input type="password" class="input-text" name="vuPassword" /></dd>
				<dt>确认密码：</dt>
				<dd><input type="password" class="input-text" name="confirmPassword" /></dd>
				<dt></dt>
				<dd><input type="submit" class="input-button" value=""/></dd>
			</dl>
		</form> --%>
		<f:form method="post" action="user/register"  modelAttribute="user">
			<dl>
				<dt>用户名：</dt>
				<dd>
					<f:input path="vuUsername" class="input-text" name="vuUsername" required="required" placeholder="请输入用户名"/>
					<f:errors path="vuUsername" cssClass="error"/>
				</dd>
				<dt>密码：</dt>
				<dd>
					<f:password path="vuPassword" class="input-text" name="vuPassword" required="required" placeholder="请输入密码 "/>
					<f:errors path="vuPassword" cssClass="error"/>
				</dd>
				<dt>确认密码：</dt>
				<dd>
					<f:password path="confirmPassword"  class="input-text" name="confirmPassword" required="required" placeholder="请输入确认密码"/>
					<f:errors path="confirmPassword" cssClass="error"/>
				</dd>
				<dt>电子邮箱：</dt>
				<dd>
					<f:input type="email" path="vuEmail"  class="input-text" name="vuEmail" required="required" placeholder="请输入j电子邮箱"/>
					<f:errors path="vuEmail" cssClass="error"/>
				</dd>
				<dt></dt>
				<dd><input type="submit" class="input-button" value=""/></dd>
			</dl>
		</f:form>
		<div class="error">${regErrorMsg }</div>
	</div>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>
