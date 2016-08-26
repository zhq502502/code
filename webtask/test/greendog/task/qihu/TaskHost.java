package greendog.task.qihu;
/**
 * task host config
 * @author zhangqing
 *
 */
public class TaskHost {
	private String host;
	private String scheme;
	private int port;
	private String name;
	public TaskHost(String host, String scheme, int port, String name) {
		super();
		this.host = host;
		this.scheme = scheme;
		this.port = port;
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
