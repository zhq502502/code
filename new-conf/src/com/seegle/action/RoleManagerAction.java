package com.seegle.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import com.seegle.beans.Menu;
import com.seegle.data.MenuOperation;

public class RoleManagerAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private MenuOperation menuOper = new MenuOperation(null) ;

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String method = request.getParameter("method");
    	request.setCharacterEncoding("utf-8");
    	if(method==null||method.equals("")){    		
    		List<Menu> list = menuOper.getAllMenuOrder(true);
    		List<Menu> listTop = menuOper.getMenuByParentid("0");
    		request.setAttribute("list", list);
    		request.setAttribute("listTop", listTop);
    		return mapping.findForward(SUCCESS);
    	}if(method.equals("saveOrUpdate")){
    		String id = request.getParameter("id");
    		String menuName = request.getParameter("menuName");
    		String menuUrl = request.getParameter("menuUrl");
    		String menuIndex = request.getParameter("menuIndex");
    		String parentId = request.getParameter("parentId");
    		String menuCode = request.getParameter("menuCode");
    		String flag = request.getParameter("flag");
    		String menuDiscription = request.getParameter("menuDiscription");
    		Menu menu = new Menu();
    		menu.setFlag(flag);
    		menu.setId(id);
    		menu.setMenuCode(menuCode);
    		menu.setMenuDiscription(menuDiscription);
    		menu.setMenuIndex(menuIndex);
    		menu.setMenuName(menuName);
    		menu.setMenuUrl(menuUrl);
    		menu.setParentId(parentId);
    		boolean rs = menuOper.saveOrUpdate(menu);
    		response.setContentType("text/html; charset=UTF-8"); 
		    response.setHeader("Cache-Control","no-cache");
		    response.getWriter().write(rs?"0":"-1");
    		
    	}if(method.equals("getOne")){
    		String id = request.getParameter("id");
    		Menu menu = menuOper.getById(id);
    		JSONObject obj = new JSONObject();
    		obj.put("id", menu.getId());
    		obj.put("flag", menu.getFlag());
    		obj.put("menuCode", menu.getMenuCode());
    		obj.put("menuDiscription", menu.getMenuDiscription());
    		obj.put("menuIndex", menu.getMenuIndex());
    		obj.put("menuName", menu.getMenuName());
    		obj.put("menuUrl", menu.getMenuUrl());
    		obj.put("parentId", menu.getParentId());
    		response.setContentType("text/html; charset=UTF-8"); 
    		response.setHeader("Cache-Control","no-cache");
    		response.getWriter().write(obj.toJSONString());
    	}if(method.equals("del")){
	    	String id = request.getParameter("id");
	    	boolean rs = menuOper.delmenu(id);
	    	response.setContentType("text/html; charset=UTF-8"); 
	    	response.setHeader("Cache-Control","no-cache");
	    	response.getWriter().write(rs?"0":"-1");
	    }
    	
    	return null;
    }

}
