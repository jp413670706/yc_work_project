package com.yc.jsp.servlet;

import static com.yc.jsp.util.ServletUtil.getUriName;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.jsp.entity.UserBean;
import com.yc.jsp.service.UserService;
import com.yc.jsp.service.impl.UserServiceImpl;

@WebServlet("/user/*")  // *：表示一级请求名匹配  /user/login, 不能匹配/user/login/a
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 3261276555502805699L;
	
	private UserService us;
	
	public UserServlet() {
		us = new UserServiceImpl();
	}

	@Override  //只处理get请求
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是get....................");
		doPost(req, resp);
	}

	@Override //只处理post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//String uriName = ServletUtil.getUriName(request.getRequestURI());
		
		//使用静态引入  可以不写类名， 直接调用 import static com.yc.jsp.util.ServletUtil.*;
		String uriName = getUriName(request.getRequestURI()); //取到请求的URI
		System.out.println("请求处理的资源是  ===> " + uriName);
		switch(uriName){
		case "login":   //登录处理
			doLogin(request, response);
			break;
		case "add":    //添加处理
			doAdd(request, response);
		case "list":    //用户数据分页处理
			doList(request, response);
			break;
		
		}
		/*
		*/
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currPage = 1;  //当前页
		int pageSize = 10;  //每页显示数据和记录数
		//int totalPage = getTotalPage(pageSize);
		
		String pageStr = request.getParameter("page");
		if(pageStr != null){
			currPage = Integer.parseInt(pageStr);
		}
		
		UserBean userBean = us.getUserBean(currPage, pageSize);
		request.setAttribute("userBean", userBean);
		request.getRequestDispatcher("/page/manage.jsp").forward(request, response);
		
		
	}

	//添加用户处理
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
			String testUploadPath =  "D:\\Work\\JavaWork\\jsp_007_mvc\\WebContent\\upload\\" 
			+  f.getFileName();  //虚拟路径
			f.saveAs(testUploadPath, SmartUpload.SAVE_PHYSICAL);// 上传到那个目录
			
			if(us.addUser(name, password, uploadPath)){
				response.sendRedirect("/jsp_007_mvc/user/list");
			}else{
				request.setAttribute("errorMsg", "添加用户失败！！！");
				request.getRequestDispatcher("/page/add.jsp").forward(request, response);
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}

	//做登录操作
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); //取到session对象
		//验证码处理
		Object rCode = session.getAttribute("rCode"); //取到自动生成的验证码
		String inputCode = request.getParameter("imageCode"); //输入的验证码

		if (!inputCode.equals(rCode)) { //判断验证是否正确
			request.setAttribute("errorMsg", "验证错误！！！"); //把传递数据给request对象存储
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		String name = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			if (us.login(name, password)) { //登录处理   alt + <- 返回跳转来的上一个位置
				//重定向
				session.setAttribute("user", name); //保存登录用户信息
				response.sendRedirect("/jsp_007_mvc/user/list");
			} else {
				request.setAttribute("errorMsg", "账号或密码错误！！！"); //把传递数据给request对象存储
				request.getRequestDispatcher("/page/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
