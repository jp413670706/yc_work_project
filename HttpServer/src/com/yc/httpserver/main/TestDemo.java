package com.yc.httpserver.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TestDemo {
	public static void main(String[] args) throws IOException {  
	    // �����������˿ں�8081  
	    ServerSocket serverSocket = new ServerSocket(8082);  
	  
	    // �ȴ�������������һ�������ķ���������������ʱ��Ż��������ִ��  
	    Socket socket = serverSocket.accept();  
	  
	    // ��ȡ��������  
	    InputStream is = socket.getInputStream();  
	    Scanner reader = new Scanner(is);  
	  
	 /*   // �����������  
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
