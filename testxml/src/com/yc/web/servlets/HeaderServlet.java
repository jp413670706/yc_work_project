package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/header.action")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HeaderServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num=0;
		//if(  request.getHeader("num")!=null ){
		Enumeration enu=request.getHeaders("num");
		System.out.println(  enu );
			 num=Integer.parseInt( request.getHeader("num") );
			 num*=10;
			// response.setHeader("num", num+"");
			 response.setIntHeader("num", num);
		//}
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		PrintWriter out=response.getWriter();
		out.println("hello");
		out.flush();
	}

}
