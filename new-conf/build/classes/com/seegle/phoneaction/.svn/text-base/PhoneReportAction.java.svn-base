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

import com.seegle.beans.PhoneConf;
import com.seegle.data.PhoneConfOperation;
/**
 * 会议记录
 * @author zhangqing
 * @date 2013-11-20 下午02:32:40
 */
public class PhoneReportAction extends Action {
	
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
		if(method==null||method.equals("")||method.equals("list")){
			return list(mapping, form, request, response);
		}
		return null;
	}
	/**
	 * 会议列表
	 * @author zhangqing
	 * @date 2013-12-20 下午01:58:36
	 * @return
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	List<PhoneConf> list = new ArrayList<PhoneConf>();
     	request.setAttribute("list", list);
		return mapping.findForward("list");
	}
}
