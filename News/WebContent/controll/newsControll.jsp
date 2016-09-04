<%@page import="com.yc.news.entity.NewsBean"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.yc.news.util.DbHelper"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--声明--%>
<%!public List<Map<String, Object>> getAllNews(int pageSize, int pageNum) throws Exception {
		String sql = String.format("select nid, ntitle, ncreatedate, nauthor from(select n.*, rownum rn from " + 
		"(select * from news order by 5) n where rownum <= %d) " + "where rn > %d", pageSize * pageNum, pageSize
				* (pageNum - 1));
		return DbHelper.findMultiObject(sql, null);
	}

	public int getTotalPage(int pageSize) throws Exception {
		String sql = String.format("select ceil(count(1)/%d) tp from news", pageSize);
		return ((BigDecimal) (DbHelper.findSingleObject(sql, null).get("TP"))).intValue();
	}
	
	//...表示可以有，可有1,也可以有多个
	public List<Map<String, Object>> getNewsByType(int...ntids) throws Exception {
		String sql = "select nid, ntid, ntitle from news";
		
		if(ntids != null && ntids.length != 0){
			sql += " where ";
			for(int ntid : ntids){
				sql += "nid in (select nid from (select * from news where ntid = " + ntid + " order by 5)  where rownum <= 5) or "; 
			}
			sql = sql.substring(0, sql.length() - 4);
		}
		System.out.println(sql);
		
		return DbHelper.findMultiObject(sql, null);
	}
	
	public Map<String, Object> getNewsById(String id) throws Exception {
		String sql = "select * from news where nid=" + id;
		return DbHelper.findSingleObject(sql, null);
	}
	%>

	
<%--小脚本--%>
<%
	String type =  request.getParameter("type");
	if(type == null){
		int currPage = 1;
		int pageSize = 30;
		String temp = request.getParameter("page");
		String size = request.getParameter("size");
		if(size != null){
			pageSize = Integer.parseInt(size);
		}
		int totalPage = getTotalPage(pageSize);
		if (temp != null) {
			currPage = Integer.parseInt(temp);
			if (currPage > totalPage) {
				currPage = totalPage;
			} else if (currPage < 1) {
				currPage = 1;
			}
		}
		List<Map<String, Object>> newses = getAllNews(pageSize, currPage);  //分页数据
		
		NewsBean newsBean = new NewsBean(currPage, totalPage, newses); //封装要传递的数据
		
		Gson gson = new Gson(); // json 与 java对象的转换
		out.println(gson.toJson(newsBean));  //把java对象转换成json字符串
		out.flush();
	}
	else if("125" == type.intern()){
		List<Map<String, Object>> newses = getNewsByType(1, 2, 5);  //分页数据
		Gson gson = new Gson(); // json 与 java对象的转换
		out.println(gson.toJson(newses));  //把java对象转换成json字符串
		out.flush();
	}else if("byId" == type.intern()){
		String id = request.getParameter("id");
		Map<String, Object> news = getNewsById(id);  //分页数据
		request.setAttribute("news", news);
		request.getRequestDispatcher("/page/news_modify.jsp").forward(request, response);
	}
%>