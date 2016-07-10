package com.seegle.phoneaction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;

import com.seegle.beans.PhoneConf;
import com.seegle.beans.PhoneFacility;
import com.seegle.data.PhoneConfOperation;
import com.seegle.util.PropUtil;
/**
 * 公公电话会议
 * @author zhangqing
 * @date 2013-11-20 下午02:32:40
 */
public class PublicPhoneconf extends Action {
	
	PhoneConfOperation confOper = null;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String method=request.getParameter("method");
		HttpSession session = request.getSession();
		if(session.getAttribute("token")==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
		String orgid = session.getAttribute("orgid").toString();
     	confOper = new PhoneConfOperation(orgid);
		if(method==null||method.equals("")){
			return list(mapping, form, request, response);
		}if(method.equals("goadd")){
			return goadd(mapping, form, request, response);
		}if(method.equals("goedit")){
			return goadd(mapping, form, request, response);
		}
		return null;
	}
	/**
	 * 公共电话会议列表
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	String page = request.getParameter("page");
     	if(page==null||page.equals("")){
     		page = "1";
     	}
     	int count = confOper.getPhoneConfCount(token, orgid, "", 0);
     	int onepagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
     	String Keyword = request.getParameter("Keyword");
     	request.setAttribute("nowPage", page);  //当前页
     	request.setAttribute("pageNumber", (count+onepagesize-1)/onepagesize);//分页数
     	List<PhoneConf> list = confOper.getPhoneConfList(token, orgid, Keyword,(Integer.parseInt(page)-1)*onepagesize,onepagesize, 0);
     	request.setAttribute("list", list);     	
     	request.setAttribute("Keyword", Keyword);
		return mapping.findForward("list");
	}
	/**
	 * 跳转到公共电话会议添加页面
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward goadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token").toString();
		List<PhoneFacility> facilityList = new ArrayList<PhoneFacility>();
		facilityList = confOper.getPhoneFacilityList(token);
		request.setAttribute("facilityList", facilityList);
		return mapping.findForward("goadd");
	}
	/**
	 * 跳转到公共电话会议编辑页面
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward goedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token").toString();
		List<PhoneFacility> facilityList = new ArrayList<PhoneFacility>();
		facilityList = confOper.getPhoneFacilityList(token);
		request.setAttribute("facilityList", facilityList);
		return mapping.findForward("goadd");
	}
	
}
