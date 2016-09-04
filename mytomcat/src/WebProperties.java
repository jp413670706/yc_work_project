import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//要求：单例
public class WebProperties extends Properties {

	private static final long serialVersionUID = -8169595636823259735L;

	private WebProperties() {
		InputStream iis = WebProperties.class.getClassLoader()
				.getResourceAsStream("web.properties");
		try {
			load(iis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static WebProperties webProperties;

	//synchronized :    同步...
	public synchronized static WebProperties getInstance() {
		if (webProperties == null) {
			webProperties = new WebProperties();
		}
		return webProperties;
	}
}
