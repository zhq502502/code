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

public class TopUserInfoAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private GetConfigFile gcf = new GetConfigFile();//读取配置
    private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	HttpSession session = request.getSession();
//    	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in TopUserInfoAction");
    	Object token = session.getAttribute("token");
    	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
    	Object orgid = session.getAttribute("orgid");
    	
    	HttpClient hc = new HttpClient(url,orgid.toString());
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("accessKey", token.toString())); 
		JSONObject json = hc.getJObject("currentuser", params);
		String usertype = json.get("usertype").toString();
		String userid = json.get("userid").toString();
		String username = json.get("username").toString();
		String email = json.get("email").toString();
		String phone = json.get("phone").toString();
		System.out.println("用户类型："+usertype+"|ID:"+userid+"|昵称："+username+"|邮箱："+email+"|电话："+phone);

	    request.setAttribute("usertype", usertype);
	    request.setAttribute("userid", userid);
	    request.setAttribute("username", username);
	    request.setAttribute("email", email);
	    request.setAttribute("phone", phone);
	    
        return mapping.findForward(SUCCESS);
    }
}
