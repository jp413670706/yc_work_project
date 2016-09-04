package com.yc.httpserver.main;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpServerRequest {
	private Scanner in; //����������
	private String requestMethod;  //����ʽ
	private String requestUri;  //����URI
	private String protocolVersion; //�����HTTP�汾
	private Map<String, String> requestProperties = new HashMap<String, String>(); //����ͷ��Ԫ��
	private Map<String, String> parameter = new HashMap<String, String>(); //�������

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
		// ȡ��һ������ : ������
		parseRequestLine(); //����������		
		parseHeadProperties(); //��������ͷ
	}

	//��������ͷ
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

	//����������
	private void parseRequestLine() {
		String[] strs = in.nextLine().split("\\s");
		// ȡ�����󷽷�
		requestMethod = strs[0];

		// ȡ����URI
		int hasParam = -1;
		requestUri = (hasParam = strs[1].indexOf("?")) == -1 ? strs[1] : strs[1].substring(0, hasParam);

		// ����http�汾
		protocolVersion = strs[2];

		// ȡ���������get�������
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
