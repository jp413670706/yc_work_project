<%@page import="java.util.List" isErrorPage="true."%>
<%@page import="java.util.Arrays"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>成功</title>
</head>
<body>
	<%
		//取出session中的值
		Object name = session.getAttribute("name");
	
		//此界面必须登录后， 才能使用
		if (name == null){
			//没有登录， 回到登录界面
			session.setAttribute("errorMsg", "请登录后，再来此界面...");
			response.sendRedirect("index.jsp");
		}
		
		//统计网站的访问次数：
			//Cookie  ==> 本地浏览器
			int cCount = 0;  //做cookie存储统计数据
			Cookie[] cs = request.getCookies();
			for(Cookie c : cs){
				if("cCount" == c.getName().intern()){
					cCount = Integer.parseInt(c.getValue());
					break;
				}
			}
			cCount++;
			response.addCookie(new Cookie("cCount", cCount+""));
			out.println("Cookie网站统计数为：" + cCount + "<br>");
			
			//request ==> 一次请求
			int rCount = 0;
			Object obj = request.getAttribute("rCount");
			if (obj != null){
				rCount = (Integer)obj;
			}
			rCount++;
			request.setAttribute("rCount", rCount);
			out.println("request网站统计数为：" + rCount + "<br>");
			
			//session ==> 一次会话
			int sCount = 0;
			Object objs = session.getAttribute("sCount");
			if (objs != null){
				sCount = (Integer)objs;
			}
			sCount++;
			session.setAttribute("sCount", sCount);
			out.println("session网站统计数为：" + sCount + "<br>");
			
			//pageContext ==> 一个页面
			int pCount = 0;
			Object objp = pageContext.getAttribute("pCount");
			if (objp != null){
				sCount = (Integer)objs;
			}
			pCount++;
			pageContext.setAttribute("pCount", pCount);
			out.println("pageContext网站统计数为：" + pCount + "<br>");		
					
			//application  ==> 整个应用
			int aCount = 0;
			Object obja = application.getAttribute("aCount");
			if (obja != null){
				aCount = (Integer)obja;
			}
			aCount++;
			application.setAttribute("aCount", aCount);
			out.println("application网站统计数为：" + aCount + "<br>");	
	%>

	<h1>
		欢迎<%=name%>， 登录成功....
	</h1>
</body>
</html>