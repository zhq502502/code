package greendog.webtask.business.thread;

import greendog.webtask.beans.TaskUrl;
import greendog.webtask.beans.TaskUser;
import greendog.webtask.business.Business;
import greendog.webtask.dao.Dao;
import greendog.webtask.util.Constants;

import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;

/**
 * 任务线程
 * @author zhangqing
 *
 */
public class UserTaskThread extends Thread{
	/**线程是否正常执行*/
	private boolean status = true;
	private Business bus;
	private TaskUser user;
	private Dao dao ;
	Logger log ;
	public UserTaskThread(TaskUser user){
		this.bus = new Business();
		this.dao = new Dao();
		this.user = user;
		log = Logger.getRootLogger().getLogger(user.getLoginNameName());
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		log.debug("thread start");
		
		int waitTime = getWaitTime();
		/*try {
			if(waitTime==-1){
				this.stop();
			}
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			this.notify();
			e.printStackTrace();
		}*/
		HttpClient client = new HttpClient();
		HostConfiguration host = new HostConfiguration();
		host.setHost(user.getHost().getHost(), user.getHost().getPort(), user.getHost().getType());
		client.setHostConfiguration(host);
		List<TaskUrl> urlList = user.getTaskUrl();
		for(int i = 0;i<urlList.size();i++){
			if(i==0){
				NameValuePair params [] =  {
					new NameValuePair(user.getLoginNameName(),user.getLoginNameValue()),
					new NameValuePair(user.getLoginPwdName(),user.getLoginPwdValue()),
					new NameValuePair(user.getParam1Name(),user.getParam2Value()),
					new NameValuePair(user.getParam2Name(),user.getParam2Value())
				};
				bus.execute(client, params, urlList.get(i).getUrl(), urlList.get(i).getMethodType());
			}else{
				bus.execute(client, null, urlList.get(i).getUrl(), urlList.get(i).getMethodType());
			}
		}
		dao.changeLastTime(user.getId());
		log.debug("thread end");
	}
	/**
	 * 获得需要等待的时间，如果为-1的话停止运行
	 * @return 
	 */
	@SuppressWarnings("deprecation")
	public int getWaitTime(){
		String runType = user.getHost().getRuntype();
		if(Constants.RUN_TYPE_NOW.equals(runType)){
			Date nowTime = new Date();
			if(user.getLastRunTime()==null){
				return 0;
			}
			if(user.getLastRunTime().getDate()==nowTime.getDate()
					&&user.getLastRunTime().getMonth()==nowTime.getMonth()
					&&user.getLastRunTime().getYear()==nowTime.getYear()){
				return -1;
			}
			return 0;
		}
		String runCycle = user.getHost().getRuncycle();
		if(Constants.CYCLE_DAY.equals(runCycle)||1==1){
			Date nowTime = new Date();
			Date runTime = user.getHost().getRuntime();
			if(runTime.getHours()>nowTime.getHours()
					||(runTime.getHours()==nowTime.getHours()&&runTime.getMinutes()>nowTime.getMinutes())){
				return ((runTime.getHours()-nowTime.getHours())*60+(runTime.getMinutes()-nowTime.getMinutes()))*60*1000;
			}
		}
		return 0;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public static void main(String [] a){
		Date b = new Date();
	}
	
}
