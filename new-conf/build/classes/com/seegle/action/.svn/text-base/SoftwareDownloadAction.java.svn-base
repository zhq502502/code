package com.seegle.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.data.DownloadOperation;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class SoftwareDownloadAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in SoftwareDownloadAction");
    	String top = request.getParameter("top");
    	List<Map<String,String>> list =null;
    	if(top==null||top.equals("")){
    		list = new DownloadOperation(session.getAttribute("orgid").toString()).getDownLoadList();
    	}else{
    		list = new DownloadOperation(session.getAttribute("orgid").toString()).getTopDownLoadList();
    	}
    	request.setAttribute("downloadList", list);
        return mapping.findForward(SUCCESS);
    }
	
}
