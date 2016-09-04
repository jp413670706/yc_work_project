import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * 获取输入流，得到客户端传过来的协议. 将协议解析一个HttpServletRequest对象 (
 * http://localhost:10000/company/index.html ） 协议: GET /company/index.html
 * HTTP/1.1 换行 换
 * 
 * 
 * 
 * 这里不提供 => 调用 doGet/doPost方法 完成业务操作 => 由j2ee程序提供 调用 HttpServletResponse对象完成响应 (
 * response.sendRedirect(), request.getRequestDispatcher().forward(),
 * out.println(); )
 */
public class HttpSessionTask implements Runnable {
	private Socket s;
	private InputStream iis;
	private OutputStream oos;
	private boolean flag;

	public HttpSessionTask(Socket s) {
		this.s = s;
		// 获取各种流
		try {
			iis = s.getInputStream();
			oos = s.getOutputStream();
			flag = true;
		} catch (Exception e) {
			Utils.printException(e);
			flag = false;
		}
	}

	@Override
	// http协议是一个无状态
	public void run() {
		if (flag == false) {
			return;
		}
		try {
			// 创建request对象,解析http协议的请求部分
			HttpServletRequest request = new HttpServletRequest(iis);
			// 得到 /kaw/index.html 资源地址
			String uri = request.getUri();
			// 通过 WebProperties的找到 path c:\kaw\index.html
			String filepath = WebProperties.getInstance().getProperty("path")
					+ uri;
			// 再创建response对象, 调用 redirect ( path );
			HttpServletResponse response = new HttpServletResponse(oos);
			response.redirect(filepath);
		} catch (IOException e) {
			Utils.printException(e);
			out500(e);
		}finally{
			if(  this.s!=null){
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void out500(IOException e) {
		try {
			String exceptioninfo = Utils.getExceptionInfo(e);
			String responseProtocol500 = "HTTP/1.1 500 Internal Server Error\r\nContent-Type: text/html;charset=utf-8\r\nContent-Length: "
					+ exceptioninfo.getBytes().length
					+ "\r\n\r\n"
					+ exceptioninfo;
			oos.write(responseProtocol500.getBytes());
			oos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}

}
