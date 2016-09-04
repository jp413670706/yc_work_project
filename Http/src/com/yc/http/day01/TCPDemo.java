package com.yc.http.day01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPDemo {
	public static void main(String[] args) {
		try {
			// InetAddress address =
			// InetAddress.getByName("http://www.baidu.com");
			Socket client = new Socket("www.baidu.com", 80);
			Scanner in = new Scanner(client.getInputStream());
			PrintWriter out = new PrintWriter(client.getOutputStream());
			out.println("GET / HTTP/1.0\r\n");
			out.println("HOST: www.baidu.com\r\n\r\n");
			out.flush();
			System.out.println("·¢ËÍÍê³É...");
			
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			
			in.close();
			out.close();
			client.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
