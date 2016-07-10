package com.seegle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.data.SQLOperation;

public class ModPassAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private SQLOperation op = new SQLOperation();
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        request.setAttribute("userpass", op.getUserpass(session.getAttribute("orgid").toString(), session.getAttribute("userid").toString()));
        return mapping.findForward(SUCCESS);
    }
}
