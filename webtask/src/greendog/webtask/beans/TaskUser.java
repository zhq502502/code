package greendog.webtask.beans;

import java.util.Date;
import java.util.List;

/**
 * 任务登录用户
 * @author zhangqing
 *
 */
public class TaskUser {
	/**id*/
	private Integer id;
	/**登录名名称*/
	private String loginNameName;
	/**登录密码名称*/
	private String loginPwdName;
	/**登录名值*/
	private String loginNameValue;
	/**登录密码值*/
	private String loginPwdValue;
	/**服务器Id*/
	private Integer hostId;
	/**服务器对象*/
	private TaskHost host;
	/**备用字段1name*/
	private String param1Name;
	/**备用字段1value*/
	private String param1Value;
	/**备用字段2name*/
	private String param2Name;
	/**备用字段2value*/
	private String param2Value;
	/**链接集合类*/
	private List<TaskUrl> taskUrl;
	/**最后执行时间*/
	private Date lastRunTime;
	public Date getLastRunTime() {
		return lastRunTime;
	}
	public void setLastRunTime(Date lastRunTime) {
		this.lastRunTime = lastRunTime;
	}
	public String getLoginNameName() {
		return loginNameName;
	}
	public void setLoginNameName(String loginNameName) {
		this.loginNameName = loginNameName;
	}
	public String getLoginPwdName() {
		return loginPwdName;
	}
	public void setLoginPwdName(String loginPwdName) {
		this.loginPwdName = loginPwdName;
	}
	public String getLoginNameValue() {
		return loginNameValue;
	}
	public void setLoginNameValue(String loginNameValue) {
		this.loginNameValue = loginNameValue;
	}
	public String getLoginPwdValue() {
		return loginPwdValue;
	}
	public void setLoginPwdValue(String loginPwdValue) {
		this.loginPwdValue = loginPwdValue;
	}
	public Integer getHostId() {
		return hostId;
	}
	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}
	public List<TaskUrl> getTaskUrl() {
		return taskUrl;
	}
	public void setTaskUrl(List<TaskUrl> taskUrl) {
		this.taskUrl = taskUrl;
	}
	public TaskHost getHost() {
		return host;
	}
	public void setHost(TaskHost host) {
		this.host = host;
	}
	public String getParam2Value() {
		return param2Value;
	}
	public void setParam2Value(String param2Value) {
		this.param2Value = param2Value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParam1Name() {
		return param1Name;
	}
	public void setParam1Name(String param1Name) {
		this.param1Name = param1Name;
	}
	public String getParam1Value() {
		return param1Value;
	}
	public void setParam1Value(String param1Value) {
		this.param1Value = param1Value;
	}
	public String getParam2Name() {
		return param2Name;
	}
	public void setParam2Name(String param2Name) {
		this.param2Name = param2Name;
	}
}
