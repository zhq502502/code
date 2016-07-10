package com.seegle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.io.File;

import com.seegle.data.GetConfigFile;
import com.seegle.data.SQLOperation;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

/**
 * 游客登陆后页面,包含头部,底部
 */
public class GuestConfPageAction extends org.apache.struts.action.Action {

    private static final String DEFAULT = "GuestConfList"; //默认加载页游客会议室列表
    private static final String ERROR = "error"; //出现错误，跳转到登录页
    private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"

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
     	Object token = session.getAttribute("token");
		Object orgid = session.getAttribute("orgid");
     	String inc = "";
        inc = request.getParameter("inc");
     	if(token==null) {
     		return mapping.findForward(ERROR); //如果token为空，跳转到登录页
		}  
//		SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).debug("debug in GuestConfPageAction");
		
     	SQLOperation so = new SQLOperation();
     	//获取企业logo
		String orglogo = so.getOrglogo(orgid.toString());
		String path1 = "/"+PropUtil.getInstance().getValue("logo.path")+"/"+orgid.toString()+"/"+orglogo;
		String path =  session.getServletContext().getRealPath(path1);
		String logopath1 = "";
		File file = new File(path);
		if(orglogo==null||orglogo.equals("")||(!file.exists())){
			logopath1 = PropUtil.getInstance().getValue("logo.path")+"/"+PropUtil.getInstance().getValue("logo.default");
		}else{
		    if (file.isFile()&&file.exists()) {   
	            logopath1 = PropUtil.getInstance().getValue("logo.path")+"/"+orgid.toString()+"/"+orglogo;
	        }
		}
		
//		String values[] = PropUtil.getInstance().getValue("download.meetUnOnline").split(",");
//        session.setAttribute("download_meetUnOnline", values[4]);
		
//		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("logo路径为"+logopath1);
		session.setAttribute("logopath", logopath1);
		
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		session.setAttribute("siteurl", webSiteUrl);
		
//		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("inc路径为"+inc);
		request.setAttribute("client_registry", PropUtil.getInstance().getValue("client.registry"));
        request.setAttribute("client_confname", PropUtil.getInstance().getValue("client.confname"));
        request.setAttribute("client_sgplayname", PropUtil.getInstance().getValue("client.sgplayname"));
		if(inc==null||"".equals(inc)){
        	return mapping.findForward(DEFAULT);
        }else{
        	return mapping.findForward(inc);
        }
    }
}
