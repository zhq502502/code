package freedede.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class Xiuwei {

	
	public static void main(String srg[]) throws HttpException, IOException, InterruptedException{
		String url_login="/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes&inajax=1";
		HttpClient client = new HttpClient();
		HostConfiguration host = new HostConfiguration();
		host.setHost("www.freedede.com", 80, "http");
		client.setHostConfiguration(host);
		//执行登录操作
		PostMethod postMethod = new PostMethod(url_login);
		NameValuePair [] param= {
				new NameValuePair("fastloginfield", "username"),
				new NameValuePair("handlekey", "ls"),
				new NameValuePair("password", "4741118"),
				new NameValuePair("quickforward", "yes"),
				new NameValuePair("username", "zhq503503"),
		};
		postMethod.addParameters(param);
		client.executeMethod(postMethod);
		//获得模版的所有分页
		List<String> fenyeList = new ArrayList<String>();
		for(int i=1;i<=43;i++){
//			for(int i=2;i<=2;i++){
			fenyeList.add("/forum-39-"+i+".html");
		}
		
		//获得所有也的连接
		Set<String> set = new HashSet<String>();
		List<String> urls =  new ArrayList<String>();
		String start="<a href=\"http://www.freedede.com/thread-[0-9]*-[0-9]*-[0-9]*.html"; //第一个 
		GetMethod getMethod = null;
		int sum=1;
		for(String url_moban:fenyeList){
			getMethod = new GetMethod(url_moban);
			client.executeMethod(getMethod);
			String retult = getMethod.getResponseBodyAsString();
			Pattern p=Pattern.compile(start); 
			Matcher m=p.matcher(retult); 
			while (m.find()) {
				String url = m.group().replace("<a href=\"http://www.freedede.com", "");
				if(set.add(url)){
					urls.add(url);
					System.out.println("存入第["+sum+"]个模版地址");
					sum++;
				}
			}
		}

		//每页中回复
		for(int i = 0;i<urls.size();i++){
			
			getMethod = new GetMethod(urls.get(i));
			client.executeMethod(getMethod);
			String retult = getMethod.getResponseBodyAsString();
			
			String posttime ="var ts = '[0-9]*'";
			String formhash = "<input type=\"hidden\" name=\"formhash\" value=\"[0-9a-zA-Z]*\" />";
			String subject = "<input type=\"hidden\" name=\"subject\" value=\"[0-9z-zA-Z ]*\" />";
			String usesig = "<input type=\"hidden\" name=\"usesig\" value=\"[0-9a-zA-Z]*\" />";
			
			Pattern p=Pattern.compile(formhash); 
			Matcher m=p.matcher(retult); 
			if(m.find()){
				formhash = m.group().replace("<input type=\"hidden\" name=\"formhash\" value=\"", "").replace("\" />", "");
			}else{
				formhash = "";
			}
			p=Pattern.compile(subject); 
			m=p.matcher(retult); 
			if(m.find()){
				subject = m.group().replace("<input type=\"hidden\" name=\"subject\" value=\"", "").replace("\" />", "");
			}else{
				subject = "";
			}
			p=Pattern.compile(usesig); 
			m=p.matcher(retult); 
			if(m.find()){
				usesig = m.group().replace("<input type=\"hidden\" name=\"usesig\" value=\"", "").replace("\" />", "");
			}else{
				usesig = "";
			}
			p=Pattern.compile(posttime); 
			m=p.matcher(retult); 
			if(m.find()){
				posttime = m.group().replace("var ts = '", "").replace("'", "");
			}else{
				posttime = "";
			}
			String tid = urls.get(i).replace("/thread-", "").split("-")[0];
			String fid = 39+"";//urls.get(i).replace("/thread-", "").split("-")[1];
			postMethod= new PostMethod("/forum.php?mod=post&action=reply&fid="+fid+"&tid="+tid+"&extra=page%3D1&replysubmit=yes&infloat=yes&handlekey=fastpost&inajax=1");
			
			NameValuePair [] param1= {
					new NameValuePair("connect_publish_t", "0"),
					new NameValuePair("formhash", formhash),
					new NameValuePair("message", "xin ren bao dao"),
					new NameValuePair("posttime", posttime),
					new NameValuePair("subject", subject),
					new NameValuePair("usesig", usesig),
			};
			postMethod.addParameters(param1);
			client.executeMethod(postMethod);
			System.out.println("第"+(i+1)+"次回复。响应如下");
			System.out.println(postMethod.getResponseBodyAsString());
			Thread.sleep(61000);
		}
		
		
		
		
		/*connect_publish_t	1
		formhash	a57b2333
		message	¿´¿´£¬£¬Ñ§Ï°Ñ§Ï°ÁË
		posttime	1375024293
		subject	
		usesig	1	*/	
		
		  
		
	}
}
