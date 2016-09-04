package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Student;

@WebServlet("/boke.action")
public class BokeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BokeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		String op=request.getParameter("op");
		if( op.equals("findAll")){
			findAll( request, response);
		}else if( op.equals("add")){
			add( request, response);
		}
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String content=new String(request.getParameter("content").getBytes("ISO-8859-1"), "utf-8");
		ServletContext application=request.getServletContext();
		application.setAttribute("content", content);
		PrintWriter out=response.getWriter();
		out.println("Ìí¼Ó³É¹¦");
		out.flush();
		out.close();
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		ServletContext application=request.getServletContext();
		out.println(  application.getAttribute("content") );
		out.flush();
		out.close();
		
	}

}
