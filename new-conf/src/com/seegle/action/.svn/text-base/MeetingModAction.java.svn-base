package com.seegle.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.beans.PhoneVerifycode;
import com.seegle.data.GetConfigFile;
import com.seegle.data.MeetingOperation;
import com.seegle.data.PhoneConfOperation;
import com.seegle.form.ConfRoomActionForm;
import com.seegle.util.PropUtil;

public class MeetingModAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private GetConfigFile gcf = new GetConfigFile();//读取配置
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in MeetingModAction");
    	Object token = session.getAttribute("token");
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	Object orgid = session.getAttribute("orgid");
    	String confid = request.getParameter("confid");
    	String nowPage = request.getParameter("page");
        MeetingOperation mo = new MeetingOperation(orgid.toString());
        ConfRoomActionForm crf = new ConfRoomActionForm();
        crf = mo.querySingleMeeting(token.toString(), orgid.toString(), confid);
		ArrayList groupList = mo.queryGroup(token.toString());
		PhoneConfOperation confOper = new PhoneConfOperation(orgid.toString());
     	List<PhoneVerifycode> list = confOper.getVerifycodeList(token.toString(), orgid.toString(), confid,"1");
     	PhoneVerifycode code = new PhoneVerifycode();
     	if(list.size()>0){
     		code = list.get(0);
     	}
        Date DT = new Date();
        SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String StartDate = SimDF.format(new Date(DT.getTime() - 60 * 1000));  //提前一分钟,解决新建会议室提示"会议尚未开始"
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 50);
        long date = cal.getTimeInMillis();
        String endDate = SimDF.format(date);
     	request.setAttribute("code", code);
		request.setAttribute("crf", crf);
		request.setAttribute("groupList", groupList);	
        request.setAttribute("soft_version", PropUtil.getInstance().getValue("soft.version"));
		request.setAttribute("nowPage", nowPage);       
        request.setAttribute("confid", confid);
        request.setAttribute("btime", StartDate);
        request.setAttribute("etime", endDate);
        
        return mapping.findForward(SUCCESS);
    }
}

