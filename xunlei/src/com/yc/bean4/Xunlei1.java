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
//				System.out.println(   "已经下载了"+  sum);
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
		//创建一个  randomaccessfile类的对象,并设置好它的大小, 
		RandomAccessFile raf=new RandomAccessFile( fileName, "rwd");
		raf.setLength(  totalSize  );
		raf.close();
		//开启线程  
		
		for(  int i=0;i<threadSize;i++  ){
			//                                           第i个线程已经下载的数据
			Thread t=new Thread(     new  DownLoadTask(   map.get(i),   path, fileName, threadSize,   i,   sizePerThread,   listener ) );
			t.start();
		}
	}
	
	/**
	 * 根据路径查到要下载的文件的总大小
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public long getFileSize(   String path ) throws IOException{
		URL url=new URL( path );
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");      //   GET   POST    HEAD(只请求服务器端返回响应头，而不返回响应的内容)
		con.setConnectTimeout(   10000  );
		
		return con.getContentLength();
	}
	
	/**
	 * 计算每个线程要下载的数据量
	 */
	private long getdownLoadSizePerThread(   long totalSize,   int threadSize){
		return totalSize%threadSize==0?   totalSize/threadSize    :  totalSize/threadSize+1;
	}
	
	
	/**
	 * 根据路径名取出要下载的文件名
	 * @param path
	 * @return
	 */
	public String getFileName(  String path){
		int index=path.lastIndexOf("/");
		return path.substring(index+1);
	}
	
	
	private class DownLoadTask implements Runnable{
		/** 要下载的文件的url地址  */
		private String path;    
		/** 保存的文件名 */
		private String fileName;
		/**   总共有多少线程  */
		private int threadSize;
		/**   这个第几个线程   */
		private int threadid;
		/**  每个线程要下载多少数据 */
        private long sizePerThread;
        
        /** 大小改变的临听器  */
        private OnSizeChangeListener listener;
        
        
        /** 当前这个线程已经下载的数据量  */
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
				//1 .计算当前这个线程要下载的起始位置
				long startPosition=threadid*sizePerThread+     downLoadlength;       //已经下载的数据量
				long endPosition=(threadid+1)*sizePerThread-1;
				//2. 创建RandomAccessFile文件对象，以备存储
				 raf=new RandomAccessFile( fileName, "rwd");
				raf.seek(  startPosition  );     //找到要保存的位置
				//3. 使用  HttpURLConnection对象开始下载,   协议:   con.setRequestProperty("Range", "bytes=" + startPosition + "-"              + endPosition);
				URL url=new URL( path );
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");      //   GET   POST    HEAD(只请求服务器端返回响应头，而不返回响应的内容)
				con.setConnectTimeout(   10000  );
				con.setRequestProperty("Range", "bytes=" + startPosition + "-"              + endPosition);
				//4. 下载到的数据，以流的方式存到  RandomAccessFile对象中. 
				 bis=new BufferedInputStream (      con.getInputStream() );
				byte[] bs=new byte[1024*1024];
				int length=-1;
				long size=0;
				while(    (length=bis.read(bs, 0, bs.length))!=-1){
					raf.write(    bs, 0,   length);
					
					if(   listener!=null){
						listener.notify(  length  );
					}
					
					//操作数据库记录当前这个线程已经下载的长度....
					//TODO:  
					size+=length;   //累加当前线程下载的数据量
					//判断每下载   2m， 就记录一次数据库
					if(   size>=2*1024*1024){
						dlb.record(    path,threadid,    size        );
						size=0;
					}
					
				}
				
				System.out.println("第"+threadid+"个线程下载数据完毕,它的任务从"+ startPosition+"到"+endPosition);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//5. 下完了，则关闭流
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






