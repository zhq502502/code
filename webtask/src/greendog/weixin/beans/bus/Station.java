package greendog.weixin.beans.bus;

public class Station {
	private Double jingdu;//精度
	private Double weidu;//纬度
	private int order;//序号
	private String stopName;//站点名字
	private String stopId;//站点ID
	private String stopNo;//站点编号
	public Double getJingdu() {
		return jingdu;
	}
	public void setJingdu(Double jingdu) {
		this.jingdu = jingdu;
	}
	public Double getWeidu() {
		return weidu;
	}
	public void setWeidu(Double weidu) {
		this.weidu = weidu;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getStopName() {
		return stopName;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getStopNo() {
		return stopNo;
	}
	public void setStopNo(String stopNo) {
		this.stopNo = stopNo;
	}
}
