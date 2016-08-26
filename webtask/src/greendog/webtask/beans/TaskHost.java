package greendog.webtask.beans;

import java.util.Date;

/**
 * 服务器信息
 * @author zhangqing
 *
 */
public class TaskHost {
	
	/**服务器ID*/
	private Integer id;
	/**服务器ip或域名*/
	private String host;
	/**服务器类型,http/https*/
	private String type;
	/**服务器端口*/
	private Integer port;
	/**服务器描述*/
	private String describe;
	/**任务执行时间*/
	private Date runtime;
	/**任务执行周期*/
	private String runcycle;
	/**任务执行类型，马上/定时*/
	private String runtype;
	/**服务器标识*/
	private Integer flag;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Date getRuntime() {
		return runtime;
	}
	public void setRuntime(Date runtime) {
		this.runtime = runtime;
	}
	public String getRuncycle() {
		return runcycle;
	}
	public void setRuncycle(String runcycle) {
		this.runcycle = runcycle;
	}
	public String getRuntype() {
		return runtype;
	}
	public void setRuntype(String runtype) {
		this.runtype = runtype;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}
