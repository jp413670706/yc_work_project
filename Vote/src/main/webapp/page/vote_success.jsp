<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<jsp:include page="top.jsp" />
<div id="message" class="box">
	<h2>提示信息</h2>
	<div class="content">
		<p>恭喜：投票成功！<a href="Vote!view.action?entityId=<s:property value='subject.id'/>">返回查看投票结果&gt;&gt;</a></p>
	</div>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>
