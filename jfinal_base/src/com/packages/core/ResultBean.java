package com.packages.core;

import java.io.Serializable;

import com.packages.util.PropUtil;

@SuppressWarnings("serial")
public class ResultBean implements Serializable {
	private int code;
	private String msg;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
		this.msg = PropUtil.getInstance().getValue("errorcode."+code);
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
