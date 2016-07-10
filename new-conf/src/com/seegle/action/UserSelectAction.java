package com.seegle.action;

import com.seegle.data.GetConfigFile;
import com.seegle.data.UserOperation;
import com.seegle.form.UserInfoActionForm;
import com.seegle.util.SeegleLog;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class UserSelectAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {    	
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in UserSelectAction");
    	Object orgid = session.getAttribute("orgid");
    	Object account = session.getAttribute("userid");
        UserOperation uo = new UserOperation(orgid.toString());
        String Keyword = request.getParameter("Keyword");
        if (Keyword != null) {
            //Keyword = new String(Keyword.getBytes("ISO-8859-1"), "UTF-8");  //中文转码
        }
        Keyword = Keyword.trim();
        GetConfigFile gcf = new GetConfigFile();
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
        ArrayList aList = uo.queryUserlist(orgid.toString(),Keyword,"admin", account.toString(),firstIndex, showNumber);

        if (aList.size() > 0) {
        	UserInfoActionForm uif = (UserInfoActionForm) aList.get(0);
            request.setAttribute("pageNumber", uif.getPageNumber());//分页数
        } else {
        	if(nowPage>1){
        		aList = uo.queryUserlist(orgid.toString(),Keyword,"admin", account.toString(),firstIndex-showNumber, showNumber);
        		UserInfoActionForm uif = (UserInfoActionForm) aList.get(0);
                request.setAttribute("pageNumber", uif.getPageNumber());//分页数
                nowPage = nowPage-1;
        	}
            request.setAttribute("pageNumber", 1);//分页数 
        }
        
        request.setAttribute("userList", aList);
        request.setAttribute("nowPage", nowPage);  //当前页
        request.setAttribute("Keyword", Keyword);  //根据关键字查询列表

        return mapping.findForward(SUCCESS);
    }
}
