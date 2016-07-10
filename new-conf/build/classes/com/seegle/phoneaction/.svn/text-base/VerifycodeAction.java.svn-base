package com.seegle.phoneaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.beans.PhoneVerifycode;
import com.seegle.data.PhoneConfOperation;
/**
 * 电话会议验证码
 * @author zhangqing
 * @date 2013-11-20 下午02:32:40
 */
public class VerifycodeAction extends Action {
	
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
//     	request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=utf-8");
		confOper = new PhoneConfOperation(orgid);
		if(method==null||method.equals("")){
			return list(mapping, form, request, response);
		}if(method.equals("add")){
			return add(mapping, form, request, response);
		}if(method.equals("disable")){
			return disable(mapping, form, request, response);
		}if(method.equals("del")){
			return del(mapping, form, request, response);
		}if(method.equals("goadd")){
			return goadd(mapping, form, request, response);
		}
		if(method.equals("list1")){
			return list1(mapping, form, request, response);
		}
		if(method.equals("edit")){
			return edit(mapping, form, request, response);
		}
		return null;
	}
	/**
	 * 验证码列表
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	String confid = request.getParameter("confid");
     	List<PhoneVerifycode> list = confOper.getVerifycodeList(token, orgid, confid,"0");
     	request.setAttribute("confid", confid);
     	request.setAttribute("list", list);
		return mapping.findForward("list");
	}
	/**
	 * 视频会议验证码列表
	 */
	public ActionForward list1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	String confid = request.getParameter("confid");
     	List<PhoneVerifycode> list = confOper.getVerifycodeList(token, orgid, confid,"1");
     	PhoneVerifycode code = new PhoneVerifycode();
     	if(list.size()>0){
     		code = list.get(0);
     		response.getWriter().print("codeid:"+code.getId()+";code:"+code.getCode());
     	}else{
     		response.getWriter().print("");
     	}		
		return null;
	}
	/**
	 * 验证码列表
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward goadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	String confid = request.getParameter("confid");
     	request.setAttribute("confid", confid);
		return mapping.findForward("goadd");
	}
	/**
	 * 新建验证码
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String verifycode = request.getParameter("captchaString");
		String type = request.getParameter("type")==null?"0":request.getParameter("type");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		PhoneVerifycode code = new PhoneVerifycode();
		code.setCode(verifycode);
		code.setBegintime(begintime);
		code.setEndtime(endtime);
		code.setConfid(confid);
		code.setType(type);
		response.getWriter().print(confOper.addPhoneVerifycode(token,orgid, code));
		return null;
	}
	/**
	 * 禁用验证码
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward disable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String codes = request.getParameter("codes");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		response.getWriter().print(confOper.disablePhoneVerifycode(token,orgid, codes, confid));
		return null;
	}
	/**
	 * 删除验证码
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String codes = request.getParameter("codes");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		response.getWriter().print(confOper.delPhoneVerifycode(token,orgid, codes, confid));
		return null;
	}
	/**
	 * 修改验证码
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String codeid = request.getParameter("codeid");
		String status = request.getParameter("status");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String verifycode = request.getParameter("captchaString");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		String type = request.getParameter("type")==null?"0":request.getParameter("type");
		PhoneVerifycode code = new PhoneVerifycode();
		code.setId(codeid);
		code.setCode(verifycode);
		code.setStatus(status);
		code.setBegintime(begintime);
		code.setEndtime(endtime);
		code.setConfid(confid);
		code.setType(type);
		response.getWriter().print(confOper.editPhoneVerifycode(token,orgid, code));
		return null;
	}
	
}
