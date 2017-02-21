package com.packages;

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
import com.packages.util.PropUtil;

public class AppConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setMaxPostSize(100 * Const.DEFAULT_MAX_POST_SIZE);
		me.setDevMode(Boolean.parseBoolean(PropUtil.getInstance().getValue("devMode", "false")));
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
        
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropUtil.getInstance().getValue("jdbcUrl"), PropUtil.getInstance().getValue("user"), PropUtil.getInstance().getValue("password").trim());
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);//debug Sql
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true)); //大小写不敏感		
		me.add(arp);
//		arp.addMapping("gjuser", GjUser.class);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
//		me.add("/",IndexController.class);
	}

}
