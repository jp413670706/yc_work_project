<%@page import="java.math.BigDecimal"%>
<%@page import="com.yc.jsp.util.DbHelper"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%!
	public List<Map<String, Object>> getAllUserInfo() throws Exception {
			String sql = "select * from users order by 1";
			return DbHelper.findMultiObject(sql, null);
	
	}

	public List<Map<String, Object>> getPartUserInfo(int currPage, int pageSize) throws Exception {
		String sql = String.format("select * from (select u.*, rownum rn from " +
				"(select * from users order by 1) u where rownum <= %d) where  rn > %d", 
				currPage * pageSize, (currPage - 1)*pageSize);
		return DbHelper.findMultiObject(sql, null);
	
	}
	

	public int getTotalPage(int pageSize) throws Exception {
		String sql = String.format("select ceil(count(1)/%d) TotalPage from users", pageSize);
		return ((BigDecimal)DbHelper.findSingleObject(sql, null).get("TOTALPAGE")).intValue();
	}
	%>
<!DOCTYPE html>
<html>
<head>
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
		int currPage = 1;  //当前页
		int pageSize = 10;  //每页显示数据和记录数
		int totalPage = getTotalPage(pageSize);
		
		String pageStr = request.getParameter("page");
		if(pageStr != null){
			currPage = Integer.parseInt(pageStr);
			
			if(currPage >=  totalPage){
				currPage = totalPage;
			}
			
			if(currPage <= 1){
				currPage = 1;
			}
		}
		
		List<Map<String, Object>> users = getPartUserInfo(currPage, pageSize);
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
				<label>当前是第<%=currPage%>页， 共有<%=totalPage%>页</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label><a href="add.jsp">添加用户</a></label>
			</td>
			<td colspan="3" style="border-left:none;">
				<a href="?page=1">首页</a>
				<a href="?page=<%=currPage - 1 %>">上一页</a>
				<a href="?page=<%=currPage + 1 %>">下一页</a>
				<a href="?page=<%=totalPage%>">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>

</body>
</html>