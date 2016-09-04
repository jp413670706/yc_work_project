
<%@page import="com.yc.jsp.util.Encrypt"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.yc.jsp.util.DbHelper"%>
<%@page import="com.jspsmart.upload.File"%>
<%@page import="com.jspsmart.upload.Files"%>
<%@page import="com.jspsmart.upload.Request"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%!
	public boolean addUser(String name, String password, String uploadPath){
		String sql = "insert into users values(seq_users.nextval, ?, ?, ?)";
		return DbHelper.doUpdate(sql, Arrays.asList(new Object[]{name, 
				Encrypt.md5AndSha(password), uploadPath})) > 0;
	}
%>

<%
	request.setCharacterEncoding("utf-8");

	//直接取出request中的数据， 取不到， 因为数据以字节 流的方式传过来的
	String  name = request.getParameter("username");
	String  password = request.getParameter("password");
	String  pic = request.getParameter("pic");
	out.println(String.format("HttpServletRequest 取数据 ===> name=%s, password=%s, pic=%s<br>",
			name, password, pic));
	
	//使用第三的文件上传工具SmartUpload处理
	SmartUpload su = new SmartUpload(); //创建文件上传下载处理对象
	su.setCharset("utf-8");  //设置数据处理的编码集
	su.initialize(pageContext);  //初始文件上传下载环境
	su.upload();
	
	//1.请求处理参数 
	Request req = su.getRequest();  //文件上传下载请求处理对象
	name = req.getParameter("username");
	password = req.getParameter("password");
	pic = req.getParameter("pic");
	out.println(String.format("Request取数据 ==> name=%s, password=%s, pic=%s<br>", 
			name, password, pic));
	
	//2.请求处理文件
	Files fs = su.getFiles();  //取到所有的上传文件对象
	
	File f = fs.getFile(0);  //上传的文件对象
	out.println("ContentType ==>" + f.getContentType() + "<br>");
	out.println("FileName ==>" + f.getFileName() + "<br>");
	out.println("FieldName ==>" + f.getFieldName() + "<br>");
	out.println("FileExt ==>" + f.getFileExt() + "<br>");
	out.println("FilePathName ==>" + f.getFilePathName() + "<br>");
	out.println("ContentString ==>" + f.getContentString() + "<br>");
	
	//String uploadPath = application.getRealPath("/upload") + "/" +  f.getFileName(); //绝对路径
	String uploadPath = "upload" + "/" +  f.getFileName();  //虚拟路径
	f.saveAs(uploadPath, SmartUpload.SAVE_VIRTUAL);// 上传到那个目录
	
	//上传到工作空间中
	String testUploadPath =  "D:\\Workspaces\\jsp_005_jdbc_jndi\\WebContent\\upload\\" 
	+  f.getFileName();  //虚拟路径
	f.saveAs(testUploadPath, SmartUpload.SAVE_PHYSICAL);// 上传到那个目录
	
	
	if(addUser(name, password, uploadPath)){
		response.sendRedirect("manage.jsp");
	}else{
		request.setAttribute("errorMsg", "添加用户失败！！！");
		request.getRequestDispatcher("add.jsp").forward(request, response);
	}
	
%>
