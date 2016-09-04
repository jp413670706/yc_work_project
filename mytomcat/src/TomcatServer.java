import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//启动serversocket功能，临听端口，当有客户上来时，则启动一个新线程来操作
public class TomcatServer {
	public static void main(String[] args) {
		int port=Integer.parseInt( (String)WebProperties.getInstance().get("port"));
		ServerSocket ss=null;
		try {
			ss = new ServerSocket(   port );
		} catch (IOException e) {
			Utils.printException(e);
		}
		System.out.println(  Utils.formatData()+ "\t服务器启动，监听"+ ss.getLocalPort()+"端口"  );
		while( true ){
			Socket s=null;
			try {
				s = ss.accept();
			} catch (IOException e) {
				Utils.printException(e);
			}
			System.out.println("客户端"+ s.getRemoteSocketAddress()+"登录上了服务器");
			HttpSessionTask hst=new HttpSessionTask( s);
			new Thread(  hst ).start();
			
			
		}
	}
}
