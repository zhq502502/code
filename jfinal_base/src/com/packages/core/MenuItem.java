package com.packages.core;

import java.io.Serializable;

public class MenuItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int pid;
	private String name;
	private String url;
	private Long authcode;
	private int level;
	private String baseurl;
	private int canlink;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getAuthcode() {
		return authcode;
	}
	public void setAuthcode(Long authcode) {
		this.authcode = authcode;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public int getCanlink() {
		return canlink;
	}
	public void setCanlink(int canlink) {
		this.canlink = canlink;
	}
}
