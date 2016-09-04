package com.yc.fav.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.yc.fav.entity.Tag;
import com.yc.fav.service.TagService;

@WebServlet("/tag/*")
public class TagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TagService tagService ;
	
	@Override
	public void init() throws ServletException {
		//取到spring容器
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		//取到容器中的bean对象
		tagService = (TagService) wac.getBean("tagService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (getrequestFlag(request.getRequestURI())) {
		case "findAll":
				doFindAll(request, response);
			break;
		default:
			break;
		}
	}

	private void doFindAll(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Tag> tags = tagService.findAllTags();
		
		//使用Gson把对象转换为json字符串
		Gson gson = new Gson();
		String outJsonStr = gson.toJson(tags);
		LogManager.getLogger().debug("响应的数据是：" + outJsonStr);
		
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
