package com.seegle.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.data.GetConfigFile;
import com.seegle.data.MeetingOperation;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class MeetingAddMenuAction extends org.apache.struts.action.Action {
	
    private static final String SUCCESS = "success";
	private GetConfigFile gcf = new GetConfigFile();//读取配置

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		HttpSession session = request.getSession();
//		SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in MeetingAddMenuAction");
		Object token = session.getAttribute("token");
		if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
		MeetingOperation mo = new MeetingOperation(session.getAttribute("orgid").toString());
		ArrayList groupList = mo.queryGroup(token.toString());
		request.setAttribute("groupList", groupList);		
        Date DT = new Date();
        SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String StartDate = SimDF.format(new Date(DT.getTime() - 60 * 1000));  //提前一分钟,解决新建会议室提示"会议尚未开始"
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 50);
        long date = cal.getTimeInMillis();
        String endDate = SimDF.format(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.MONTH, 1);
        long date1 = cal1.getTimeInMillis();
        String endDate1 = SimDF.format(date1);
        request.setAttribute("btime", StartDate);
        request.setAttribute("etime", endDate);
        request.setAttribute("etime1", endDate1);
        request.setAttribute("soft_version", PropUtil.getInstance().getValue("soft.version"));
        //System.out.println("action中："+PropUtil.getInstance().getValue("soft.version"));
    	return mapping.findForward(SUCCESS);
    }
}
