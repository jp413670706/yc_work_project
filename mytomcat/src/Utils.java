import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static String formatData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return sdf.format(new Date());
	}
	
	public static void printException(   Exception e){
		StackTraceElement[]  stes=e.getStackTrace();
		for(  StackTraceElement ste:stes){
			System.out.println(     formatData()+ "\t"+ ste);
		}
	}
	
	public static String getExceptionInfo(   Exception e){
		StringBuffer sb=new StringBuffer();
		StackTraceElement[]  stes=e.getStackTrace();
		for(  StackTraceElement ste:stes){
			sb.append(   ste.toString() +"\n");
		}
		return sb.toString();
	}
	
	
}
