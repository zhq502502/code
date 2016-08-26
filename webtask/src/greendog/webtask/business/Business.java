package greendog.webtask.business;

import greendog.webtask.util.Constants;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 业务类，执行业务操作
 * @author zhangqing
 * @date 2013-5-21 下午05:28:03
 */
public class Business {
	
	/**
	 * 执行操作
	 * @author zhangqing
	 * @date 2013-5-17 下午07:03:50
	 * @param client HttpClient对象
	 * @param params 参数数组
	 * @param uri 访问链接
	 * @param methodType 方法类型：post或get
	 * @throws HttpException
	 * @throws IOException
	 */
	public void execute(HttpClient client,NameValuePair [] params,String uri,String methodType){
		if(methodType!=null&&Constants.METHOD_TYPE_GET.equals(methodType)){
			GetMethod method ;
			method = new GetMethod(uri);
			try {
				client.executeMethod(method);
				System.out.println(method.getResponseBodyAsString());
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(methodType!=null&&Constants.METHOD_TYPE_POST.equals(methodType)){
			PostMethod method ;
			method = new PostMethod(uri);
			method.addParameters(params);
			try {
				client.executeMethod(method);
				System.out.println(method.getResponseBodyAsString());
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
