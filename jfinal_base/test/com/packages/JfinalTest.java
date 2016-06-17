package com.packages;

import org.junit.Before;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.packages.util.PropUtil;

public class JfinalTest {
	@Before
	public void befor(){
		C3p0Plugin cp = new C3p0Plugin(PropUtil.getInstance().getValue("jdbcUrl"), PropUtil.getInstance().getValue("user"), PropUtil.getInstance().getValue("password").trim());
        cp.start();
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        arp.start();
        //arp.addMapping("tbuser", User.class);
	}
}
