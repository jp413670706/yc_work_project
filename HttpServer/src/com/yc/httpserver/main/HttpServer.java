package com.yc.httpserver.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;

public class HttpServer {
	public static void main(String[] args) {
		new HttpServer().startServer();
	}

	public void startServer() {
		// 连接服务器连接
		// Http是一个无状态的协议: 一个请求， 一个响应 然后断开
		LogManager.getLogger().debug("开始启动服务器...");
		ServerSocket server = null;
		try {
			server = new ServerSocket(9090);
			LogManager.getLogger().debug("启动服务器成功...");
		} catch (IOException e) {
			LogManager.getLogger().error("客户端请求连接服务器失败!!!", e);
		}

		while (true) {
			Socket client = null;
			try {
				client = server.accept();
				LogManager.getLogger().debug("客户端" + client.getRemoteSocketAddress() + "连接服务器成功...");
				new Thread(new ClientTask(client)).start();
			} catch (IOException e) {
				LogManager.getLogger().error("客户端" + client.getRemoteSocketAddress() + "断开连接!!!", e);
			}
		}
	}
}
