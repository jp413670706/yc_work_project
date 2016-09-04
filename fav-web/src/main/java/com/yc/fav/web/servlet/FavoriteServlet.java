package com.yc.fav.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.yc.fav.entity.Favorite;
import com.yc.fav.service.FavoriteService;

@WebServlet("/favorite/*")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FavoriteService favoriteService ;

	@Override
	public void init() throws ServletException {
		// 取到spring容器
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		// 取到容器中的bean对象
		favoriteService = (FavoriteService) wac.getBean("favoriteService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (getrequestFlag(request.getRequestURI())) {
		case "add":
			doAdd(request, response);
			break;
		default:
			break;
		}
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		String param = null;
		try {
			param = URLDecoder.decode(request.getParameter("param"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Gson gson = new Gson();
		Favorite fav = gson.fromJson(param, Favorite.class);
		LogManager.getLogger().debug("取到的数据  ==> " + fav);

		String outJsonStr = gson.toJson(favoriteService.addFavorite(fav));
		try {
			PrintWriter out = response.getWriter();
			out.println(outJsonStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getrequestFlag(String requestURI) {
		LogManager.getLogger().debug("请求的URI为" + requestURI);
		return requestURI.substring(requestURI.lastIndexOf("/") + 1);
	}
}
