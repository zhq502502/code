package com.packages.contoller;

import javax.servlet.ServletContext;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;



public class IndexController extends Controller {
	private Logger log = Logger.getLogger("申通接口");
	
	/***
	 * 向国家平台发出授权请求
	 */
	public void index(){
		log.info("===========in=============");
		render("/index.jsp");
    }
}
