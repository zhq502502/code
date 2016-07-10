package com.seegle.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.seegle.data.HttpClient;
import com.seegle.data.SQLOperation;
import com.seegle.data.UserOperation;
import com.seegle.util.EmailUtil;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

/**
 * 会议预约，包括人员选择，邮件发送功能
 * @author zhangqing
 * @date 2013-5-7 上午10:41:58
 */
public class MeetingReservationAction extends Action{
	/**请求成功标识*/
	private static final String SUCCESS = "success";
	private static final String SUCCESS_TOP = "top";
	private static final String EMAILCONFIG = "emailconfig";
	private static final String EMAILCONFIG_TOP = "emailconfig_top";
	/**sql操作类*/
	private SQLOperation sqlOper;
	/**用户操作接口调用类*/
	private UserOperation userOper;
	/**
	 * 无参构造方法
	 */
	public MeetingReservationAction(){
		sqlOper = new SQLOperation();
		
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
//		SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in MeetingReservationAction");
		Object orgid = session.getAttribute("orgid");
		Object token = session.getAttribute("token");
		if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
		String method = request.getParameter("method");
		userOper = new UserOperation(orgid.toString());
		//转到预约界面
		if(method.equals("goto")){
			String confid = request.getParameter("confid");
			String top = request.getParameter("top");
			//是否为平台用户
			request.setAttribute("confid", confid);
			request.setAttribute("orgid", orgid);
			if(!(top==null||top.equals(""))){
				request.setAttribute("top", top);
				return mapping.findForward(SUCCESS_TOP);
			}
			return mapping.findForward(SUCCESS);
		}
		//发送邮件
		if(method.equals("sendemail")){
			String title = request.getParameter("title");
			//title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
			String content = request.getParameter("content");
			//content = new String(content.getBytes("ISO-8859-1"),"UTF-8");
			String emails = request.getParameter("emails");
			//emails = new String(emails.getBytes("ISO-8859-1"),"UTF-8");
			String emailtype = request.getParameter("emailtype");
			EmailUtil emailUtil  = null;
			boolean flag = false;
			int result = -1;
			try{
				emailUtil = new EmailUtil(orgid.toString());
				flag = emailUtil.send(emails, title, content, emailtype);
			}catch (Exception e) {
				flag = false;
				result = -4;
			}
			response.setContentType("text/html; charset=UTF-8"); 
		    response.setHeader("Cache-Control","no-cache");
		    response.getWriter().write(flag?"0":""+result);
		}
		//获得邮箱列表
		if(method.equals("getemails")){
			String top = request.getParameter("top");
			if(!(top==null||top.equals(""))){
				JSONArray jsonArray = userOper.getUserEmails(orgid.toString(),token.toString());
				response.setContentType("text/html; charset=UTF-8"); 
				response.setHeader("Cache-Control","no-cache");
				response.getWriter().write(jsonArray.toJSONString());
			}else{
				JSONArray jsonArray = sqlOper.getUserEmails(orgid.toString());
				response.setContentType("text/html; charset=UTF-8"); 
				response.setHeader("Cache-Control","no-cache");
				response.getWriter().write(jsonArray.toJSONString());
			}
		}
		//邮件配置界面
		if(method.equals("emailconfig")){
			request.setAttribute("emailConfig", sqlOper.emailConfig(orgid.toString()));
			String top = request.getParameter("top");
			if(!(top==null||top.equals(""))){
				request.setAttribute("top", top);
				return mapping.findForward(EMAILCONFIG_TOP);
			}
			return mapping.findForward(EMAILCONFIG);
		}
		
		//保存邮件配置
		if(method.equals("editemaiconfig")){
			String emailhost = request.getParameter("emailhost");
			String emailloginname = request.getParameter("emailloginname");
			String emailloginpwd = request.getParameter("emailloginpwd");
			String emailport = request.getParameter("emailport");
			String emailalias = request.getParameter("emailalias");
			if(emailalias!=null){
				// = new String(emailalias.getBytes("ISO-8859-1"));
			}
			if(emailhost==null||emailhost.equals("")
					||emailloginname==null||emailloginname.equals("")
					||emailloginpwd==null||emailloginpwd.equals("")
					||emailalias==null||emailalias.equals("")
					||emailport==null||emailport.equals("")
			){
				response.setContentType("text/html; charset=UTF-8"); 
				response.setHeader("Cache-Control","no-cache");
				response.getWriter().write("-1");
				return null;
			}
			
			String top = request.getParameter("top");
			String org_name = null;
			if(!(top==null||top.equals(""))){
				HttpClient hc = new HttpClient(PropUtil.getInstance().getValue("apiUrl"),orgid.toString());
		    	List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("accessKey", token.toString())); 
				params1.add(new BasicNameValuePair("orgid", orgid.toString())); 
				JSONObject json1 = hc.getJObject("orgname", params1);
				org_name = json1.get("orgname").toString();
			}
			if(!EmailUtil.validateLogin(orgid.toString(),emailhost, emailloginname, emailloginpwd, emailport)){
				response.setContentType("text/html; charset=UTF-8"); 
				response.setHeader("Cache-Control","no-cache");
				response.getWriter().write("-2");
			}else{
				boolean flag = sqlOper.savaOrUpdateEmailConfig(orgid.toString(),org_name, emailhost, emailloginname, emailloginpwd, emailport,emailalias);
				response.setContentType("text/html; charset=UTF-8"); 
				response.setHeader("Cache-Control","no-cache");
				response.getWriter().write(flag?"0":"-1");
			}
		}
		return null;
	}
	
}
