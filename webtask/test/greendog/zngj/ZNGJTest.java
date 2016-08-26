package greendog.zngj;

import java.io.IOException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class ZNGJTest {
	@Test
	public void 查询车次() throws HttpException, IOException, JSONException {
		HttpClient client = new HttpClient();
		HostConfiguration host = new HostConfiguration();
		host.setHost("59.173.9.172", 7000, "http");
		client.setHostConfiguration(host);
		PostMethod method = new PostMethod("/wuhan/line!map.action");
		NameValuePair [] param= {
				new NameValuePair("direction", "0"),
				new NameValuePair("lineNo", "811"),
		};
		method.addParameters(param);
		client.executeMethod(method);
		String result = method.getResponseBodyAsString();
		System.out.println(result);
		JSONObject obj = new JSONObject(result);
		System.out.println(obj.get("jsonr"));
		
	}
}
