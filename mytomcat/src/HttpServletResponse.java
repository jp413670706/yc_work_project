import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

//响应
public class HttpServletResponse {
	private OutputStream oos;

	public HttpServletResponse(OutputStream oos) {
		this.oos = oos;
	}

	// pageUrl: c:\kaw\index.html
	public void redirect(String pageUrl) throws IOException {
		// 输出流到客户端
		byte[] bs = new byte[1024];
		FileInputStream fis = null;
		File f = new File(pageUrl);
		if (!f.exists()) {
			out404();
			return;
		}
		try {
			fis = new FileInputStream(f);
			StringBuffer sb = new StringBuffer();
			int length = -1;
			while ((length = fis.read(bs, 0, bs.length)) != -1) {
				sb.append(new String(bs, 0, length,"GBK"));
			}
			// 拼接http的正常响应协议
			String reponseHead = "HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=GBK\r\nContent-Length: "
					+ sb.toString().getBytes().length + "\r\n\r\n";
			out(reponseHead, sb.toString());
		} catch (Exception e) {
			Utils.printException(e);
		}
	}
	private void out(String responseHead, String responseBody)
			throws IOException {
		oos.write(responseHead.getBytes());
		oos.write(responseBody.getBytes());
		oos.flush();
	}
	private void out404() throws IOException {
		String responseBody = "<h1>查无此页面</h1>";
		String responseHead = "HTTP/1.1 404 Not Found\r\nServer: Apache-Coyote/1.1\r\nContent-Type: text/html;charset=utf-8\r\nContent-Length: "
				+ responseBody.getBytes().length + "\r\n\r\n";
		out(responseHead, responseBody);

	}
}
