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
		// ���ӷ���������
		// Http��һ����״̬��Э��: һ������ һ����Ӧ Ȼ��Ͽ�
		LogManager.getLogger().debug("��ʼ����������...");
		ServerSocket server = null;
		try {
			server = new ServerSocket(9090);
			LogManager.getLogger().debug("�����������ɹ�...");
		} catch (IOException e) {
			LogManager.getLogger().error("�ͻ����������ӷ�����ʧ��!!!", e);
		}

		while (true) {
			Socket client = null;
			try {
				client = server.accept();
				LogManager.getLogger().debug("�ͻ���" + client.getRemoteSocketAddress() + "���ӷ������ɹ�...");
				new Thread(new ClientTask(client)).start();
			} catch (IOException e) {
				LogManager.getLogger().error("�ͻ���" + client.getRemoteSocketAddress() + "�Ͽ�����!!!", e);
			}
		}
	}
}
