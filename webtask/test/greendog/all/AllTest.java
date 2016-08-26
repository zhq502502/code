package greendog.all;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.directwebremoting.impl.ShutdownAlarm;
import org.junit.Test;

public class AllTest {
	
	@Test
	public void 数字位移(){
		long yi = new Long(1);
		for(long i =0;i<100;i++){
			System.out.println(i+"#"+(yi<<i));
		}
	}
	
	@Test
	public void 数字与或非运算(){
		int a = 1;
		int b = 4;
		
		System.out.println(a|b);
	}
	@Test
	public void 秒数获得日期(){
		System.out.println(Calendar.getInstance().getTimeInMillis());
	}
	int sum;
	int n=10;
	int a[] = new int[n];
	int xs;
	@Test
	public void 测试数据内容对调(){
		for(int i = 0;i<a.length;i++){
			a[i] = i;
		}
		for(int i=0;i<n;i++){
			int b[] = new int[n];
			b[0] = n-1-i;
			int sum = n-1-i;
			int xs = 0;
			if(change(b,n)){
				
			}
		}
	}
	/**
	 * 移位运算
	 * @author zhangqing
	 * @date 2014-1-10 下午05:41:28
	 */
	@Test
	public void 移位运算(){
		System.out.println(1<<32);
	}
	public boolean change(int []b,int n){
		for(int i=1;i<n;i++){
			b[b[0]] = i;
			sum+=i;
			xs+=i*a[b[0]];
			if(sum>n||xs>n){
				return false;
			}
		}
		return false;
	}
	
	@Test
	public void 测试睡眠时间(){
		System.out.println(Calendar.getInstance().getTimeInMillis());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Calendar.getInstance().getTimeInMillis());
		
	}
	@Test
	public void 有符号转无符号int(){
		int value = -28385;
		System.out.println(value&0x0FFFF );
	}
	@Test
	public void int越界测试(){
		Long l = new Long("9696969696");
		long r = Long.parseLong("9696969696");
		System.out.println((short)2147483647);
		System.out.println((int)(r));
		System.out.println(1<<30);
	}
	@Test
	public void 测试url连接(){
		try {
			URL url = new URL("http://www.baidu.com");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(5 * 1000);
	        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
	        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
	        String htmlSource = new String(data);
	        System.out.println(htmlSource);;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] readInputStream(InputStream instream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[]  buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        instream.close();
        return outStream.toByteArray();         
    }

}
