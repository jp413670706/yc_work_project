package com.yc.http.day01;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HttpDemo {
	public static void main(String[] args) {
		try {
			
			URL url = new URL("http://www.baidu.com");
			URLConnection con = url.openConnection();
			Scanner in = new Scanner(con.getInputStream());
			
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			
			in.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
