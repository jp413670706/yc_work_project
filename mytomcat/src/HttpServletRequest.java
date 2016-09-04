import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {

	private String uri;
	private String method;
	private String protocolVersion;
	private Map<String, String> parameter = new HashMap<String, String>();
	private InputStream iis;

	public HttpServletRequest(InputStream iis) throws IOException {
		this.iis = iis;
		parse();
	}

	// 解析协议的方法 : GET /kaw/index.html?name=a&pwd=b HTTP/1.1
	private void parse() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(iis));
		String line = null;
		int i = 0;
		//注意：这里我们只取了第一行，即请求的命令行，如果要取其它的请求头信息，则if要改为while,并注意协议在这里是两个  \r\n\r\n
		if ((line = br.readLine()) != null) {
			if (i == 0) {
				parseCommandLine(line);
			}
			i++;
			
			//    doGet是没有请求实体的   \r\n\r\n 停止读     post是有请求实体，\r\n\r\n 还得多一行
		}
	}

	private void parseCommandLine(String line) {
		if (line != null && !"".equals(line)) {
			String[] strs = line.split(" ");
			method = strs[0];
			protocolVersion = strs[2];
			if ("GET".equals(method)) {
				doGet(strs[1]);
			}
		}
	}
	private void doGet(String str) {
		// 解析参数
		if (str.contains("?")) {
			String[] strs = str.split("?");
			uri = strs[0];
			String[] ps = strs[1].split("&");
			for (String s : ps) {
				String[] pp = s.split("=");
				parameter.put(pp[0], pp[1]);
			}
		} else {
			uri = str;
		}
	}
	public String getParameter(String key) {
		String value = parameter.get(key);
		if (value != null && !"".equals(value)) {
			return value;
		} else {
			return null;
		}
	}
	public String getUri() {
		return uri;
	}

	public String getMethod() {
		return method;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

}
