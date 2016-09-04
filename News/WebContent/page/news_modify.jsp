<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="/News/">
<meta charset="utf-8" />
<title>修改新闻--管理后台</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="header">
		<div id="welcome">欢迎使用新闻管理系统！</div>
		<div id="nav">
			<div id="logo">
				<img src="images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="images/a_b01.gif" alt="" />
			</div>
		</div>
	</div>
	<div id="admin_bar">
		<div id="status">
			管理员： 登录 &#160;&#160;&#160;&#160;<a href="#">login out</a>
		</div>
		<div id="channel"></div>
	</div>
	<div id="main">
		<div id="opt_list">
			<ul>
				  <li><a href="newspages/news_add.jsp">添加新闻</a></li>
			      <li><a href="admin.jsp">编辑新闻</a></li>
			      <li><a href="newspages/news_read.jsp">查找新闻</a></li>
			      <li><a href="newspages/topic_add.jsp">添加主题</a></li>
			      <li><a href="newspages/news_add.jsp">编辑主题</a></li>
			</ul>
		</div>
		<div id="opt_area">
			<h1 id="opt_type">添加新闻：</h1>
			<form action="news/update.action" method="post" id="addNews" enctype="multipart/form-data">
				<%
				Map<String, Object> news = (Map<String, Object>)request.getAttribute("news");
				%>
				<p>
					<label> 主题 </label> <select name="ntid" id="ntid">
						<option value="<%=news.get("NTID") %>"></option>
					</select>
				</p>
				<p>
					<label> 标题 </label> <input name="ntitle" type="text" value="<%=news.get("NTITLE") %>"
						class="opt_input" />
				</p>
				<p>
					<label> 作者 </label> <input name="nauthor" type="text" value="<%=news.get("NAUTHOR") %>"
						class="opt_input" />
				</p>
				<p>
					<label> 摘要 </label>
					<textarea name="nsummary" cols="40" rows="3"><%=news.get("NSUMMARY") %></textarea>
				</p>
				<p>
					<label> 内容 </label>
					<textarea name="ncontent" cols="70" rows="10"><%=news.get("NCONTENT") %></textarea>
				</p>
				<p>
					<label> 上传图片 </label> <input name="file" type="file"
						onchange="addPic(this)" multiple="multiple"/><br>
					<img alt="" src="<%=news.get("NPICPATH") == null ? "images/not_pic.jpg" : news.get("NPICPATH")%>" width="100">
				</p>
				<p id="pics"></p>
				
				<input name="nid" type="hidden" value="<%=news.get("NID") %>"> 
				<input type="button" value="修改" class="opt_sub" onclick="checkCode()"/> 
					<input type="reset" value="重置" class="opt_sub" />
			</form>
		</div>
	</div>
	<div id="site_link">
		<a href="#">关于我们</a><span>|</span> <a href="#">Aboue Us</a><span>|</span>
		<a href="#">联系我们</a><span>|</span> <a href="#">广告服务</a><span>|</span>
		<a href="#">供稿服务</a><span>|</span> <a href="#">法律声明</a><span>|</span>
		<a href="#">招聘信息</a><span>|</span> <a href="#">网站地图</a><span>|</span>
		<a href="#">留言反馈</a>
	</div>
	<div id="footer">
		<p class="">
			24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a>
			&#160;&#160;&#160;&#160; 新闻热线：010-627488888<br />
			文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱：<a href="#">jubao@jb-aptech.com.cn</a>
		</p>
		<p class="copyright">
			Copyright &copy; 1999-2009 News China gov, All Right Reserver<br />
			新闻中国 版权所有
		</p>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
	$.post("controll/topicControll.jsp", function(data){
		var currValue = $("#ntid").val();
		$("#ntid").empty();
		for(var i = 0 ; i < data.length; i++){
			$("#ntid").append("<option value='" + data[i].TID + "' " + 
					(currValue == data[i].TID ? "selected" : "") + ">" + data[i].TNAME + "</option>");
		}
	}, "json");
	</script>
</body>
</html>
