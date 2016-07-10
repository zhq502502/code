package com.seegle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.seegle.data.UserOperation;
import com.seegle.form.UserInfoActionForm;
import com.seegle.util.SeegleLog;

public class UserEditAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();		
//        SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in UserEditAction");
		String orgid = session.getAttribute("orgid").toString();
		UserOperation uo = new UserOperation(orgid);
		String userid = request.getParameter("account");
		String nowPage = request.getParameter("page");
		UserInfoActionForm uif = uo.getSingleUser(orgid,userid);		
		String account = uif.getUserName();
		String alias = uif.getAlias();
		String userpass = uif.getPasswordMd5();
		String email = uif.getAccountEmail();
		int proxytype = uif.getProxytype();
		String proxyaddr = uif.getProxyaddr();
		String proxyport = uif.getProxyport();
		String proxyuser = uif.getProxyuser();
		String proxypass = uif.getProxypass();
		
		request.setAttribute("account", account);
		request.setAttribute("alias", alias);
		request.setAttribute("userpass", userpass);
		request.setAttribute("email", email);
		request.setAttribute("proxytype", proxytype);
		request.setAttribute("proxyaddr", proxyaddr);
		request.setAttribute("proxyport", proxyport);
		request.setAttribute("proxyuser", proxyuser);
		request.setAttribute("proxypass", proxypass);
		request.setAttribute("nowPage", nowPage);
        
        return mapping.findForward(SUCCESS);
    }
}
