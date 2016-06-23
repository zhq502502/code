package com.packages.controller;

import java.io.IOException;
import java.io.InputStream;

import com.jfinal.core.Controller;
import com.packages.util.HttpUtil;

public class IndexController extends Controller {
	public void index(){
		
		String echostr = getPara("echostr");
		if(echostr!=null){
			//auth();
			//return;
			renderText("fuck you!");
		}else{
			buss();
		}
		
	}
	public void auth(){
		String echostr = getPara("echostr");
		
		String signature = getPara("signature");
		Long timestamp = getParaToLong("timestamp",Long.valueOf(0));
		String nonce = getPara("nonce");
		renderText(echostr);
	}
	public void buss(){
		InputStream inputStream = null;
		try {
			inputStream =  this.getRequest().getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(HttpUtil.readInputStream(inputStream));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
