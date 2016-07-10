package com.seegle.action;

import java.util.ArrayList;

import com.seegle.data.GetConfigFile;
import com.seegle.data.SQLOperation;
import com.seegle.data.UserOperation;
import com.seegle.form.ConfRoomActionForm;
import com.seegle.form.UserInfoActionForm;
import com.seegle.util.SeegleLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConfSetManagerAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
    	HttpSession session = request.getSession();
    	Object orgid = session.getAttribute("orgid");
//    	SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).debug("debug in ConfSetManagerAction");
    	GetConfigFile gcf = new GetConfigFile();
    	UserOperation uo = new UserOperation(orgid.toString());
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

        //查询所有非会议管理员记录
		ArrayList ucmList = uo.queryManagerList("uconfManager",orgid.toString(),"","admin","",firstIndex, showNumber);
        //查询当前页会议管理员记录
		ArrayList cmList = uo.queryManagerList("confManager",orgid.toString(),"","admin","",firstIndex, showNumber);
		if (cmList.size() > 0) {
			int pageNumber = uo.getListPage("confManager", orgid.toString(), "", "admin", "", showNumber) ; //分页数
			System.out.println(pageNumber);
            request.setAttribute("nowPage", nowPage);  //当前页
            request.setAttribute("pageNumber", pageNumber);//分页数
        } else {
        	if(nowPage>1){
        		cmList = uo.queryManagerList("confManager",orgid.toString(),"","admin","",firstIndex-showNumber, showNumber);
    			int pageNumber = uo.getListPage("confManager", orgid.toString(), "", "admin", "", showNumber) ; //分页数
                request.setAttribute("nowPage", nowPage-1);  //当前页
                request.setAttribute("pageNumber", pageNumber);//分页数
        	}else{
        		request.setAttribute("nowPage", 1);  //当前页
                request.setAttribute
                ("pageNumber", 1);//分页数
        	}            
        }
        request.setAttribute("cmList", cmList);
        request.setAttribute("ucmList", ucmList);
        return mapping.findForward(SUCCESS);
    }
}
