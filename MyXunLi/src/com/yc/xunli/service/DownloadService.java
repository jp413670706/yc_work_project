package com.yc.xunli.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DownloadService {
	private long contentLength;
	private static long downloadAllLength;
	/**
	 *
	 * @param urlPath
	 *            ���صĵ�ַ
	 * @param dirPath
	 *            ���ص��ļ�����·��
	 * @param threadCount
	 *            �����������߳���
	 */
	
	
	public void downloadFile(String urlPath, String dirPath, int threadCount) {
		// ����http://dldir1.qq.com/qqfile/qq/QQ8.4/18380/QQ8.4.exe
		try {
			URL url = new URL(urlPath);
			contentLength = getDownloadFileSize(url); // ȡ�������ļ��Ĵ�С
			long threadSize = contentLength % threadCount == 0 ? contentLength / threadCount : (contentLength / threadCount + 1); // ÿ���߳����صĴ�С

			String filePath = getFilePath(urlPath, dirPath); // ���� �ļ�

			if (contentLength != 0) { // ����ļ��Ĵ�СдΪ0,��ʾȡ���ļ�ʧ��
				startDownload(url, threadCount, threadSize, filePath);
			} else {
				System.out.println("ȡ������ �ļ�ʧ��!!!!");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public long getContentLength() {
		return contentLength;
	}

	public long getDownloadAllLength() {
		return downloadAllLength;
	}

	/**
	 * 
	 * @param urlPath ���صĵ�ַ
	 * @param dirPath ���ص��ļ�����·��
	 * @return �����ļ���·��
	 */
	private String getFilePath(String urlPath, String dirPath) {
		String filename = urlPath.substring(urlPath.lastIndexOf("/"));
		File file = new File(dirPath + filename);
		if (file.exists()) {
			filename = filename.replace(".", "_" + System.currentTimeMillis() + ".");
		}
		return dirPath + filename;
	}

	/**
	 * 
	 * @param url ���صĵ�ַ
	 * @param threadCount �����������߳���
	 * @param threadSize �����ļ��Ĵ�С
	 * @param filePath �����ļ���·��
	 */
	private void startDownload(URL url, int threadCount, long threadSize, String filePath) {
		final DownloadTask[] dts = new DownloadTask[threadCount];
		for (int i = 1; i <= threadCount; i++) {
			long beginLoc = (i - 1) * threadSize + (i == 1 ? 0 : 1); // ���صĿ�ʼλ��
			long endLoc = i * threadSize; // ���صĽ���λ��
			dts[i - 1] = new DownloadTask(i, beginLoc, endLoc, filePath, url);
			new Thread(dts[i - 1]).start(); // ���������߳�
		}
		new Thread(){
			public void run() {
				while (downloadAllLength != contentLength) {
					int temp = 0;
					for (DownloadTask downloadTask : dts) {
						temp += downloadTask.downloadContentLength;
					}
					downloadAllLength = temp;
					System.out.println(contentLength + " ==> " + downloadAllLength);
				}
			};
		}.start();

	}

	/**
	 * 
	 * @param url ���صĵ�ַ
	 * @return �����ļ��Ĵ�С
	 */
	private long getDownloadFileSize(URL url) {
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("HEAD"); // ����ʽ
			con.connect();
			return con.getContentLength(); // ȡ�������ļ��Ĵ�С
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * ������������
	 * @author Administrator
	 */
	public class DownloadTask implements Runnable, Serializable{
		private static final long serialVersionUID = 409515127435291479L;
		
		private int threadId; // �̱߳��
		private long beginLocation; // ��ʼ���ֽ�����λ��
		private long endLocation; // �������ֽ�����λ��
		long downloadContentLength; // ��ǰ�߳����صĳ���
		private String filePath; // �����ļ��ı���·��
		private URL url;

		public DownloadTask(int threadId, long beginLocation, long endLocation, String filePath, URL url) {
			this.threadId = threadId;
			this.beginLocation = beginLocation;
			this.endLocation = endLocation;
			this.filePath = filePath;
			this.url = url;
		}

		@Override
		public void run() {
			System.out.println("�����˵�" + threadId + "�����߳�...");
			try {
				RandomAccessFile raf = new RandomAccessFile(filePath, "rwd");
				raf.seek(beginLocation); // ���ļ���ָ���ֽ�λ�ÿ�ʼ��������
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET"); // ���������ļ��Ļ�÷�ʽ
				con.setRequestProperty("Range", "bytes=" + beginLocation + "-" + endLocation); // ���������ļ�λ��
				// �������������
				InputStream in = con.getInputStream();
				byte[] bs = new byte[1024];
				int len = 0;
				while ((len = in.read(bs)) != -1) {
					raf.write(bs, 0, len);
					downloadContentLength += len;
				}
				System.out.println("��" + threadId + "�����߳������, ���� ��" + beginLocation + "~" + endLocation);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
