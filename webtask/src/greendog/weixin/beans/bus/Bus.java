package greendog.weixin.beans.bus;

public class Bus {
	
	private int busNum;//车数
	private int arrived;//是否到站
	private int order;//车所在位置
	private String stopId;//
	public int getBusNum() {
		return busNum;
	}
	public void setBusNum(int busNum) {
		this.busNum = busNum;
	}
	public int getArrived() {
		return arrived;
	}
	public void setArrived(int arrived) {
		this.arrived = arrived;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	
}
