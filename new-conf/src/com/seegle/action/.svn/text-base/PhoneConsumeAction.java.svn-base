package com.seegle.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.beans.PhoneConsume;
import com.seegle.beans.PhoneConsumeConf;
import com.seegle.beans.PhoneConsumeCount;
import com.seegle.beans.PhoneConsumeOrgid;
import com.seegle.beans.PhoneConsumePhone;
import com.seegle.data.PhoneConsumeOper;
import com.seegle.util.ExcelUtil;
import com.seegle.util.LanguageUtil;
//import com.seegle.phoneconsume.PhoneConsumeOper;
import com.seegle.util.PropUtil;

public class PhoneConsumeAction extends org.apache.struts.action.Action {
	private int orgtype ;
	private LanguageUtil lu = new LanguageUtil();
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	request.setCharacterEncoding("utf-8");
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	String path = request.getContextPath().toString();
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	//获取企业类型
		orgtype = session.getAttribute("orgtype")==null?0:Integer.parseInt(session.getAttribute("orgtype").toString());
    	String method = request.getParameter("method");
    	//设置年
    	Calendar cal = Calendar.getInstance();
    	/*List<Integer> years = new ArrayList<Integer>();
    	for(int i=0;i<5;i++){
    		years.add(cal.get(Calendar.YEAR)-i);
    	}
    	request.setAttribute("years", years);
    	//设置月
    	List<Integer> months = new ArrayList<Integer>();
    	for(int i=1;i<=12;i++){
    		months.add(i);
    	}
    	request.setAttribute("months", months);*/
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		request.setAttribute("nowtime", format.format(cal.getTime()));
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
		request.setAttribute("nexttime", format.format(cal.getTime()));
    	
    	//设置参会方式
    	List<Map<String,String>> loginFlags = new ArrayList<Map<String,String>>();
    	Map<String, String> map1 = new HashMap<String, String>();
    	Map<String, String> map2 = new HashMap<String, String>();
    	Map<String, String> map3 = new HashMap<String, String>();
    	map1.put("key", "");
    	map1.put("value", "全部");
    	map2.put("key", "0");
    	map2.put("value", "电话加入");
    	map3.put("key", "1");
    	map3.put("value", "网络邀请");
    	/*Map<String, String> map4 = new HashMap<String, String>();
    	map4.put("key", "2");
    	map4.put("value", "电话邀请");
    	Map<String, String> map5 = new HashMap<String, String>();
    	map5.put("key", "3");
    	map5.put("value", "视频会议link");*/
    	
    	String language = null;
    	Cookie mycookies[] = request.getCookies();
    	 if (mycookies!= null) {
            for (int i = 0; i < mycookies.length; i++) {
                if ((path+"/SGlanguage").equalsIgnoreCase(mycookies[i].getName())) {
                	language=mycookies[i].getValue();
                }
             }
    	  }
    	LanguageUtil lu = new LanguageUtil();
    	if(language!=null){
        	map1.put("key", "");
        	map1.put("value", lu.getLanguage(language,"phonelog.all","全部"));
        	map2.put("key", "0");
        	map2.put("value", lu.getLanguage(language,"phonelog.loginbyphone","电话加入"));        	
        	map3.put("key", "1");
        	map3.put("value", lu.getLanguage(language,"phonelog.loginbynet","网络邀请"));
        	/*map4.put("key", "2");
        	map4.put("value", lu.getLanguage(language,"phonelog.loginphone","电话邀请"));
        	map5.put("key", "3");
        	map5.put("value", lu.getLanguage(language,"phonelog.conflink","视频会议link"));*/
    	}
    	loginFlags.add(map1);
    	loginFlags.add(map2);
    	loginFlags.add(map3);
    	/*loginFlags.add(map4);
    	loginFlags.add(map5);*/
    	request.setAttribute("loginFlags", loginFlags);
    	
    	if(method==null||method.equals("")||method.equals("conf")){
    		String url = request.getRequestURL().toString();
        	Enumeration<String> params = request.getParameterNames();
        	String ps = "";
        	while (params.hasMoreElements()) {
    			String p = (String) params.nextElement();
    			ps+="&"+p+"="+request.getParameter(p);
    		}
        	ps = ps.equals("")?"":"?"+ps.substring(1,ps.length());
        	url+=ps;
        	session.setAttribute("url_conf", url);
    		return conf(mapping, form, request, response);
    	}
    	if(method.equals("confDetail")){
    		return confDetail(mapping, form, request, response);
    	}
    	if(method.equals("phone")){
    		String url = request.getRequestURL().toString();
        	Enumeration<String> params = request.getParameterNames();
        	String ps = "";
        	while (params.hasMoreElements()) {
    			String p = (String) params.nextElement();
    			ps+="&"+p+"="+request.getParameter(p);
    		}
        	ps = ps.equals("")?"":"?"+ps.substring(1,ps.length());
        	url+=ps;
        	session.setAttribute("url_phone", url);
    		return phone(mapping, form, request, response);
    	}
    	if(method.equals("phoneDetail")){
    		return phoneDetail(mapping, form, request, response);
    	}
    	if(method.equals("org")){
    		return org(mapping, form, request, response);
    	}
    	if(method.equals("export")){
    		return export(mapping, form, request, response);
    	}
    	return null;
    }
    public ActionForward conf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	Object ORG = session.getAttribute("orgid");
    	PhoneConsumeOper oper = new PhoneConsumeOper(ORG.toString());
//    	Object token = "90000@1000@1389340164";
//    	Object token = "889210@admin@1389863038";
    	String conf = request.getParameter("conf");
    	String btime = request.getParameter("btime");
    	String etime = request.getParameter("etime");
    	if(btime==null||etime==null){
    		btime = request.getAttribute("nowtime").toString();
    		etime = request.getAttribute("nexttime").toString();
    	}
    	String orderName = request.getParameter("orderName");
    	String orderType = request.getParameter("orderType");
    	String loginFlag = request.getParameter("loginFlag");
    	if(loginFlag==null||loginFlag.equals("")){
    		loginFlag="0,1";
    	}
    	String confType= request.getParameter("confType");
    	String area= request.getParameter("area");
    	String org = request.getParameter("org");
    	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
    	PhoneConsumeCount count = oper.getCountByConf(token==null?null:token.toString(),loginFlag,confType,area, conf,orgid, btime, etime);
    	String page1 = request.getParameter("page");
    	int page = page1==null||page1.equals("")?1:Integer.parseInt(page1);
    	int pagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
    	request.setAttribute("nowPage", page);
    	int countData = count.getCountData();
    	int countPage = (countData+(pagesize-1))/(pagesize);
    	request.setAttribute("pageNumber", countPage);
    	request.setAttribute("count", count);
    	
    	List<PhoneConsumeConf> list = oper.getPhoneconsumeByConf((page-1)*pagesize, pagesize, token==null?null:token.toString(), loginFlag,confType,area,conf,orgid,btime, etime, orderName, orderType);
    	request.setAttribute("list", list);
    	request.setAttribute("btime", btime);
    	request.setAttribute("etime", etime);
    	if(btime!=null||etime!=null){
    		request.setAttribute("nowtime", btime);
    		request.setAttribute("nexttime", etime);
    	}
    	request.setAttribute("conf", conf);
    	request.setAttribute("orderName", orderName);
    	request.setAttribute("orderType", orderType);
    	request.setAttribute("loginFlag", loginFlag);
    	request.setAttribute("confType", confType);
    	request.setAttribute("area", area);
    	request.setAttribute("org", org);
    	
    	String forward = "consumeConf";
    	if(orgtype!=0){
    		forward = "consumeConfTop";
    	}
    	return mapping.findForward(forward);
    }
    public ActionForward confDetail(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	Object ORG = session.getAttribute("orgid");
    	PhoneConsumeOper oper = new PhoneConsumeOper(ORG.toString());
//    	Object token = "90000@1000@1389340164";
//    	Object token = "889210@admin@1389863038";
    	String conf = request.getParameter("conf");
    	String btime = request.getParameter("btime");
    	String etime = request.getParameter("etime");
    	if(btime==null||etime==null){
    		btime = request.getAttribute("nowtime").toString();
    		etime = request.getAttribute("nexttime").toString();
    	}
    	String orderName = request.getParameter("orderName");
    	String orderType = request.getParameter("orderType");
    	String loginFlag = request.getParameter("loginFlag");
    	if(loginFlag==null||loginFlag.equals("")){
    		loginFlag="0,1";
    	}
    	String confType= request.getParameter("confType");
    	String area= request.getParameter("area");
    	String org = request.getParameter("org");
    	Calendar cal = Calendar.getInstance();
    	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
    	
    	PhoneConsumeCount count = oper.getCount(token==null?null:token.toString(),loginFlag,confType,area, conf, null,orgid, btime, etime);
    	String page1 = request.getParameter("page");
    	int page = page1==null||page1.equals("")?1:Integer.parseInt(page1);
    	int pagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
    	request.setAttribute("nowPage", page);
    	int countData = count.getCountData();
    	int countPage = (countData+(pagesize-1))/(pagesize);
    	request.setAttribute("pageNumber", countPage);
    	request.setAttribute("count", count);
    	
    	List<PhoneConsume> list = oper.getPhoneconsume((page-1)*pagesize, pagesize, token==null?null:token.toString(),loginFlag,confType,area, conf, null,orgid, btime, etime, orderName, orderType);
    	request.setAttribute("list", list);
    	request.setAttribute("btime", btime);
    	request.setAttribute("etime", etime);
    	if(btime!=null||etime!=null){
    		request.setAttribute("nowtime", btime);
    		request.setAttribute("nexttime", etime);
    	}
    	request.setAttribute("conf", conf);
    	request.setAttribute("orderName", orderName);
    	request.setAttribute("orderType", orderType);
    	request.setAttribute("loginFlag", loginFlag);
    	request.setAttribute("confType", confType);
    	request.setAttribute("area", area);
    	request.setAttribute("org", org);

    	String forward = "consumeConfDetail";
    	if(orgtype!=0){
    		forward = "consumeConfDetailTop";
    	}
    	return mapping.findForward(forward);
    }
    public ActionForward phone(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	Object ORG = session.getAttribute("orgid");
    	PhoneConsumeOper oper = new PhoneConsumeOper(ORG.toString());
//    	Object token = "90000@1000@1389340164";
//    	Object token = "889210@admin@1389863038";
    	String phone = request.getParameter("phone");
    	String btime = request.getParameter("btime");
    	String etime = request.getParameter("etime");
    	if(btime==null||etime==null){
    		btime = request.getAttribute("nowtime").toString();
    		etime = request.getAttribute("nexttime").toString();
    	}
    	String orderName = request.getParameter("orderName");
    	String orderType = request.getParameter("orderType");
    	String loginFlag = request.getParameter("loginFlag");
    	if(loginFlag==null||loginFlag.equals("")){
    		loginFlag="0,1";
    	}
    	String confType= request.getParameter("confType");
    	String area= request.getParameter("area");
    	String org = request.getParameter("org");
    	Calendar cal = Calendar.getInstance();
    	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
    	
    	PhoneConsumeCount count = oper.getCountByPhone(token==null?null:token.toString(),loginFlag,confType,area, phone,orgid, btime, etime);
    	String page1 = request.getParameter("page");
    	int page = page1==null||page1.equals("")?1:Integer.parseInt(page1);
    	int pagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
    	request.setAttribute("nowPage", page);
    	int countData = count.getCountData();
    	int countPage = (countData+(pagesize-1))/(pagesize);
    	request.setAttribute("pageNumber", countPage);
    	request.setAttribute("count", count);
    	
    	List<PhoneConsumePhone> list = oper.getPhoneconsumeByPhone((page-1)*pagesize, pagesize, token==null?null:token.toString(),loginFlag,confType,area, phone,orgid, btime, etime, orderName, orderType);
    	request.setAttribute("list", list);
    	request.setAttribute("btime", btime);
    	request.setAttribute("etime", etime);
    	if(btime!=null||etime!=null){
    		request.setAttribute("nowtime", btime);
    		request.setAttribute("nexttime", etime);
    	}
    	request.setAttribute("phone", phone);
    	request.setAttribute("orderName", orderName);
    	request.setAttribute("orderType", orderType);
    	request.setAttribute("loginFlag", loginFlag);
    	request.setAttribute("confType", confType);
    	request.setAttribute("area", area);
    	request.setAttribute("org", org);

    	String forward = "consumePhone";
    	if(orgtype!=0){
    		forward = "consumePhoneTop";
    	}
    	return mapping.findForward(forward);
    }
    public ActionForward phoneDetail(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	System.out.println(request.getRequestURL()+"?"+request.getQueryString());
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	Object ORG = session.getAttribute("orgid");
    	PhoneConsumeOper oper = new PhoneConsumeOper(ORG.toString());
//    	Object token = "90000@1000@1389340164";
//    	Object token = "889210@admin@1389863038";
    	String phone = request.getParameter("phone");
    	String btime = request.getParameter("btime");
    	String etime = request.getParameter("etime");
    	if(btime==null||etime==null){
    		btime = request.getAttribute("nowtime").toString();
    		etime = request.getAttribute("nexttime").toString();
    	}
    	String orderName = request.getParameter("orderName");
    	String orderType = request.getParameter("orderType");
    	String loginFlag = request.getParameter("loginFlag");
    	if(loginFlag==null||loginFlag.equals("")){
    		loginFlag="0,1";
    	}
    	String confType= request.getParameter("confType");
    	String area= request.getParameter("area");
    	String org = request.getParameter("org");
    	Calendar cal = Calendar.getInstance();
    	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
    	
    	PhoneConsumeCount count = oper.getCount(token==null?null:token.toString(),loginFlag,confType,area, null,  phone,orgid,btime, etime);
    	String page1 = request.getParameter("page");
    	int page = page1==null||page1.equals("")?1:Integer.parseInt(page1);
    	int pagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
    	request.setAttribute("nowPage", page);
    	int countData = count.getCountData();
    	int countPage = (countData+(pagesize-1))/(pagesize);
    	request.setAttribute("pageNumber", countPage);
    	request.setAttribute("count", count);
    	
    	List<PhoneConsume> list = oper.getPhoneconsume((page-1)*pagesize, pagesize, token==null?null:token.toString(),loginFlag,confType,area, null, phone,orgid, btime, etime, orderName, orderType);
    	request.setAttribute("list", list);
    	request.setAttribute("btime", btime);
    	request.setAttribute("etime", etime);
    	if(btime!=null||etime!=null){
    		request.setAttribute("nowtime", btime);
    		request.setAttribute("nexttime", etime);
    	}
    	request.setAttribute("phone", phone);
    	request.setAttribute("orderName", orderName);
    	request.setAttribute("orderType", orderType);
    	request.setAttribute("loginFlag", loginFlag);
    	request.setAttribute("confType", confType);
    	request.setAttribute("area", area);
    	request.setAttribute("org", org);

    	String forward = "consumePhoneDetail";
    	if(orgtype!=0){
    		forward = "consumePhoneDetailTop";
    	}
    	return mapping.findForward(forward);
    }
    
    public ActionForward org(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	Object ORG = session.getAttribute("orgid");
    	PhoneConsumeOper oper = new PhoneConsumeOper(ORG.toString());
//    	Object token = "90000@1000@1389340164";
//    	Object token = "889210@admin@1389863038";
    	String org = request.getParameter("org");
    	if(org==null){
    		org="";
    	}
    	String btime = request.getParameter("btime");
    	String etime = request.getParameter("etime");
    	if(btime==null||etime==null){
    		btime = request.getAttribute("nowtime").toString();
    		etime = request.getAttribute("nexttime").toString();
    	}
    	String orderName = request.getParameter("orderName");
    	String orderType = request.getParameter("orderType");
    	String loginFlag = request.getParameter("loginFlag");
    	if(loginFlag==null||loginFlag.equals("")){
    		loginFlag="0,1";
    	}
    	String confType= request.getParameter("confType");
    	String area= request.getParameter("area");
    	Calendar cal = Calendar.getInstance();
    	
    	PhoneConsumeCount count = oper.getCountByOrg(token==null?null:token.toString(), loginFlag, confType, area, org, btime, etime);
    	String page1 = request.getParameter("page");
    	int page = page1==null||page1.equals("")?1:Integer.parseInt(page1);
    	int pagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
    	request.setAttribute("nowPage", page);
    	int countData = count.getCountData();
    	int countPage = (countData+(pagesize-1))/(pagesize);
    	request.setAttribute("pageNumber", countPage);
    	request.setAttribute("count", count);
    	
    	List<PhoneConsumeOrgid> list = oper.getPhoneconsumeByOrg((page-1)*pagesize, pagesize, token==null?null:token.toString(),loginFlag,confType,area, org, btime, etime, orderName, orderType);
    	request.setAttribute("list", list);
    	request.setAttribute("btime", btime);
    	request.setAttribute("etime", etime);
    	if(btime!=null||etime!=null){
    		request.setAttribute("nowtime", btime);
    		request.setAttribute("nexttime", etime);
    	}
    	request.setAttribute("org", org);
    	request.setAttribute("orderName", orderName);
    	request.setAttribute("orderType", orderType);
    	request.setAttribute("loginFlag", loginFlag);
    	request.setAttribute("confType", confType);
    	request.setAttribute("area", area);

    	String forward = "consumeOrg";
    	if(orgtype!=0){
    		forward = "consumeOrgTop";
    	}
    	return mapping.findForward(forward);
    }
    public ActionForward export(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)throws Exception {
    	String type = request.getParameter("type");
    	String language = request.getParameter("language");
    	
    	if(type==null){
    		return null;
    	}
    	List<List<String>> data = new ArrayList<List<String>>();
    	List<String> title = new ArrayList<String>();
    	
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	Object ORG = session.getAttribute("orgid");
    	PhoneConsumeOper oper = new PhoneConsumeOper(ORG.toString());
    	String dfileName = "";
    	String sheelName = "";
    	if(type.equals("confList")){
    		String conf = request.getParameter("conf");
        	String btime = request.getParameter("btime");
        	String etime = request.getParameter("etime");
        	if(btime==null||etime==null){
        		btime = request.getAttribute("nowtime").toString();
        		etime = request.getAttribute("nexttime").toString();
        	}
        	String orderName = request.getParameter("orderName");
        	String orderType = request.getParameter("orderType");
        	if(orderName==null||orderName.equals("")){
        		orderName = "confid";
        		orderType = "asc";
        	}
        	String loginFlag = request.getParameter("loginFlag");
        	if(loginFlag==null||loginFlag.equals("")){
        		loginFlag="0,1";
        	}
        	String confType= request.getParameter("confType");
        	String area= request.getParameter("area");
        	String org = request.getParameter("org");
        	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
        	PhoneConsumeCount count = oper.getCountByConf(token==null?null:token.toString(),loginFlag,confType,area, conf,orgid, btime, etime);
        	List<PhoneConsumeConf> list = oper.getPhoneconsumeByConf(0, count.getCountData(), token==null?null:token.toString(), loginFlag,confType,area,conf,orgid,btime, etime, orderName, orderType);
        	for(PhoneConsumeConf c:list){
        		List<String> row = new ArrayList<String>();
        		row.add(c.getConfid()+"");
        		row.add(c.getConfname());
        		row.add(timeFormat(c.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        		row.add(c.getSumChargingtime()+"");
        		row.add(((float)c.getSumCost()/100.0)+"");
        		row.add(c.getCountLogin()+"");
        		data.add(row);
        	}
        	/*StringBuffer hz = new StringBuffer();
        	hz.append(lu.getLanguage(language,"phonelog.summaryinformation","汇总信息")+"--");
        	hz.append(lu.getLanguage(language,"phonelog.countrecord","总记录数")+":"+count.getCountData()+",");
        	hz.append(lu.getLanguage(language,"phonelog.countlogintime","总参会时长")+":"+timeFormat(count.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        	hz.append(lu.getLanguage(language,"phonelog.sumchargetime","总计费时长")+":");
        	hz.append(count.getSumChargingtime()+lu.getLanguage(language,"phonelog.minute1","分钟")+",");
        	hz.append(lu.getLanguage(language,"phonelog.sumcost","总费用")+":"+((float)count.getSumCost()/(float)100)+lu.getLanguage(language,"phonelog.yuan","元")+",");
        	hz.append(lu.getLanguage(language,"phonelog.countlogin","总参会次数")+":"+count.getCountLogin());*/
        	List<String> row = new ArrayList<String>();
        	row.add(lu.getLanguage(language,"phonelog.summaryinformation","汇总信息"));
        	row.add("");
        	row.add(timeFormat(count.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        	row.add(count.getSumChargingtime()+lu.getLanguage(language,"phonelog.minute1","分钟"));
        	row.add(((float)count.getSumCost()/(float)100)+lu.getLanguage(language,"phonelog.yuan","元"));
        	row.add(count.getCountLogin()+"");
        	data.add(row);
        	title.add(lu.getLanguage(language,"phonelog.confid","会议ID"));
        	title.add(lu.getLanguage(language,"phonelog.confname","会议名称"));
        	title.add(lu.getLanguage(language,"phonelog.sumtime","总时长"));
        	title.add(lu.getLanguage(language,"phonelog.sumchargetime","总计费时长")+"("+lu.getLanguage(language,"phonelog.minute","分")+")");
        	title.add(lu.getLanguage(language,"phonelog.sumcost","总费用")+"("+lu.getLanguage(language,"phonelog.yuan1","元")+")");
        	title.add(lu.getLanguage(language,"phonelog.countlogin","总参会次数"));
        	dfileName = lu.getLanguage(language,"phonelog.logbyconf","通话记录-会议");
        	sheelName = dfileName;
        	
    	}else if(type.equals("confDetail")){
    		String conf = request.getParameter("conf");
        	String btime = request.getParameter("btime");
        	String etime = request.getParameter("etime");
        	if(btime==null||etime==null){
        		btime = request.getAttribute("nowtime").toString();
        		etime = request.getAttribute("nexttime").toString();
        	}
        	String orderName = request.getParameter("orderName");
        	String orderType = request.getParameter("orderType");
        	if(orderName==null||orderName.equals("")){
        		orderName = "confid";
        		orderType = "asc";
        	}
        	String loginFlag = request.getParameter("loginFlag");
        	if(loginFlag==null||loginFlag.equals("")){
        		loginFlag="0,1";
        	}
        	String confType= request.getParameter("confType");
        	String area= request.getParameter("area");
        	String org = request.getParameter("org");
        	Calendar cal = Calendar.getInstance();
        	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
        	PhoneConsumeCount count = oper.getCount(token==null?null:token.toString(),loginFlag,confType,area, conf, null,orgid, btime, etime);
        	List<PhoneConsume> list = oper.getPhoneconsume(0, count.getCountData(), token==null?null:token.toString(),loginFlag,confType,area, conf, null,orgid, btime, etime, orderName, orderType);
        	for(PhoneConsume c:list){
        		List<String> row = new ArrayList<String>();
        		row.add(c.getConfid()+"");
        		row.add(c.getConfname());
        		switch (c.getLoginFlag()) {
				case 0:row.add(lu.getLanguage(language,"phonelog.loginbyphone","电话加入"));break;
				case 1:row.add(lu.getLanguage(language,"phonelog.loginbynet","网络邀请"));break;
				case 2:row.add(lu.getLanguage(language,"phonelog.loginphone","电话邀请"));break;
				case 3:row.add(lu.getLanguage(language,"phonelog.conflink","视频会议link"));break;
				default:
					row.add(lu.getLanguage(language,"phonelog.loginbynet","网络邀请"));
				}
        		//row.add(c.getLoginFlag()==0?lu.getLanguage(language,"phonelog.loginbyphone","电话加入"):lu.getLanguage(language,"phonelog.loginbynet","网络邀请"));
        		row.add(c.getPhoneNumber());
        		row.add(c.getBtime());
        		row.add(timeFormat(c.getConsumeCount(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        		row.add(c.getChargingtime()+"");
        		row.add(((float)c.getConsumeAmount()/100.0)+"");
        		data.add(row);
        	}
        	List<String> row = new ArrayList<String>();
        	row.add(lu.getLanguage(language,"phonelog.summaryinformation","汇总信息"));
        	row.add("");
        	row.add("");
        	row.add("");
        	row.add("");
        	row.add(timeFormat(count.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        	row.add(count.getSumChargingtime()+lu.getLanguage(language,"phonelog.minute1","分钟"));
        	row.add(((float)count.getSumCost()/(float)100)+lu.getLanguage(language,"phonelog.yuan","元"));
        	data.add(row);
        	title.add(lu.getLanguage(language,"phonelog.confid","会议ID"));
        	title.add(lu.getLanguage(language,"phonelog.confname","会议名称"));
        	title.add(lu.getLanguage(language,"phonelog.logintype","参会方式"));
        	title.add(lu.getLanguage(language,"phonelog.phonenumber","参会电话"));
        	title.add(lu.getLanguage(language,"phonelog.btime","入会时间"));
        	title.add(lu.getLanguage(language,"phonelog.countlogintime","参会时长"));
        	title.add(lu.getLanguage(language,"phonelog.chargetime","计费时长")+"("+lu.getLanguage(language,"phonelog.minute","分")+")");
        	title.add(lu.getLanguage(language,"phonelog.cost","费用")+"("+lu.getLanguage(language,"phonelog.yuan1","元")+")");
        	dfileName = lu.getLanguage(language,"phonelog.detailbyconf","通话详情-会议");
        	sheelName = dfileName;
    	}else if(type.equals("phoneList")){
    		String phone = request.getParameter("phone");
        	String btime = request.getParameter("btime");
        	String etime = request.getParameter("etime");
        	if(btime==null||etime==null){
        		btime = request.getAttribute("nowtime").toString();
        		etime = request.getAttribute("nexttime").toString();
        	}
        	String orderName = request.getParameter("orderName");
        	String orderType = request.getParameter("orderType");
        	if(orderName==null||orderName.equals("")){
        		orderName = "phoneNumber";
        		orderType = "asc";
        	}
        	String loginFlag = request.getParameter("loginFlag");
        	if(loginFlag==null||loginFlag.equals("")){
        		loginFlag="0,1";
        	}
        	String confType= request.getParameter("confType");
        	String area= request.getParameter("area");
        	String org = request.getParameter("org");
        	Calendar cal = Calendar.getInstance();
        	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
        	PhoneConsumeCount count = oper.getCountByPhone(token==null?null:token.toString(),loginFlag,confType,area, phone,orgid, btime, etime);
        	List<PhoneConsumePhone> list = oper.getPhoneconsumeByPhone(0, count.getCountData(), token==null?null:token.toString(),loginFlag,confType,area, phone,orgid, btime, etime, orderName, orderType);
        	for(PhoneConsumePhone c:list){
        		List<String> row = new ArrayList<String>();
        		row.add(c.getPhoneNumber());
        		row.add(c.getNickname());
        		row.add(timeFormat(c.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        		row.add(c.getSumChargingtime()+"");
        		row.add(((float)c.getSumCost()/100.0)+"");
        		row.add(c.getCountLogin()+"");
        		data.add(row);
        	}
        	List<String> row = new ArrayList<String>();
        	row.add(lu.getLanguage(language,"phonelog.summaryinformation","汇总信息"));
        	row.add("");
        	row.add(timeFormat(count.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        	row.add(count.getSumChargingtime()+lu.getLanguage(language,"phonelog.minute1","分钟"));
        	row.add(((float)count.getSumCost()/(float)100)+lu.getLanguage(language,"phonelog.yuan","元"));
        	row.add(count.getCountLogin()+"");
        	data.add(row);
        	title.add(lu.getLanguage(language,"phonelog.phonenumber","参会电话"));
        	title.add(lu.getLanguage(language,"phonelog.nickname","用户昵称"));
        	title.add(lu.getLanguage(language,"phonelog.sumtime","总时长"));
        	title.add(lu.getLanguage(language,"phonelog.sumchargetime","总计费时长")+"("+lu.getLanguage(language,"phonelog.minute","分")+")");
        	title.add(lu.getLanguage(language,"phonelog.sumcost","总费用")+"("+lu.getLanguage(language,"phonelog.yuan1","元")+")");
        	title.add(lu.getLanguage(language,"phonelog.countlogin","总参会次数"));
        	dfileName = lu.getLanguage(language,"phonelog.logbyphone","通话记录-电话");
        	sheelName = dfileName;
    	}else if(type.equals("phoneDetail")){
    		String phone = request.getParameter("phone");
        	String btime = request.getParameter("btime");
        	String etime = request.getParameter("etime");
        	if(btime==null||etime==null){
        		btime = request.getAttribute("nowtime").toString();
        		etime = request.getAttribute("nexttime").toString();
        	}
        	String orderName = request.getParameter("orderName");
        	String orderType = request.getParameter("orderType");
        	if(orderName==null||orderName.equals("")){
        		orderName = "phoneNumber";
        		orderType = "asc";
        	}
        	String loginFlag = request.getParameter("loginFlag");
        	if(loginFlag==null||loginFlag.equals("")){
        		loginFlag="0,1";
        	}
        	String confType= request.getParameter("confType");
        	String area= request.getParameter("area");
        	String org = request.getParameter("org");
        	Calendar cal = Calendar.getInstance();
        	int orgid = org==null||org.equals("")?0:Integer.parseInt(org);
        	
        	PhoneConsumeCount count = oper.getCount(token==null?null:token.toString(),loginFlag,confType,area, null,  phone,orgid,btime, etime);
        	List<PhoneConsume> list = oper.getPhoneconsume(0, count.getCountData(), token==null?null:token.toString(),loginFlag,confType,area, null, phone,orgid, btime, etime, orderName, orderType);
        	for(PhoneConsume c:list){
        		List<String> row = new ArrayList<String>();
        		row.add(c.getConfid()+"");
        		row.add(c.getConfname());
        		
        		switch (c.getLoginFlag()) {
				case 0:row.add(lu.getLanguage(language,"phonelog.loginbyphone","电话加入"));break;
				case 1:row.add(lu.getLanguage(language,"phonelog.loginbynet","网络邀请"));break;
				case 2:row.add(lu.getLanguage(language,"phonelog.loginphone","电话邀请"));break;
				case 3:row.add(lu.getLanguage(language,"phonelog.conflink","视频会议link"));break;
				default:
					row.add(lu.getLanguage(language,"phonelog.loginbynet","网络邀请"));
				}
        		//row.add(c.getLoginFlag()==0?lu.getLanguage(language,"phonelog.loginbyphone","电话加入"):lu.getLanguage(language,"phonelog.loginbynet","网络邀请"));
        		row.add(c.getPhoneNumber());
        		row.add(c.getBtime());
        		row.add(timeFormat(c.getConsumeCount(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        		row.add(c.getChargingtime()+"");
        		row.add(((float)c.getConsumeAmount()/100.0)+"");
        		data.add(row);
        	}
        	List<String> row = new ArrayList<String>();
        	row.add(lu.getLanguage(language,"phonelog.summaryinformation","汇总信息"));
        	row.add("");
        	row.add("");
        	row.add("");
        	row.add("");
        	row.add(timeFormat(count.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        	row.add(count.getSumChargingtime()+lu.getLanguage(language,"phonelog.minute1","分钟"));
        	row.add(((float)count.getSumCost()/(float)100)+lu.getLanguage(language,"phonelog.yuan","元"));
        	data.add(row);
        	title.add(lu.getLanguage(language,"phonelog.confid","会议ID"));
        	title.add(lu.getLanguage(language,"phonelog.confname","会议名称"));
        	title.add(lu.getLanguage(language,"phonelog.logintype","参会方式"));
        	title.add(lu.getLanguage(language,"phonelog.phonenumber","参会电话"));
        	title.add(lu.getLanguage(language,"phonelog.btime","入会时间"));
        	title.add(lu.getLanguage(language,"phonelog.countlogintime","参会时长"));
        	title.add(lu.getLanguage(language,"phonelog.chargetime","计费时长")+"("+lu.getLanguage(language,"phonelog.minute","分")+")");
        	title.add(lu.getLanguage(language,"phonelog.cost","费用")+"("+lu.getLanguage(language,"phonelog.yuan1","元")+")");
        	dfileName = lu.getLanguage(language,"phonelog.detailbyphone","通话详情-电话");
        	sheelName = dfileName;
    	}else if(type.equals("org")){
    		String org = request.getParameter("org");
        	String btime = request.getParameter("btime");
        	String etime = request.getParameter("etime");
        	if(btime==null||etime==null){
        		btime = request.getAttribute("nowtime").toString();
        		etime = request.getAttribute("nexttime").toString();
        	}
        	String orderName = request.getParameter("orderName");
        	String orderType = request.getParameter("orderType");
        	if(orderName==null||orderName.equals("")){
        		orderName = "orgid";
        		orderType = "asc";
        	}
        	String loginFlag = request.getParameter("loginFlag");
        	if(loginFlag==null||loginFlag.equals("")){
        		loginFlag="0,1";
        	}
        	String confType= request.getParameter("confType");
        	String area= request.getParameter("area");
        	Calendar cal = Calendar.getInstance();
        	
        	PhoneConsumeCount count = oper.getCountByOrg(token==null?null:token.toString(), loginFlag, confType, area, org, btime, etime);
        	List<PhoneConsumeOrgid> list = oper.getPhoneconsumeByOrg(0, count.getCountData(), token==null?null:token.toString(),loginFlag,confType,area, org, btime, etime, orderName, orderType);
        	for(PhoneConsumeOrgid c:list){
        		List<String> row = new ArrayList<String>();
        		row.add(c.getOrgid()+"");
        		row.add(c.getCountConf()+"");
        		row.add(c.getCountPhonenumber()+"");
        		row.add(timeFormat(c.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        		row.add(c.getSumChargingtime()+"");
        		row.add(((float)c.getSumCost()/100.0)+"");
        		row.add(c.getCountLogin()+"");
        		data.add(row);
        	}
        	List<String> row = new ArrayList<String>();
        	row.add(lu.getLanguage(language,"phonelog.summaryinformation","汇总信息"));
        	row.add("");
        	row.add("");
        	row.add(timeFormat(count.getSumTime(),lu.getLanguage(language,"log.user.hour","小时"),lu.getLanguage(language,"log.user.minute","分钟"),lu.getLanguage(language,"log.user.second","秒")));
        	row.add(count.getSumChargingtime()+lu.getLanguage(language,"phonelog.minute1","分钟"));
        	row.add(((float)count.getSumCost()/(float)100)+lu.getLanguage(language,"phonelog.yuan","元"));
        	row.add(count.getCountLogin()+"");
        	data.add(row);
        	title.add(lu.getLanguage(language,"phonelog.orgid","企业ID"));
        	title.add(lu.getLanguage(language,"phonelog.countconf","参会会议总数"));
        	title.add(lu.getLanguage(language,"phonelog.countphone","参会号码总数"));
        	title.add(lu.getLanguage(language,"phonelog.sumtime","总时长"));
        	title.add(lu.getLanguage(language,"phonelog.sumchargetime","总计费时长")+"("+lu.getLanguage(language,"phonelog.minute","分")+")");
        	title.add(lu.getLanguage(language,"phonelog.sumcost","总费用")+"("+lu.getLanguage(language,"phonelog.yuan","元")+")");
        	title.add(lu.getLanguage(language,"phonelog.countlogin","总参会次数"));
        	dfileName = lu.getLanguage(language,"phonelog.logbyorg","通话记录-企业");
        	sheelName = dfileName;
    	}
    	if(request.getHeader("user-agent").indexOf("MSIE") != -1) {   
            dfileName = java.net.URLEncoder.encode(dfileName,"utf-8") + ".xls";   
        } else {   
            dfileName = new String(dfileName.getBytes("utf-8"),"iso-8859-1") + ".xls";   
        }  
    	response.setContentType("application/vnd.ms-excel"); 
		response.setHeader("Content-Disposition", "attachment; filename=\""+dfileName+"\"");        	
		OutputStream os = response.getOutputStream();
    	ExcelUtil.exportExel(os, sheelName, title, data);
    	return null;
    	/*String forward = "consumeOrg";
    	if(orgtype!=0){
    		forward = "consumeOrgTop";
    	}
    	return mapping.findForward(forward);*/
    }
    
    /**
     * 时间长度格式化     
     **/
    public String timeFormat(int second,String h,String m,String s){
    	String t = "";
    	int hour = second/3600;
    	if(hour>0){
    		t+=hour + " " + h + " ";
    	}
    	int min = (second/60)%60;
    	if(hour>0 || min>0){
    		t+=min + m + " ";
    	}
    	second = second%60;
    	t+=second + s + " ";
    	return t;
    }
}
