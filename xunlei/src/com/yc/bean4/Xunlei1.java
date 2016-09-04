package com.yc.bean4;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class Xunlei1 {
	static long sum=0;
	
	private Map<Integer,   Long> map;

//	public static void main(String[] args) throws IOException {
//		String path="http://dldir1.qq.com/qqfile/qq/QQ6.1/11879/QQ6.1.exe";
//		int threadSize=5;
//		Xunlei1 xl=new Xunlei1();
//		
//		xl.download(path, threadSize,    new OnSizeChangeListener(){
//			@Override
//			public void notify(long size) {
//				sum+=size;
//				System.out.println(   "�Ѿ�������"+  sum);
//			}
//		});
//		
//	}
	
	public void download( Map<Integer, Long> map,String path,   int threadSize,   OnSizeChangeListener   listener   ) throws IOException{
		this.map=map;
		String fileName=getFileName(   path );
		long totalSize=getFileSize(   path );
		long sizePerThread=getdownLoadSizePerThread(   totalSize, threadSize);
		
		System.out.println(    fileName+"\t"+   totalSize+"\t"+   sizePerThread );
		//����һ��  randomaccessfile��Ķ���,�����ú����Ĵ�С, 
		RandomAccessFile raf=new RandomAccessFile( fileName, "rwd");
		raf.setLength(  totalSize  );
		raf.close();
		//�����߳�  
		
		for(  int i=0;i<threadSize;i++  ){
			//                                           ��i���߳��Ѿ����ص�����
			Thread t=new Thread(     new  DownLoadTask(   map.get(i),   path, fileName, threadSize,   i,   sizePerThread,   listener ) );
			t.start();
		}
	}
	
	/**
	 * ����·���鵽Ҫ���ص��ļ����ܴ�С
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public long getFileSize(   String path ) throws IOException{
		URL url=new URL( path );
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");      //   GET   POST    HEAD(ֻ����������˷�����Ӧͷ������������Ӧ������)
		con.setConnectTimeout(   10000  );
		
		return con.getContentLength();
	}
	
	/**
	 * ����ÿ���߳�Ҫ���ص�������
	 */
	private long getdownLoadSizePerThread(   long totalSize,   int threadSize){
		return totalSize%threadSize==0?   totalSize/threadSize    :  totalSize/threadSize+1;
	}
	
	
	/**
	 * ����·����ȡ��Ҫ���ص��ļ���
	 * @param path
	 * @return
	 */
	public String getFileName(  String path){
		int index=path.lastIndexOf("/");
		return path.substring(index+1);
	}
	
	
	private class DownLoadTask implements Runnable{
		/** Ҫ���ص��ļ���url��ַ  */
		private String path;    
		/** ������ļ��� */
		private String fileName;
		/**   �ܹ��ж����߳�  */
		private int threadSize;
		/**   ����ڼ����߳�   */
		private int threadid;
		/**  ÿ���߳�Ҫ���ض������� */
        private long sizePerThread;
        
        /** ��С�ı��������  */
        private OnSizeChangeListener listener;
        
        
        /** ��ǰ����߳��Ѿ����ص�������  */
        private Long downLoadlength;
        
        private DownLoadBiz dlb=new DownLoadBiz();
        
		
		public DownLoadTask(long downLoadLength, String path, String fileName, int threadSize,
				int threadid, long sizePerThread,  OnSizeChangeListener listener) {
			super();
			
			this.downLoadlength=downLoadLength;
			
			this.path = path;
			this.fileName = fileName;
			this.threadSize = threadSize;
			this.threadid = threadid;
			this.sizePerThread = sizePerThread;
			this.listener=listener;
		}


		@Override
		public void run() {
			RandomAccessFile raf=null;
			BufferedInputStream bis=null;
			try {
				//1 .���㵱ǰ����߳�Ҫ���ص���ʼλ��
				long startPosition=threadid*sizePerThread+     downLoadlength;       //�Ѿ����ص�������
				long endPosition=(threadid+1)*sizePerThread-1;
				//2. ����RandomAccessFile�ļ������Ա��洢
				 raf=new RandomAccessFile( fileName, "rwd");
				raf.seek(  startPosition  );     //�ҵ�Ҫ�����λ��
				//3. ʹ��  HttpURLConnection����ʼ����,   Э��:   con.setRequestProperty("Range", "bytes=" + startPosition + "-"              + endPosition);
				URL url=new URL( path );
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");      //   GET   POST    HEAD(ֻ����������˷�����Ӧͷ������������Ӧ������)
				con.setConnectTimeout(   10000  );
				con.setRequestProperty("Range", "bytes=" + startPosition + "-"              + endPosition);
				//4. ���ص������ݣ������ķ�ʽ�浽  RandomAccessFile������. 
				 bis=new BufferedInputStream (      con.getInputStream() );
				byte[] bs=new byte[1024*1024];
				int length=-1;
				long size=0;
				while(    (length=bis.read(bs, 0, bs.length))!=-1){
					raf.write(    bs, 0,   length);
					
					if(   listener!=null){
						listener.notify(  length  );
					}
					
					//�������ݿ��¼��ǰ����߳��Ѿ����صĳ���....
					//TODO:  
					size+=length;   //�ۼӵ�ǰ�߳����ص�������
					//�ж�ÿ����   2m�� �ͼ�¼һ�����ݿ�
					if(   size>=2*1024*1024){
						dlb.record(    path,threadid,    size        );
						size=0;
					}
					
				}
				
				System.out.println("��"+threadid+"���߳������������,���������"+ startPosition+"��"+endPosition);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//5. �����ˣ���ر���
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		}
		
	}
	
	

}






