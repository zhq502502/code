package com.packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.packages.controller.IndexController;
import com.packages.handler.JspHandler;
import com.packages.model.*;
import com.packages.util.PropUtil;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.Const;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class AppConfig extends JFinalConfig{


	
	@Override
	public void configConstant(Constants me) {
		 me.setMaxPostSize(100*Const.DEFAULT_MAX_POST_SIZE);
		//loadPropertyFile("config.txt");				// 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(Boolean.parseBoolean(PropUtil.getInstance().getValue("devMode","false")));
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
				
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropUtil.getInstance().getValue("jdbcUrl"), PropUtil.getInstance().getValue("user"), PropUtil.getInstance().getValue("password").trim());
		c3p0Plugin.start();
		ComboPooledDataSource cpd = (ComboPooledDataSource)c3p0Plugin.getDataSource();
		cpd.setAutomaticTestTable("abc");
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);//debug Sql
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true)); //大小写不敏感		
		me.add(arp);
		
		//arp.addMapping("testuser", Testuser.class);
		//arp.addMapping("goods", Goods.class);
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		//me.add(new SessionInViewInterceptor());		
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new JspHandler());
	}

	@Override
	public void afterJFinalStart() {		
/*		Sysitem.dao.setAuthorize();
		System.out.println("init authorize object size: "+Sysitem.dao.getListAuthorizes().size());
		Sysitem.dao.setMenus();
		Sysmenu.dao.setSysMenus();
		System.out.println("init Menu size: "+Sysitem.dao.getListMenus().size());
		Sysdvp.dao.setListDvps();
		System.out.println("init dvp size: "+Sysdvp.dao.getListDvps().size());*/
	}
	
}
