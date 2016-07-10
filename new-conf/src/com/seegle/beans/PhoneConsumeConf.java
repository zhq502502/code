package com.seegle.beans;

public class PhoneConsumeConf {
	private int confid;//会议id
	private int sumCost;//总费用
	private int sumTime;//总时长
	private int countLogin;//总参会次数
	private String confname;//会议名称
	private int sumChargingtime;//计费时长（分钟）
	private String sumFormatCount;//格式化时间
	public int getSumChargingtime() {
		return sumChargingtime;
	}
	public void setSumChargingtime(int sumChargingtime) {
		this.sumChargingtime = sumChargingtime;
	}
	public String getSumFormatCount() {
		return sumFormatCount;
	}
	public void setSumFormatCount(String sumFormatCount) {
		this.sumFormatCount = sumFormatCount;
	}
	public String getConfname() {
		return confname;
	}
	public void setConfname(String confname) {
		this.confname = confname;
	}
	public int getConfid() {
		return confid;
	}
	public void setConfid(int confid) {
		this.confid = confid;
	}
	public int getSumCost() {
		return sumCost;
	}
	public void setSumCost(int sumCost) {
		this.sumCost = sumCost;
	}
	public int getSumTime() {
		return sumTime;
	}
	public void setSumTime(int sumTime) {
		this.sumTime = sumTime;
	}
	public int getCountLogin() {
		return countLogin;
	}
	public void setCountLogin(int countLogin) {
		this.countLogin = countLogin;
	}
}
