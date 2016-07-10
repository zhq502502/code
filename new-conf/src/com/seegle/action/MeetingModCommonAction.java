package com.seegle.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.seegle.data.GetConfigFile;
import com.seegle.data.UserOperation;
import com.seegle.form.UserInfoActionForm;



public class MeetingModCommonAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	Object orgid = session.getAttribute("orgid");
    	String confid = request.getParameter("confid");
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
        
        UserOperation uo = new UserOperation(orgid.toString());
        ArrayList userList = uo.queryCommonList(orgid.toString(), token.toString(), confid);
        //所有非会议室默认与会者
        ArrayList ucommonList = new ArrayList();
        for(int i=0;i<userList.size();i++){
        	UserInfoActionForm uif =  (UserInfoActionForm)userList.get(i);
        	if(!uif.getSelected()){
        		ucommonList.add(uif);
        	}
        }
        //会议室所有的默认与会者
        ArrayList AllcommonList = new ArrayList();
        for(int i=0;i<userList.size();i++){
        	UserInfoActionForm uif =  (UserInfoActionForm)userList.get(i);
        	if(uif.getSelected()){
        		AllcommonList.add(uif);
        	}
        }
        //查询当前选中页默认与会者列表
        ArrayList commonList = new ArrayList();
        if(AllcommonList.size()>firstIndex){
        	for(int i=firstIndex;i<AllcommonList.size()&&i<(firstIndex+showNumber);i++){
        		UserInfoActionForm uif =  (UserInfoActionForm)AllcommonList.get(i);
        		commonList.add(uif);
        	}
    		int pageNumber = (AllcommonList.size() + showNumber - 1) / showNumber; 
            request.setAttribute("nowPage", nowPage);  //当前页
            request.setAttribute("pageNumber", pageNumber);//分页数
        }else{
        	if(nowPage>1){
            	for(int i=firstIndex-showNumber;i<AllcommonList.size()&&i<firstIndex;i++){
            		UserInfoActionForm uif =  (UserInfoActionForm)AllcommonList.get(i);
            		commonList.add(uif);
            	}    
    			int pageNumber = (AllcommonList.size() + showNumber - 1) / showNumber; 
                request.setAttribute("nowPage", nowPage-1);  //当前页
                request.setAttribute("pageNumber", pageNumber);//分页数
        	}else{
        		request.setAttribute("nowPage", 1);  //当前页
                request.setAttribute("pageNumber", 1);//分页数
        	}
        }

        request.setAttribute("commonList", commonList);        
        request.setAttribute("ucommonList", ucommonList);   
        request.setAttribute("confid", confid);
        return mapping.findForward(SUCCESS);   	
    }
}

