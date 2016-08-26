package greendog.httpclient;

import greendog.webtask.util.HTTPSSecureProtocolSocketFactory;

import java.net.URL;
import java.util.Calendar;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;

public class HttpclentTest {
	public static void main(String a []){
		try {
			URL uri = new URL("https://login.360.cn/?o=sso&m=getToken&func=QHPass.loginUtils.tokenCallback&userName=zhq502502%40126.com&rand=0.907433327448459&callback=QiUserJsonP@millisTime");
			String host = uri.getHost();
			String path = uri.getPath();
			String param = uri.getQuery();
			String scheme = "https";
			int port = uri.getPort();
			if(port==-1){
				if(scheme.equals("http")){
					port = 80;
				}else if(scheme.equals("https")){
					port = 443;
				}
			}
			Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), port);
			Protocol.registerProtocol("https", https);
			HttpClient client = new HttpClient();
			HostConfiguration hostConfg = new HostConfiguration();
			hostConfg.setHost(host, port, scheme);
			client.setHostConfiguration(hostConfg);
			
			String getPath = path;
			if(!(param==null||param.equals(""))){
				getPath+="?"+param;
			}
			getPath = getPath.replace("@millisTime", Calendar.getInstance().getTimeInMillis()+"");
			GetMethod getMethod = new GetMethod(getPath);
			client.executeMethod(getMethod);
			String response = getMethod.getResponseBodyAsString();
			System.out.println(response);
			Protocol.unregisterProtocol("https");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
