package com.packages.controller;

import com.jfinal.core.Controller;

public class IndexController extends Controller {
	public void index(){
		renderText("helloword");
	}
}
