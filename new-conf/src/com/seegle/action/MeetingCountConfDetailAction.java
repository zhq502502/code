package com.seegle.action;

import java.util.ArrayList;
import com.seegle.data.ConfCountOperation;
import com.seegle.data.GetConfigFile;
import com.seegle.util.SeegleLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MeetingCountConfDetailAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private GetConfigFile gcf = new GetConfigFile();//读取配置
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in MeetingCountConfDetailAction");
    	Object token = session.getAttribute("token");
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	String confid = request.getParameter("confid");
    	String begintime = request.getParameter("begintime");
    	String endtime = request.getParameter("endtime");

        ConfCountOperation cco = new ConfCountOperation(session.getAttribute("orgid").toString());
        
        int firstIndex = 0;  //显示记录的起始位置
        int showNumber = Integer.parseInt(gcf.getConfig("SHOW_NUMBER"));  //每页显示记录数
        int queryNumber = 1;  //查询页数
        int nowPage = 1;  //当前页
        String page = request.getParameter("page");//所要查询的页数
        if (page != null) {//如果用户选择页数
            queryNumber = Integer.parseInt(page);
            if (queryNumber > 1) {  //如果查询的页数大于1
                nowPage = queryNumber;
                firstIndex = showNumber * queryNumber - showNumber;  //显示记录的起始位置
            }
        }
       
        int maxNumber = cco.getSingleConfLogCount(token.toString(), begintime, endtime, confid);
        int pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数
        if (maxNumber > 0) {
            request.setAttribute("nowPage", nowPage);  //当前页
            request.setAttribute("pageNumber", pageNumber);//分页数
        } else {
            request.setAttribute("nowPage", 1);  //当前页
            request.setAttribute("pageNumber", 1);//分页数
        }
        
        ArrayList cdList = new ArrayList();	
        cdList = cco.getSingleConfLogInfo(token.toString(), begintime, endtime, confid, firstIndex, showNumber);
        request.setAttribute("cdList", cdList); 
        request.setAttribute("begintime", begintime);
        request.setAttribute("endtime", endtime);
        request.setAttribute("confid", confid);

        return mapping.findForward(SUCCESS);
    }
}
