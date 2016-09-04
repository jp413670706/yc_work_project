package com.yc.jsp.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.jsp.util.DbHelper;
import com.yc.jsp.util.Encrypt;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/add.yc")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			SmartUpload su = new SmartUpload(); //创建文件上传下载处理对象
			su.setCharset("utf-8");  //设置数据处理的编码集
			//su.initialize(pageContext);  //初始文件上传下载环境  ==> 针对jsp初始化的
			su.initialize(getServletConfig(), request, response); //==> 可以对servelt初始化的
			su.upload();
			
			//1.请求处理参数 
			Request req = su.getRequest();  //文件上传下载请求处理对象
			String name = req.getParameter("username");
			String password = req.getParameter("password");
			
			//2.请求处理文件
			Files fs = su.getFiles();  //取到所有的上传文件对象
			File f = fs.getFile(0);  //上传的文件对象

			//String uploadPath = application.getRealPath("/upload") + "/" +  f.getFileName(); //绝对路径
			String uploadPath = "upload" + "/" +  f.getFileName();  //虚拟路径
			f.saveAs(uploadPath, SmartUpload.SAVE_VIRTUAL);// 上传到那个目录
			
			//上传到工作空间中
			String testUploadPath =  "D:\\Work\\JavaWork\\jsp_006_ajax\\WebContent\\upload" 
			+  f.getFileName();  //虚拟路径
			f.saveAs(testUploadPath, SmartUpload.SAVE_PHYSICAL);// 上传到那个目录
			
			
			if(addUser(name, password, uploadPath)){
				response.sendRedirect("manage.jsp");
			}else{
				request.setAttribute("errorMsg", "添加用户失败！！！");
				request.getRequestDispatcher("add.jsp").forward(request, response);
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean addUser(String name, String password, String uploadPath){
		String sql = "insert into users values(seq_users.nextval, ?, ?, ?)";
		return DbHelper.doUpdate(sql, Arrays.asList(new Object[]{name, 
				Encrypt.md5AndSha(password), uploadPath})) > 0;
	}

}
