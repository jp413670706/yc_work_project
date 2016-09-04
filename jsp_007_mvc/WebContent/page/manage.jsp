<%@page import="com.yc.jsp.entity.UserBean"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="/jsp_007_mvc/">
<meta charset="utf-8">
<title>用户信息列表</title>
<style type="text/css">
	table{
		width:960px;
		margin:80px auto;
		border-collapse: 0px;
		border-spacing: 0px;
		text-align:center;
	}
	table caption{height:30px; font-size:30px; font-weight: bold; padding: 10px;}
	table tr{height:30px}
	
	table #end{
		text-align:right;
	}
	
	a:HOVER, a:VISITED {
	color:blue
	}
</style>
</head>
<body>
	
	<table border="1">
		<caption>欢迎<%=session.getAttribute("user")%>， 登录成功....</caption>
		<tr>
			<th>用户编号</th>
			<th>用户名</th>
			<th>用户密码</th>
			<th>用户图像</th>
			<th>操作</th>
		</tr>
		<%
	
		Object obj = request.getAttribute("userBean");
		if(obj == null){
			out.println("<tr><td>没有数据...</td></tr>");
		}else{
			
		UserBean userBean = (UserBean)obj;
		
		List<Map<String, Object>> users = userBean.getUsers();
		for(int i = 0; i < users.size(); i++){
			%>
			<tr>
				<td><%=users.get(i).get("ID") %></td>
				<td><%=users.get(i).get("USERNAME")%></td>
				<td><%=users.get(i).get("PASSWORD")%></td>
				<td><img width="100" src="<%=users.get(i).get("PICPATH") == null ? 
						"image/not_pic.jpg" : users.get(i).get("PICPATH")%>"/></td>
				<td><a href="#">编辑</a>&nbsp;&nbsp;<a href="#">删除</a></td>
			</tr>
			<%
			}
		%>
		
		<tr id="end">
			<td colspan="2" style="border-right:none;">
				<label>当前是第<%=userBean.getCurrPage()%>页， 共有<%=userBean.getTotalPage()%>页</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label><a href="page/add.jsp">添加用户</a></label>
			</td>
			<td colspan="3" style="border-left:none;">
				<a href="user/list?page=1">首页</a>
				<a href="user/list?page=<%=userBean.getCurrPage() - 1%>">上一页</a>
				<a href="user/list?page=<%=userBean.getCurrPage() + 1%>">下一页</a>
				<a href="user/list?page=<%=userBean.getTotalPage()%>">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>