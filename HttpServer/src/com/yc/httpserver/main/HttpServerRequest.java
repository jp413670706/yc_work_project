package com.yc.httpserver.main;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpServerRequest {
	private Scanner in; //请求数据流
	private String requestMethod;  //请求方式
	private String requestUri;  //请求URI
	private String protocolVersion; //请求的HTTP版本
	private Map<String, String> requestProperties = new HashMap<String, String>(); //请求头部元素
	private Map<String, String> parameter = new HashMap<String, String>(); //请求参数

	public HttpServerRequest(InputStream in) {
		this.in = new Scanner(in);
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public Map<String, String> getRequestProperties() {
		return requestProperties;
	}

	public String getRequestProperty(String key) {
		return requestProperties.get(key);
	}

	public String getParameter(String name) {
		return parameter.get(name);
	}

	public void parseRequest() {
		// 取第一行数据 : 请求行
		parseRequestLine(); //解析请求行		
		parseHeadProperties(); //解析请求头
	}

	//解析请求头
	private void parseHeadProperties() {
		String line = "";
		while ((line = in.nextLine()).intern() != "") {
			String[] keyvalue = line.split(": ");
			requestProperties.put(keyvalue[0], keyvalue[1]);
		}
		if(requestMethod.intern() == "POST"){
			byte b = 0;
			try {
				b = in.nextByte();
			} catch (Exception e) {}
			System.out.println("==>" + b);
		}else{
			System.out.println(" =============?");
		}
	}

	//解析请求行
	private void parseRequestLine() {
		String[] strs = in.nextLine().split("\\s");
		// 取到请求方法
		requestMethod = strs[0];

		// 取请求URI
		int hasParam = -1;
		requestUri = (hasParam = strs[1].indexOf("?")) == -1 ? strs[1] : strs[1].substring(0, hasParam);

		// 请求http版本
		protocolVersion = strs[2];

		// 取到语请求的get请求参数
		if (hasParam != -1) {
			String[] params = strs[1].substring(hasParam + 1).split("&");
			for (String param : params) {
				String[] keyvalue = param.split("=");
				if (keyvalue.length == 2) {
					parameter.put(keyvalue[0], keyvalue[1]);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "HttpServerRequest [requestMethod=" + requestMethod + "\nrequestUri=" + requestUri +
				"\nprotocolVersion=" + protocolVersion + "\nrequestProperties=" + requestProperties
				+ "\nparameter=" + parameter + "]";
	}

}
