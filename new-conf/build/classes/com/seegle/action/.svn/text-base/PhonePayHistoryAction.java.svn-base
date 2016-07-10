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
import com.seegle.data.PhoneChargeOperation;
import com.seegle.form.AccountSumForm;

public class PhonePayHistoryAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String top_PayHistory = "top_PayHistory";
    private static final String ChargeHistory = "ChargeHistory";
    private static final String top_ChargeHistory = "top_ChargeHistory";
    private GetConfigFile gcf = new GetConfigFile();//读取配置
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	Object orgid = session.getAttribute("orgid");
    	
    	String searchorgid = orgid.toString();
    	//if(!(request.getParameter("sorgid")==null||request.getParameter("sorgid").equals(""))){
    		searchorgid = request.getParameter("sorgid");
    	/*}else{
    		searchorgid = "";
    	}*/
    	String begintime="";
    	String endtime="";
    	if(request.getParameter("begintime")==null||"null".equals(request.getParameter("begintime"))){
        	Date DT = new Date();
            SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            endtime = SimDF.format(new Date(DT.getTime())); 
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            long date = cal.getTimeInMillis();
            begintime = SimDF.format(date);
    	}else{
    		begintime = request.getParameter("begintime");
    		endtime = request.getParameter("endtime");
    	}
    	String recordType = request.getParameter("recordType");
    	if(recordType==null||recordType.equals("")){
    		recordType = "0";
    	}
    	request.setAttribute("recordType", recordType);
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
        System.out.println("查询历史记录firstIndex="+firstIndex+";queryNumber="+queryNumber+";showNumber="+showNumber);
        
        PhoneChargeOperation pco = new PhoneChargeOperation(orgid.toString());
        ArrayList phList = new ArrayList();	
    	phList = pco.getPayHistory(token.toString(), searchorgid, begintime, endtime, firstIndex, showNumber,recordType);
    	request.setAttribute("phList", phList);
		 int maxNumber = pco.getPayHistoryCount(token.toString(), searchorgid,begintime,endtime,recordType);
		 int pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数
		 if (maxNumber > 0) {
		     request.setAttribute("nowPage", nowPage);  //当前页
		 request.setAttribute("pageNumber", pageNumber);//分页数
		 } else {
		     request.setAttribute("nowPage", 1);  //当前页
		 request.setAttribute("pageNumber", 1);//分页数
		 }
		
		request.setAttribute("begintime", begintime);
		request.setAttribute("endtime", endtime);
		
    	AccountSumForm asf = new AccountSumForm();
    	asf = pco.getAccountinfo(token.toString(), searchorgid==null||searchorgid.equals("")?orgid.toString():searchorgid);
		request.setAttribute("accountinfo", asf);
		request.setAttribute("searchorgid", searchorgid);
		
		String top = request.getParameter("top");
		if(!(top==null||top.equals(""))){
			request.setAttribute("top", top);
			if(recordType.equals("1")){
				return mapping.findForward(top_ChargeHistory);
			}
			return mapping.findForward(top_PayHistory);
		}
		if(recordType.equals("1")){
			return mapping.findForward(ChargeHistory);
		}
        return mapping.findForward(SUCCESS);
    }
}

