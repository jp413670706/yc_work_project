<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>新闻中国</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="header">
		<div id="top_login">
			<form onsubmit="return login()" id="loginForm">
			<%
			Object name = session.getAttribute("loginUser");
			if(name == null){
				%>
				<label> 登录名 </label> 
				<input class="login_input" name="username" required="required" placeholder="输入用户名"/> 
				<label> 密&#160;&#160;码 </label> 
				<input type="password" class="login_input" name="password" required="required" placeholder="输入密码"/> 
				<input type="submit" class="login_sub" value="登录" /> 
				<label id="error"></label> 
				<% 
			}else{
				%>
				<label>欢迎<span style='color:green;'><%=name %></span>使用本网站&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
				<a href='javascript:void(0)' onclick='logout()'>注销用户</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href='admin.jsp'>管理后台</a></label>
				<%
			}
			%>
			</form>
			<img src="images/friend_logo.gif" alt="Google"
			id="friend_logo" />
		</div>
		<div id="nav">
			<div id="logo">
				<img src="images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="images/a_b01.gif" alt="" />
			</div>
			<!--mainnav end-->
		</div>
	</div>
	<div id="container">
		<div class="sidebar">
			<h1>
				<img src="images/title_1.gif" alt="国内新闻" />
			</h1>
			<div class="side_list">
				<ul id="china"></ul>
			</div>
			<h1>
				<img src="images/title_2.gif" alt="国际新闻" />
			</h1>
			<div class="side_list">
				<ul id="inter"></ul>
			</div>
			<h1>
				<img src="images/title_3.gif" alt="娱乐新闻" />
			</h1>
			<div class="side_list">
				<ul id="happy"></ul>
			</div>
		</div>
		<div class="main">
			<div class="class_type">
				<img src="images/class_type.gif" alt="新闻中心" />
			</div>
			<div class="content">
				<ul class="class_date">
					<li id='class_month'></li>
				</ul>
				<ul class="classlist" id="newsList"></ul>
				<p align="right" id="pagation"></p>
			</div>
			<div class="picnews">
				<ul>
					<li><a href="#"><img src="images/Picture1.jpg" width="249"
							alt="" /> </a><a href="#">幻想中穿越时空</a></li>
					<li><a href="#"><img src="images/Picture2.jpg" width="249"
							alt="" /> </a><a href="#">国庆多变的发型</a></li>
					<li><a href="#"><img src="images/Picture3.jpg" width="249"
							alt="" /> </a><a href="#">新技术照亮都市</a></li>
					<li><a href="#"><img src="images/Picture4.jpg" width="249"
							alt="" /> </a><a href="#">群星闪耀红地毯</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="friend">
		<h1 class="friend_t">
			<img src="images/friend_ico.gif" alt="合作伙伴" />
		</h1>
		<div class="friend_list">
			<ul>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
				<li><a href="#">中国政府网</a></li>
			</ul>
		</div>
	</div>
	<div id="footer">
		<p class="">
			24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a>
			&#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
			文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱：
			 <a href="#">jubao@jb-aptech.com.cn</a>
		</p>
		<p class="copyright">
			Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
			新闻中国 版权所有
		</p>
	</div>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
	
		//异步取到新闻分页数据
		function getNews(pageNum){
			$.get("controll/newsControll.jsp?page=" + pageNum, function(data){
				$("#newsList").empty();
				var newses = data.newses;
				for(var i = 0; i < newses.length; i++){
					if(i % 5 == 0 && i != 0){
						$("#newsList").append("<li class='space'></li>");
					}
					$("#newsList").append("<li><a href='newspages/news_add.html'>" + newses[i].NTITLE + 
							"</a><span> " + newses[i].NCREATEDATE + " </span></li>");
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
		
		
		//2.异步取到所有主题信息
		$.post("controll/topicControll.jsp", function(data){
			$("#class_month").empty();
			for(var i = 0 ; i < data.length; i++){
				$("#class_month").append("<a href='#'><b>" + data[i].TNAME + "</b></a>");
			}
		}, "json");
		
		
		//3.异步取到根据新闻类型的新闻信息
		$("#china").empty();
		$("#inter").empty();
		$("#happy").empty();
		$.get("controll/newsControll.jsp?type=125", function(data){
			for(var i = 0; i < data.length; i++){
				if (data[i].NTID == 1){
					$("#china").append("<li><a href='#'><b>" + data[i].NTITLE + "</b></a></li>");
				}else if (data[i].NTID == 2){
					$("#inter").append("<li><a href='#'><b>" + data[i].NTITLE + "</b></a></li>");
				}else if (data[i].NTID == 5){
					$("#happy").append("<li><a href='#'><b>" + data[i].NTITLE + "</b></a></li>");
				}
			}
		}, "json");
		
		//4.异步登录操作
		function login(){
			var params = $("#loginForm").serialize();  //取到表单要提交的请求数据		
			$.post("controll/userControll.jsp", params, function(data){
				if(data){
					$("#loginForm").html("<label>欢迎<span style='color:green;'>"+ $("#loginForm [name='username']").val() + 
					"</span>使用本网站&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href='javascript:void(0)' onclick='logout()'>注销用户</a>"+
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href='admin.jsp'>管理后台</a></label>");
				}else{
					$("#error").html("用户名或密码错误！！！");
					$("#error").css({"color":"red"});
				}
			}, "json");
			alert(params);
			return false;
		}
		
		//5.注销操作
		function logout(){
			if(confirm("是否确定注销此用户?")){
				$.post("controll/userControll.jsp", function(data){
					if(data){
						$("#loginForm").html('<label> 登录名 </label>' +  
								'<input class="login_input" name="username" required="required" placeholder="输入用户名"/>' +  
								'<label> 密&#160;&#160;码 </label>' +  
								'<input type="password" class="login_input" name="password" required="required" placeholder="输入密码"/> ' +  
								'<input type="submit" class="login_sub" value="登录" />' +  
								'<label id="error"></label> ');
					}
				}, "json");
			}
		}
	</script>
</body>
</html>
