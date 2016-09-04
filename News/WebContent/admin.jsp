<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Object name = null;
	if((name = session.getAttribute("loginUser")) == null){
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>添加主题--管理后台</title>
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
			管理员：<span style='color:green;'><%=name %></span>登录 &#160;&#160;&#160;&#160;
			<a href='javascript:void(0)' onclick='logout()'>注销用户</a>
		</div>
		<div id="channel"></div>
	</div>
	<div id="main">
		<div id="opt_list">
			<ul>
				<li><a href="#">添加新闻</a></li>
				<li><a href="#">编辑新闻</a></li>
				<li><a href="#">查找新闻</a></li>
				<li><a href="#">添加主题</a></li>
				<li><a href="#">编辑主题</a></li>
			</ul>
		</div>
		<div id="opt_area">
			<ul class="classlist" id="newsList"></ul>
			<p align="right" id="pagation"></p>
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
	<script>
			function clickdel(){
				return confirm("删除请点击确认");
			}
			
			//6.注销操作
			function logout(){
				if(confirm("是否确定注销此用户?")){
					$.post("controll/userControll.jsp", function(data){
						if(data){
							location.reload();
						}
					}, "json");
				}
			}
			
			//异步取到新闻分页数据
			function getNews(pageNum){
				$.get("controll/newsControll.jsp?size=15&page=" + pageNum, function(data){
					$("#newsList").empty();
					var newses = data.newses;
					for(var i = 0; i < newses.length; i++){
						if(i % 5 == 0 && i != 0){
							$("#newsList").append("<li class='space'></li>");
						}
						$("#newsList").append("<li>" + newses[i].NTITLE + "<span> 作者：" + newses[i].NAUTHOR + 
								"&#160;&#160;&#160;&#160; <a href='controll/newsControll.jsp?type=byId&id=" + 
										newses[i].NID + "'>修改</a>&#160;&#160;&#160;&#160; " + 
								"<a href='#' onclick='return clickdel()'>删除</a></span></li>");
					}
					
					$("#pagation").empty();
					$("#pagation").append("当前页数:[" + data.currPage + "/" + data.totalPage + "]&nbsp; " +
							"<a href='javascript:void(0)' onclick='getNews(1)'>首页</a>&nbsp;&nbsp;" +
							"<a href='javascript:void(0)' onclick='getNews(" + (data.currPage - 1)+ ")'>上一页</a>&nbsp;&nbsp;" +
							"<a href='javascript:void(0)' onclick='getNews(" + (data.currPage + 1)+ ")'>下一页</a>&nbsp;&nbsp;" +
							"<a href='javascript:void(0)' onclick='getNews(" + data.totalPage + ")'>末页</a>");
					
				}, "json");
			}
			getNews(1);
			
	</script>
</body>
</html>
