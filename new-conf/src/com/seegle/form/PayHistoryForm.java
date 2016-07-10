package com.seegle.form;

import java.io.Serializable;

/**
 * 充值记录
 * @author zwj
 * @date 2014-7-23 14:13:55
 */

public class PayHistoryForm implements Serializable{
	private String id;//ID
	private String orgid;//企业ID
	private String paymoney;//充值金额
	private String realmoney;//实际充值金额
	private String lessenmoney;//优惠金额
	private String beforpay;//充值前金额
	private String afterpay;//充值后金额
	private String price;//单价（分/分钟）
	private String allowexcess;//是否超额计费（0否，1是）
	private String excessprice;//超额计费单价（分/分钟）
	private String operatetime;//操作时间
	private String operateuser;//操作员
	private String param;//备用字段
	private int count; //查询结果数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public String getRealmoney() {
		return realmoney;
	}
	public void setRealmoney(String realmoney) {
		this.realmoney = realmoney;
	}
	public String getLessenmoney() {
		return lessenmoney;
	}
	public void setLessenmoney(String lessenmoney) {
		this.lessenmoney = lessenmoney;
	}
	public String getBeforpay() {
		return beforpay;
	}
	public void setBeforpay(String beforpay) {
		this.beforpay = beforpay;
	}
	public String getAfterpay() {
		return afterpay;
	}
	public void setAfterpay(String afterpay) {
		this.afterpay = afterpay;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAllowexcess() {
		return allowexcess;
	}
	public void setAllowexcess(String allowexcess) {
		this.allowexcess = allowexcess;
	}
	public String getExcessprice() {
		return excessprice;
	}
	public void setExcessprice(String excessprice) {
		this.excessprice = excessprice;
	}
	public String getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	public String getOperateuser() {
		return operateuser;
	}
	public void setOperateuser(String operateuser) {
		this.operateuser = operateuser;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
				
}
