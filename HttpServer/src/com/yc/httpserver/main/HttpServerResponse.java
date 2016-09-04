package com.yc.httpserver.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.logging.log4j.LogManager;

public class HttpServerResponse {
	private HttpServerRequest request;
	private OutputStream out;

	public HttpServerResponse(HttpServerRequest request, OutputStream out) {
		this.request = request;
		this.out = out;
	}

	public void sendRedirect() {
		LogManager.getLogger().debug("������Դ ==> " + request.getRequestUri());
		String uri = request.getRequestUri();
		if (uri == null) {
			return;
		}
		if (uri.charAt(uri.length() - 1) == '/') {
			uri += "index.html"; // ���û��ָ����Դ���ƣ�Ĭ��Ϊindex.html
		}
		File source = new File("webapps" + uri);
		if (source.exists()) {
			do200(source);
		} else {
			do404();
		}
	}

	private void do404() {
		try {
			byte[] str404 = ("<h1>�ܱ�Ǹ����Ҫ���ʵ�ҳ�治���ڣ�</h1>"
					+ "<dl><dt>��ܰ��ʾ��</dt><dd>���������ʵ���ַ�Ƿ���ȷ</dd>"
					+ "<dd>���������ȷ�Ϸ��ʵ���ַ��������ҵĸ���ҳ��鿴������ַ��</dd>"
					+ "<dd>�ص��������·�������</dd><dd>�����κ�������飬�뼰ʱ���������ǡ�</dd></dl>").getBytes();
			out.write("HTTP/1.1 404 Page Not Found\r\n".getBytes());
			out.write("Content-Type: text/html\r\n".getBytes());
			out.write(("Date: " + new Date() + "\r\n").getBytes());
			out.write(("Content-length: " + str404.length+ "\r\n\r\n").getBytes());  //������ָ�ֽ�, �����ַ�
			out.write(str404);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void do200(File source) {
		
		
		try {
			out.write("HTTP/1.1 200 OK\r\n".getBytes());
			out.write("Content-Type: text/html\r\n".getBytes());
			out.write(("Date: " + new Date() + "\r\n").getBytes());
			out.write(("Content-length: " + source.length()+ "\r\n\r\n").getBytes());
			FileInputStream in = new FileInputStream(source);
			byte[] bs = new byte[1024 * 1024];
			int len = 0;
			while ((len = in.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			in.close();
			out.flush();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		/*sendResponseLine(200, "OK");
		sendResponseHeadLines(Arrays.asList(new String[] { "Date: " + new Date(), "Content-Type: text/html", "Content-length: " + source.length() }));
		try {
			Scanner in = new Scanner(source);
			while (in.hasNextLine()) {
				sendResponseContentLines(in.nextLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
}
