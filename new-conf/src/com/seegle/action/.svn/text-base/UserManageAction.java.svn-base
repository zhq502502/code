package com.seegle.action;

import java.util.ArrayList;

import com.seegle.data.GetConfigFile;
import com.seegle.data.SQLOperation;
import com.seegle.data.UserOperation;
import com.seegle.form.UserInfoActionForm;
import com.seegle.util.SeegleLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserManageAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in UserManageAction");
    	Object orgid = session.getAttribute("orgid");
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
        String account = "";
        account = session.getAttribute("userid").toString();
		ArrayList userList = uo.queryUserlist(orgid.toString(),"","admin",account,firstIndex, showNumber);
		if (userList.size() > 0) {
			UserInfoActionForm uif = (UserInfoActionForm) userList.get(0);
            request.setAttribute("nowPage", nowPage);  //当前页
            request.setAttribute("pageNumber", uif.getPageNumber());//分页数
        } else {
        	if(nowPage>1){
        		userList = uo.queryUserlist(orgid.toString(),"","admin","",firstIndex-showNumber, showNumber);
        		UserInfoActionForm uif = (UserInfoActionForm) userList.get(0);      	
                request.setAttribute("nowPage", nowPage-1);  //当前页
                request.setAttribute("pageNumber", uif.getPageNumber());//分页数
        	}else{
        		request.setAttribute("nowPage", 1);  //当前页
                request.setAttribute("pageNumber", 1);//分页数
        	}            
        }
        request.setAttribute("userList", userList);
        request.setAttribute("Keyword", "");  //清空搜索关键字
        return mapping.findForward(SUCCESS);
    }
}
