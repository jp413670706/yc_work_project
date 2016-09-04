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

import com.google.gson.Gson;
import com.yc.bean.Student;


@WebServlet("/student.action")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentServlet() {
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
		}else if( op.equals("findAll2")){
			findAll2( request, response);
		}
		
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String sname=new String(request.getParameter("sname").getBytes("ISO-8859-1"), "utf-8");
		String age=request.getParameter("age");
		ServletContext application=request.getServletContext();
		List<Student> list=null;
		if(  application.getAttribute("list")!=null){
			list=(List<Student>) application.getAttribute("list");
		}else{
			list=new ArrayList<Student>();
		}
		Student s=new Student();
		Random r=new Random();
		
		s.setId(  r.nextInt(9999999)  );
		s.setSname(sname);
		s.setAge( Integer.parseInt(age ));
		
		list.add( s);
		application.setAttribute("list", list);
		
		PrintWriter out=response.getWriter();
		out.println("Ìí¼Ó³É¹¦");
		out.flush();
		out.close();
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.println("<students>");
		ServletContext application=request.getServletContext();
		List<Student> list=null;
		if(  application.getAttribute("list")!=null){
			list=(List<Student>) application.getAttribute("list");
			for(Student s:list){
				out.println("<student id='"+ s.getId()+"'>");
				out.println("<sname>"+s.getSname()+"</sname>");
				out.println("<age>"+ s.getAge()+"</age>");
				out.println("</student>");
			}
		}
		out.println("</students>");
		out.flush();
		out.close();
		
	}
	
	
	private void findAll2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		ServletContext application=request.getServletContext();
		List<Student> list=null;
		if(  application.getAttribute("list")!=null){
			list=(List<Student>) application.getAttribute("list");
			Gson g=new Gson();
			String json=g.toJson(list);
			out.println( json);
			
		}
		out.flush();
		out.close();
		
	}

}
