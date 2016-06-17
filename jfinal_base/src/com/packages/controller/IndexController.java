package com.packages.controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
	
	public void index(){
		render("/index.jsp");
	}
	/**
	 * 支付列表记录有哪些订单正在支付中.paying，ok，false
	 */
	private static final Map<String, String> paylist = new HashMap<>();
	public static final String PAYSTATU_OK = "ok";
	public static final String PAYSTATU_PAYING = "paying";
	public static final String PAYSTATU_FALSE = "false";
	
	//进入usercenter界面入口
	public void weixinlogin(){
		
		this.render("/inc/front/usercenter.jsp");
	}
	
}
