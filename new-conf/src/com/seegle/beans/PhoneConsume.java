package com.seegle.beans;
/**
 * 电话会议记录对象
 * @author zhangqing
 * @date 2014-1-20 上午11:08:52
 */
public class PhoneConsume {
	private int id;//
	private int confid;//会议ID
	private int orgid;//企业ID
	private String phoneNumber;//参会好吗
	private String businessId;//业务id
	private String btime;//入会时间
	private String etime;//离开会议时间
	private String area;//区号
	private int loginFlag;//参会方式
	private int consumeCount;//参会时长
	private int consumeAmount;//参会费用
	private String confname;//会议室名称
	private String nickname;//用户昵称
	private int conftype;//会议类型
	private String joinnumber;//会议接入号码
	private int chargingtime;//计费时长（分钟）
	private String formatCount;//格式化时间
	
	public int getChargingtime() {
		return chargingtime;
	}
	public void setChargingtime(int chargingtime) {
		this.chargingtime = chargingtime;
	}
	public String getFormatCount() {
		return formatCount;
	}
	public void setFormatCount(String formatCount) {
		this.formatCount = formatCount;
	}
	public String getConfname() {
		return confname;
	}
	public void setConfname(String confname) {
		this.confname = confname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getConftype() {
		return conftype;
	}
	public void setConftype(int conftype) {
		this.conftype = conftype;
	}
	public String getJoinnumber() {
		return joinnumber;
	}
	public void setJoinnumber(String joinnumber) {
		this.joinnumber = joinnumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getConfid() {
		return confid;
	}
	public void setConfid(int confid) {
		this.confid = confid;
	}
	public int getOrgid() {
		return orgid;
	}
	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBtime() {
		return btime;
	}
	public void setBtime(String btime) {
		this.btime = btime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(int loginFlag) {
		this.loginFlag = loginFlag;
	}
	public int getConsumeCount() {
		return consumeCount;
	}
	public void setConsumeCount(int consumeCount) {
		this.consumeCount = consumeCount;
	}
	public int getConsumeAmount() {
		return consumeAmount;
	}
	public void setConsumeAmount(int consumeAmount) {
		this.consumeAmount = consumeAmount;
	}
}
