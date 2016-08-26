package greendog.webtask.business.thread;

import greendog.webtask.beans.TaskUrl;
import greendog.webtask.beans.TaskUser;
import greendog.webtask.business.Business;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;

/**
 * 任务线程
 * @author zhangqing
 *
 */
public class UserListTaskThread extends Thread{
	/**用户list*/
	private List<TaskUser> userList;
	/**Httpclient链接对象*/
	private HttpClient client;
	/**线程是否正常执行*/
	private boolean status = true;
	private Business bus;
	public UserListTaskThread(HttpClient client,List<TaskUser> userList){
		this.userList = userList;
		this.client = client;
		this.bus = new Business();
	}
	@Override
	public void run() {
		for(int i = 0;i<userList.size()&&status;i++){
			NameValuePair params [] =  {
				new NameValuePair(userList.get(i).getLoginNameName(),userList.get(i).getLoginNameValue()),
				new NameValuePair(userList.get(i).getLoginPwdName(),userList.get(i).getLoginPwdValue()),
				new NameValuePair(userList.get(i).getParam1Name(),userList.get(i).getParam2Value()),
				new NameValuePair(userList.get(i).getParam2Name(),userList.get(i).getParam2Value())
			};
			List<TaskUrl> urlList = userList.get(i).getTaskUrl();
			for(int j=0;j<urlList.size();j++){
				if(j==0){
					if(j>0){
						params=null;
					}
					bus.execute(client, params, urlList.get(j).getUrl(), urlList.get(j).getMethodType());
				}
			}
		}
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
