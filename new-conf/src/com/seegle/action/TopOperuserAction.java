package com.seegle.action;

import com.seegle.beans.PhoneOperuser;
import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.MeetingOperation;
import com.seegle.data.PhoneOperuserOper;
import com.seegle.form.ConfRoomActionForm;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

public class TopOperuserAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private String token = null;
    private String orgid = null;
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
    	token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
    	orgid = session.getAttribute("orgid")==null?null:session.getAttribute("orgid").toString();
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	String method = request.getParameter("method");
    	if(method==null||method.equals("list")){
    		return this.list(mapping, form, request, response);
    	}if(method.equals("save")){
    		this.save(mapping, form, request, response);
    	}if(method.equals("update")){
    		this.update(mapping, form, request, response);
    	}if(method.equals("del")){
    		this.del(mapping, form, request, response);
    	}if(method.equals("get")){
    		this.get(mapping, form, request, response);
    	}if(method.equals("search")){
    		this.search(mapping, form, request, response);
    	}
        return null;
    }
    
    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
    	List<PhoneOperuser> list1 = oper.getPhoneOperuserList(0, 1, token);//获得数据总数
    	int count = 0;
    	if(list1!=null&&list1.size()>0){
    		count = list1.get(0).getCount();
    	}
    	String page = request.getParameter("page");
    	if(page==null||page.equals("")){
    		page="1";
    	}
    	int onepagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
    	List<PhoneOperuser> list = oper.getPhoneOperuserList((Integer.parseInt(page)-1)*onepagesize,onepagesize, token);
    	request.setAttribute("list", list);
    	request.setAttribute("nowPage", page);  //当前页
     	request.setAttribute("pageNumber", (count+onepagesize-1)/onepagesize);//分页数
    	return mapping.findForward(SUCCESS);
    }
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String account = request.getParameter("account");
    	String alias = request.getParameter("alias");
    	String phone = request.getParameter("phone");
    	String email = request.getParameter("email");
    	String type = request.getParameter("type");
    	if(type==null||type.equals("")){
    		type = "1";
    	}
    	String orgid = request.getParameter("orgid");
    	if(orgid==null||orgid.equals("")){
    		orgid = this.orgid;
    	}
    	PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
    	boolean result = oper.save(token, account, orgid, phone, email, alias, type);
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(result){
			out.print("0");
		}else{
			out.print("1");
		}
    	return null;
    }
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String account = request.getParameter("account");
    	String alias = request.getParameter("alias");
    	String phone = request.getParameter("phone");
    	String email = request.getParameter("email");
    	String type = request.getParameter("type");
    	if(type==null||type.equals("")){
    		type = "1";
    	}
    	String orgid = request.getParameter("orgid");
    	if(orgid==null||orgid.equals("")){
    		orgid = this.orgid;
    	}
    	PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
    	boolean result = oper.update(token, account, orgid, phone, email, alias, type);
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(result){
			out.print("0");
		}else{
			out.print("1");
		}
    	return null;
    }
    public ActionForward get(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	String account = request.getParameter("account");
    	String orgid = request.getParameter("orgid");
    	if(orgid==null||orgid.equals("")){
    		orgid = this.orgid;
    	}
    	PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
    	JSONObject result = (JSONObject)oper.getOne(token, account, orgid,true);
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result.toJSONString());
    	return null;
    }
    public ActionForward search(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	String account = request.getParameter("account");
    	String orgid = request.getParameter("orgid");
    	if(orgid==null||orgid.equals("")){
    		orgid = this.orgid;
    	}
    	PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
    	JSONObject result = (JSONObject)oper.search(token, account, orgid);
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();
    	out.print(result.toJSONString());
    	return null;
    }
    public ActionForward del(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	String accounts = request.getParameter("accounts");
    	String orgid = request.getParameter("orgid");
    	PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
    	boolean result = oper.del(token, accounts, orgid);
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(result){
			out.print("0");
		}else{
			out.print("1");
		}
    	return null;
    }
}
