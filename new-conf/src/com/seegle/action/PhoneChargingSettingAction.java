package com.seegle.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import com.seegle.beans.PhoneOperuser;
import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.PhoneChargeOperation;
import com.seegle.data.PhoneOperuserOper;
import com.seegle.form.AccountSumForm;
import com.seegle.form.PriceinfoForm;

public class PhoneChargingSettingAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String top_ChargingSetting = "top_ChargingSetting";
    private GetConfigFile gcf = new GetConfigFile();//读取配置
    private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
    	Object token = session.getAttribute("token");
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	Object orgid = session.getAttribute("orgid");
    	String searchorgid = "0";
    	if(request.getParameter("sorgid")==null||request.getParameter("sorgid").equals("")){
    		searchorgid = "0";
    	}else{
    		searchorgid = request.getParameter("sorgid");
    	}
    	//获取企业账户信息
    	PhoneChargeOperation pco = new PhoneChargeOperation(orgid.toString());
    	AccountSumForm asf = new AccountSumForm();
    	asf = pco.getAccountinfo(token.toString(), searchorgid);
    	
    	//获取当前的计费单价信息
    	ArrayList<PriceinfoForm> piList = new ArrayList<PriceinfoForm>();	
    	piList = pco.getPriceinfo(token.toString(), searchorgid);
    	PriceinfoForm pif = new PriceinfoForm();
    	if(piList.size()>0){
    		pif = piList.get(0);
    	}
    	
    	//获取企业名称
    	HttpClient hc = new HttpClient(url,orgid.toString());
    	List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("accessKey", token.toString())); 
		params1.add(new BasicNameValuePair("orgid", searchorgid)); 
		JSONObject json1 = hc.getJObject("orgname", params1);
		String org_name = "";
		String orgstatus = "-1";
		if(json1.get("orgname")!=null){
			orgstatus = "0";
			org_name = json1.get("orgname").toString();
		}else{
			orgstatus = "1";
		}
		
    	request.setAttribute("accountinfo", asf);
     	request.setAttribute("sorgid", searchorgid);
     	request.setAttribute("orgname", org_name);    
     	request.setAttribute("priceinfo", pif); 
     	request.setAttribute("orgstatus", orgstatus); 
     	
     	//获取企业提醒人
     	PhoneOperuserOper oper = new PhoneOperuserOper(orgid.toString());
     	List<PhoneOperuser> remindUserList = oper.getRemindUserlist(token.toString(), searchorgid);
     	for(PhoneOperuser user:remindUserList){
     		if(user.getAccount()==1){
     			request.setAttribute("remindaccount1", user.getAccount());
     			request.setAttribute("remindname1", user.getAlias());
     			request.setAttribute("remindmail1", user.getEmail());
     		}
     		if(user.getAccount()==2){
     			request.setAttribute("remindaccount2", user.getAccount());
     			request.setAttribute("remindname2", user.getAlias());
     			request.setAttribute("remindmail2", user.getEmail());
     		}
     		if(user.getAccount()==3){
     			request.setAttribute("remindaccount3", user.getAccount());
     			request.setAttribute("remindname3", user.getAlias());
     			request.setAttribute("remindmail3", user.getEmail());
     		}
     	}
     	request.setAttribute("reminduser", remindUserList);
     	String top = request.getParameter("top");
		if(!(top==null||top.equals(""))){
			request.setAttribute("top", top);
			return mapping.findForward(top_ChargingSetting);
		}
        return mapping.findForward(SUCCESS);
    }
}

