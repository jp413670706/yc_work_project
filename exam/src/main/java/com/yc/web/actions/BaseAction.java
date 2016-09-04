package com.yc.web.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.yc.bean.JsonModel;

public abstract class BaseAction extends ActionSupport implements ServletContextAware, SessionAware{

	private static final long serialVersionUID = -1598040769148528720L;

	
	protected ServletContext application;
	protected Map<String, Object> session;
	protected JsonModel jsonModel;
	
	public void outJsonCors( JsonModel jsonModel   ) throws IOException{
		HttpServletResponse response=		ServletActionContext.getResponse();
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=response.getWriter();
		Gson g=new Gson();
		String str= g.toJson(jsonModel);
		out.println(    str  );
		out.flush();
		out.close();
	}
	
	
	public JsonModel getJsonModel() {
		return jsonModel;
	}

	public void setJsonModel(JsonModel jsonModel) {
		this.jsonModel = jsonModel;
	}
	
	public void setServletContext(ServletContext arg0) {
		this.application = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
