package com.yc.httpserver.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class ClientTask implements Runnable {
	private Socket client;
	private InputStream in;
	private OutputStream out;
	
	public ClientTask(Socket client) {
		this.client = client;		
		try {
			in = client.getInputStream();
			out = client.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		
		//������
		HttpServerRequest request = new HttpServerRequest(in);
		request.parseRequest();
		
		//��Ӧ����
		HttpServerResponse response = new HttpServerResponse(request,out);
		response.sendRedirect();
		
		//http��״̬Э��, ������ر�
		/*try {
			//in.close();
			//out.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}

}
