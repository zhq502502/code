package greendog.webtask.beans;

/**
 * 任务链接
 * @author zhangqing
 *
 */
public class TaskUrl {
	/**id*/
	private Integer id;
	/**服务器id*/
	private Integer hostId;
	/**链接地址*/
	private String url;
	/**请求类型，get/post*/
	private String methodType;
	/**排序*/
	private Integer sorting;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHostId() {
		return hostId;
	}
	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public Integer getSorting() {
		return sorting;
	}
	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}
	
}
