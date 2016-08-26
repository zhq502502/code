package greendog.httpclient;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

//https://login.taobao.com/member/login.jhtml
public class TestClient{

	@Test
	public void test_taobao() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		HostConfiguration host = new HostConfiguration();
		host.setHost("login.taobao.com", 443, "https");
		client.setHostConfiguration(host);
		PostMethod method = new PostMethod("/member/login.jhtml");
		NameValuePair [] param= {
				new NameValuePair("CtrlVersion", "1,0,0,7"),
				new NameValuePair("TPL_checkcode", ""),
				new NameValuePair("TPL_password", "username"),
				new NameValuePair("TPL_redirect_url", ""),
				new NameValuePair("TPL_username", "password"),
				new NameValuePair("callback", "1"),
				new NameValuePair("css_style", ""),
				new NameValuePair("fc", "default"),
				new NameValuePair("from", "tb"),
				new NameValuePair("from_encoding", ""),
				new NameValuePair("full_redirect", ""),
				new NameValuePair("guf", ""),
				new NameValuePair("gvfdcname", "10"),
				new NameValuePair("gvfdcre", ""),
				new NameValuePair("isIgnore", ""),
				new NameValuePair("llnick", ""),
				new NameValuePair("loginType", "3"),
				new NameValuePair("loginsite", "0"),
				new NameValuePair("minipara", ""),
				new NameValuePair("minititle", ""),
				new NameValuePair("naviVer", "firefox|20"),
				new NameValuePair("need_check_code", ""),
				new NameValuePair("need_sign", ""),
				new NameValuePair("need_user_id", ""),
				new NameValuePair("newlogin", "1"),
				new NameValuePair("not_duplite_str", ""),
				new NameValuePair("osVer", "windows|6.1"),
				new NameValuePair("oslanguage", "zh-CN"),
				new NameValuePair("popid", ""),
				new NameValuePair("poy", ""),
				new NameValuePair("pstrong", "1"),
				new NameValuePair("sign", ""),
				new NameValuePair("sr", "1366*768"),
				new NameValuePair("style", "default"),
				new NameValuePair("sub", ""),
				new NameValuePair("support", "1"),
				new NameValuePair("tid","XOR_1_000000000000000000000000000000_642B305D310E0A707A04017A"),
				new NameValuePair("tid","032u5ObXObBitH2MRY+xNzEXDS85HzknNTzKfM=|uKBnf0cvpy8XTye/h++HzxU=|uZFW7MuiORVOJd5SdbKVvbWSVXIIk3gzyLSIr3Wv|voZB+2NrrKRje1Pbo2QMdDz749uz2xxkDIRDW2NrI+Sc9HymfA==|vzfw1/Aq|vCTjxOM5|vaW9esDnbCBsYFz0A/Q/xL8kzyhkf4SvtE+ok2izRJ/EHzRvQ7R/hP9kj2jDSATfFOzAm9BLZzwHSxE78iqh7aGt+tKKsqv81IykzZqy6sKr16vXMHxnnLesV7CLcKv81IykzeotCoHNgY0l0iXuFW71Hvm1rlV+ZZ55QrlilU4VzuW+kmWuVS7VicUiiQJOlV6mmpZNlY1qcnpyekLKokVNqsIlvLA7Z4wlQinxvUZKLdaKxh5SfiJ5HjJpIrmVzvW548kA2FMfU18IIHhAWQ4mflY/aEAYMFklWSXCjpVuRV6lQnmCWQ4mflY/GMIY|sqqCRWKpUimyWX6k|s6sz9E5Ggcnx+T4W0fZ6QmW/ZQ==|sKiQV+3KsCvAi3AMMDdPaK+I8muChZ2FQirthY2lYnry+t0H3Q==|samhZkH701tDS0OETmlxSSGp0cmxOTF5IVnRHRXy6mJqInoCChJ6MqqC6pIakuogB8DYAiX/|ts4Js5SzdHy74/uj+yH7|t+8okrWSVe93f3ewqNDIEtXdGj0a3bWtpb1nvQ==|tMwLsZaxdm6pwdnxabNp|tc0KsJfd1x7kuITf8xgD2IN5YUaBmV42HgYexB4=|quIln7ifWEBYAMf/dw/IUHgAGMIY|q9MUronDyQD6pprB7QYdxp1nf1ifl1DIQGhQilA=|qNAXrYqtamKlPbX99S/1|qdEWrIusa3O0rLSc1Ow27A==|rtYRq4zGzAX/o5/E6AMYw5hiel2agkVdRW01bbdt|r9cQqo3HzQT+op7F6QIZwplje1ybk1RMVGw0fKZ8|rNQTqY6pbmahuaGZ8WmzaQ==|rdUSqI+ob3ewqPDYUAjSCA==|ouotl7CXUNiA2B83TxfQyFA4EGiyaA==|o+vj6ywk48sMZKPLwwR8FNPL04tMVGwU0+uzi0xUPDTza2OknBTTy/M0DGSjm8MEHNvj2xwkDMvz6ywUHNvza6yEDMvjm1yL8yk="),
		};
		method.addParameters(param);
		client.executeMethod(method);
		System.out.println(method.getResponseBodyAsString());
	}
	@Test
	public void aaaa(){
		Random random = new Random();
		int s = random.nextInt(99999) % (99999 - 10000 + 1) + 10000;
		String guestid = "SG" + s;
		System.out.println(guestid);
	}
}
