package com.seegle.form;

public class CountUserDetailActionForm extends org.apache.struts.action.ActionForm {

	private int confid;
	private String confname;
	private String logintime;
	private String logouttime;
	private String onlinetime;
	public int getConfid() {
		return confid;
	}
	public void setConfid(int confid) {
		this.confid = confid;
	}
	public String getConfname() {
		return confname;
	}
	public void setConfname(String confname) {
		this.confname = confname;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(String logouttime) {
		this.logouttime = logouttime;
	}
	public String getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}
	
	
}
