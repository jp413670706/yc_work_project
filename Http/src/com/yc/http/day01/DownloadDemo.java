package com.yc.http.day01;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadDemo {
	public static void main(String[] args) {
		//����http://dldir1.qq.com/qqfile/qq/QQ8.4/18380/QQ8.4.exe
		try {
			URL url = new URL("http://dldir1.qq.com/qqfile/qq/QQ8.4/18380/QQ8.4.exe");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("HEAD");
			con.connect();
			
			//ȡ��ͷ����Ϣ
			long contentLength;
			System.out.println(contentLength = con.getContentLength());
			System.out.println(con.getContentType());
			System.out.println(con.getResponseCode());
			System.out.println(con.getResponseMessage());
			
			//ȡ��ͷ���ж����� ���ݵ���Ϣ
			if (200 == con.getResponseCode()){
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				RandomAccessFile raf = new RandomAccessFile("d:\\qq.exe", "rwd");
				
				//�������������
				InputStream in = con.getInputStream();
				byte[] bs = new byte[1024 * 1024];
				int len = 0;
				long downloadLength = 0;
				while((len = in.read(bs)) != -1){
					downloadLength += len;
					raf.write(bs, 0, len);
					double result = downloadLength * 1.0 / contentLength * 100 ;
					System.out.println("���� ��" + result);
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
