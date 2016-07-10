package com.seegle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.io.File;

import com.seegle.data.PhoneUserOperation;
import com.seegle.data.SQLOperation;
import com.seegle.data.UserOperation;
import com.seegle.form.UserInfoActionForm;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

/**
 * 登录后的主页,包含左侧边栏,头部,底部
 */
public class ConfPageAction extends org.apache.struts.action.Action {

    private static final String DEFAULT = "ConfList"; //默认加载页会议室列表
    private static final String ERROR = "error"; //出现错误，跳转到登录页
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
     	Object token = session.getAttribute("token");
     	Object account = session.getAttribute("userid");
		Object orgid = session.getAttribute("orgid");
		phoneUserOper = new PhoneUserOperation(orgid.toString());
     	if(token==null) {
     		return mapping.findForward(ERROR); //如果token为空，跳转到登录页
		}
//		SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).debug("debug in ConfPageAction");
     	String inc = "";
        inc = request.getParameter("inc");
     	
     	//获取用户信息
     	UserOperation uo = new UserOperation(orgid.toString());
     	UserInfoActionForm uif =new UserInfoActionForm();
     	uif = uo.getSingleUser(orgid.toString(),account.toString());
     	if(uif!=null){
     		String alias = uif.getAlias();
     		String home_username = account.toString();
     		if(alias!=null&&!alias.equals("")){	
     			home_username = alias;
     		}
     		if(session.getAttribute("urlalias")!=null&&!session.getAttribute("urlalias").equals("")){
     			home_username = session.getAttribute("urlalias").toString();
     		}
     		session.setAttribute("alias", home_username);
     		session.setAttribute("proxytype", uif.getProxytype());
     		session.setAttribute("proxyaddr", uif.getProxyaddr());
     		session.setAttribute("proxyport", uif.getProxyport());
     		session.setAttribute("proxyuser", uif.getProxyuser());
     		session.setAttribute("proxypass", uif.getProxypass());
     	}
		
//		String values[] = PropUtil.getInstance().getValue("download.meetUnOnline").split(",");
//        session.setAttribute("download_meetUnOnline", values[4]);
		String soft_version = PropUtil.getInstance().getValue("soft.version");
        session.setAttribute("soft_version", soft_version);
        
		String userManage = PropUtil.getInstance().getValue("userManage");
        session.setAttribute("userManage", userManage);
        
		SQLOperation so = new SQLOperation();
		String userrole = so.getUserrole(orgid.toString(),account.toString());
		session.setAttribute("userrole", userrole); //获取用户权限
//		String footcontent = PropUtil.getInstance().getValue("footcontent");
//        session.setAttribute("footcontent", footcontent);//获取页面底部内容
        
//        String titlecontent = PropUtil.getInstance().getValue("titlecontent");
//        session.setAttribute("titlecontent", titlecontent);//获取页面title内容
        
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
		//SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("logo路径为"+logopath1);
		session.setAttribute("logopath", logopath1);
		
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		session.setAttribute("siteurl", webSiteUrl);
		session.setAttribute("menu_default", PropUtil.getInstance().getValue("menu.default"));
		//SeegleLog.getInstance().getLog(this.getClass(), orgid.toString()).info("inc路径为"+inc);
		
		//读取会议默认参数配置
		session.setAttribute("conf_lock_flag", PropUtil.getInstance().getValue("conf.lockflag"));
		session.setAttribute("conf_auto_clear_flag", PropUtil.getInstance().getValue("conf.autoclearflag"));
		session.setAttribute("conf_download_mode", PropUtil.getInstance().getValue("conf.downloadmode"));
		session.setAttribute("conf_open_audit", PropUtil.getInstance().getValue("conf.openaudit"));
		session.setAttribute("conf_video_quality", PropUtil.getInstance().getValue("conf.videoquality"));
		session.setAttribute("conf_vrenderer", PropUtil.getInstance().getValue("conf.vrenderer"));

        if(inc==null||"".equals(inc)){
        	return mapping.findForward(DEFAULT);
        }else{
        	return mapping.findForward(inc);
        }
    }
}
