package com.seegle.action;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.MeetingOperation;
import com.seegle.form.ConfRoomActionForm;
import com.seegle.util.SeegleLog;

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

public class TopModOrgNameAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private GetConfigFile gcf = new GetConfigFile();//读取配置
    private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in TopModOrgNameAction");
    	Object token = session.getAttribute("token");
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	Object orgid = session.getAttribute("orgid");
    	
    	HttpClient hc = new HttpClient(url,orgid.toString());
    	List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("accessKey", token.toString())); 
		params1.add(new BasicNameValuePair("orgid", orgid.toString())); 
		JSONObject json1 = hc.getJObject("orgname", params1);
		String org_name = json1.get("orgname").toString();
		System.out.println("企业名称:"+org_name);

	    String current_name = org_name;
	    session.setAttribute("currentname", current_name);
        return mapping.findForward(SUCCESS);
    }
}
