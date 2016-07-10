package com.seegle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.data.MeetingOperation;
import com.seegle.data.SQLOperation;
import com.seegle.util.SeegleLog;

public class MeetingOrderAction extends org.apache.struts.action.Action {

    private  SQLOperation sqlOperation = new SQLOperation();
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in MeetingOrderAction");
    	Object orgid = session.getAttribute("orgid");
        MeetingOperation mo = new MeetingOperation(orgid.toString());
        SQLOperation sqlOperation = new SQLOperation();
        String ordername = request.getParameter("ordername");
        String ordertype = request.getParameter("ordertype");
        String tip = request.getParameter("tip");
        response.setContentType("text/html; charset=UTF-8"); 
        response.setHeader("Cache-Control","no-cache");
        if(sqlOperation.saveOrUpdateConfOrder(orgid.toString(), ordername, ordertype, tip)){
			response.getWriter().write("0");
        }else{
        	response.getWriter().write("-1");
        }        
        return null;
    }
}
