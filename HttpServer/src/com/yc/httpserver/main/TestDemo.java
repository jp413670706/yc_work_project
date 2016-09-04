package com.yc.httpserver.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TestDemo {
	public static void main(String[] args) throws IOException {  
	    // 服务器监听端口号8081  
	    ServerSocket serverSocket = new ServerSocket(8082);  
	  
	    // 等待接收请求，这是一个阻塞的方法，当请求到来的时候才会继续向下执行  
	    Socket socket = serverSocket.accept();  
	  
	    // 获取请求内容  
	    InputStream is = socket.getInputStream();  
	    Scanner reader = new Scanner(is);  
	  
	 /*   // 输出请求内容  
	    while (true) {  
	    	String line = reader.nextLine();
	    	if (line.intern() == ""){
	    		System.out.println(">>>");
	    		break;++1444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444404
	    	}
	        System.out.println(line);  
	    }  */
	    
	   InputStreamReader r = new InputStreamReader(is);
	    while(true){
	    	int rr = r.read();
	    	if(rr == -1){
	    		break;
	    	}
	    	System.out.print((char)rr);  
	    }
	}  
}
