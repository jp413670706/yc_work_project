package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PicServlet")
public class PicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		//response.setHeader("max-age", "600000");
		response.setHeader("Cache-Control", "private");
		response.setHeader("Expires", "Fri, 19 Aug 2016 14:09:38 GMT");
		PrintWriter out=response.getWriter();
		out.println("['hello','world']");
		out.flush();
		out.close();
	}

}
