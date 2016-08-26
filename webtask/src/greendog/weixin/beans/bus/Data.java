package greendog.weixin.beans.bus;

import java.util.List;
import java.util.Map;

public class Data {
	private String errmsg;//错误信息
	private String sversion;//版本
	private String status;//状态
	private List<Station> listStation;//站列表
	private List<Bus> listBus;//在线车列表
	private Map<Integer,List<Bus>> mapBus;//在线车列表
	public List<Bus> getListBus() {
		return listBus;
	}
	public void setListBus(List<Bus> listBus) {
		this.listBus = listBus;
	}
	public Map<Integer, List<Bus>> getMapBus() {
		return mapBus;
	}
	public void setMapBus(Map<Integer, List<Bus>> mapBus) {
		this.mapBus = mapBus;
	}
	private Line line;//线路信息
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getSversion() {
		return sversion;
	}
	public void setSversion(String sversion) {
		this.sversion = sversion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Station> getListStation() {
		return listStation;
	}
	public void setListStation(List<Station> listStation) {
		this.listStation = listStation;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
}
