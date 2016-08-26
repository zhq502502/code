package com.packages.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.packages.model.T1;

public class TestController extends Controller {
	public void index(){
		List<T1> userlist = T1.dao.find("select * from tb1");
		this.setAttr("list", userlist);
		this.render("/inc/test/test.jsp");
	}
	public void list(){
		List<T1> userlist = T1.dao.find("select * from tb1");
		this.setAttr("list", userlist);
		this.render("/inc/test/test.jsp");
	}
	public void save(){
		Model<T1> t1 = getModel(T1.class,"test");
		t1.save();
		list();
	}
	public void edit(){
		int id = getParaToInt("id",0);
		T1 t1 = T1.dao.findById(id);
		this.setAttr("t1", t1);
		this.render("/inc/test/testedit.jsp");
	}
	public void delete(){
		int id = getParaToInt("id",0);
		boolean flag = T1.dao.deleteById(id);
		//或者
		Db.update("delete from tb1 where id=?",id);
	}
	public void deleteall(){
		String ids = getPara("ids");//ids：1,2,3,4
		Db.update("delete from tb1 where id in("+ids+")");
	}
}
