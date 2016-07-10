package com.seegle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.PhoneUserOperation;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

/**
 * 带协同账号登录后的主页,包含左侧边栏,头部,底部
 */
public class TopConfPageAction extends org.apache.struts.action.Action {

    private static final String DEFAULT = "TopConfList"; //默认加载页会议室列表
    private static final String ERROR = "error"; //出现错误，跳转到登录页
    private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private PhoneUserOperation phoneUserOper;

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	request.setCharacterEncoding("utf-8");
     	HttpSession session = request.getSession();
//     	SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in TopConfPageAction");
     	Object token = session.getAttribute("token");
     	if(token==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
     	Object userid = session.getAttribute("userid");
		Object orgid = session.getAttribute("orgid");
		if(token==null) {
     		return mapping.findForward(ERROR); //如果token为空，跳转到登录页
		}
		phoneUserOper = new PhoneUserOperation(orgid.toString());
     	String inc = "";
        inc = request.getParameter("inc");
     	if(token==null) {
     		return mapping.findForward(ERROR); //如果token为空，跳转到登录页
		}
     	
     	//获取企业名称
     	HttpClient hc = new HttpClient(url,orgid.toString());
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("accessKey", token.toString())); 
		params1.add(new BasicNameValuePair("orgid", orgid.toString())); 
		JSONObject json1 = hc.getJObject("orgname", params1);
		String org_name = "";
		if(json1.get("orgname")!=null){
			org_name = json1.get("orgname").toString();
		}
		//String org_name = json1.get("orgname").toString();
		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("企业名称:"+org_name);
		session.setAttribute("orgname", org_name);
		
     	//获取用户昵称
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("accessKey", token.toString())); 
		JSONObject json = hc.getJObject("currentuser", params);
		String home_username = userid.toString();		
		if(json!=null){	
			if(json.get("username")!=null&&!"".equals(json.get("username"))){
				home_username = json.get("username").toString();
			}
			if(json.get("nickname")!=null&&!"".equals(json.get("nickname"))){
				home_username = json.get("nickname").toString();
			}
		}
		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("用户昵称:"+home_username);
		session.setAttribute("alias", home_username);
		
		
//		String values[] = PropUtil.getInstance().getValue("download.seegleOnline").split(",");
//        session.setAttribute("download_seegleOnline", values[4]);
        
//        String footcontent = PropUtil.getInstance().getValue("footcontent");
//        session.setAttribute("footcontent", footcontent);//获取页面底部内容
//        String titlecontent = PropUtil.getInstance().getValue("titlecontent");
//        session.setAttribute("titlecontent", titlecontent);//获取页面title内容
        
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		session.setAttribute("siteurl", webSiteUrl);
		request.setAttribute("top_menu_default", PropUtil.getInstance().getValue("top.menu.default"));
		
		//SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("inc路径为"+inc);
        if(inc==null||"".equals(inc)){
        	return mapping.findForward(DEFAULT);
        }else{
        	return mapping.findForward(inc);
        }
    }
}
