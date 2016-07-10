package com.seegle.phoneaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.beans.PhoneConf;
import com.seegle.beans.PhoneUser;
import com.seegle.beans.PhoneVerifycode;
import com.seegle.data.PhoneConfOperation;
import com.seegle.util.ExcelUtil;
/**
 * 公告会议
 * @author zhangqing
 * @date 2013-11-20 下午02:32:40
 */
public class PhoneUserAction extends Action {
	
	PhoneConfOperation confOper = null;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String method=request.getParameter("method");
		HttpSession session = request.getSession();
		if(session.getAttribute("token")==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
		if(session.getAttribute("orgid")==null){
			return null;
		}
		String orgid = session.getAttribute("orgid").toString();
     	confOper = new PhoneConfOperation(orgid);
     	
		if(method==null||method.equals("")){
			return list(mapping, form, request, response);
		}if(method.equals("add")){
			return add(mapping, form, request, response);
		}if(method.equals("edit")){
			return edit(mapping, form, request, response);
		}if(method.equals("del")){
			return del(mapping, form, request, response);
		}if(method.equals("call")){
			return call(mapping, form, request, response);
		}if(method.equals("mute")){
			return mute(mapping, form, request, response);
		}if(method.equals("muteall")){
			return muteall(mapping, form, request, response);
		}if(method.equals("sendmsg")){
			return sendmsg(mapping, form, request, response);
		}if(method.equals("status")){
			return status(mapping, form, request, response);
		}if(method.equals("goimport")){
			return goimport(mapping, form, request, response);
		}if(method.equals("importuser")){
			return importuser(mapping, form, request, response);
		}
		return null;
	}
	/**
	 * 电话会议联系人列表
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String confid = request.getParameter("confid");
     	String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
     	List<PhoneUser> list = confOper.getPhoneUserList(token,orgid, confid);
     	request.setAttribute("confid", confid);
     	request.setAttribute("list", list);
		return mapping.findForward("list");
	}
	
	/**
	 * 新建电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String username = request.getParameter("username");
		String userphone = request.getParameter("userphone");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		PhoneUser user = new PhoneUser();
		user.setConfid(confid);
		user.setName(username);
		user.setPhone(userphone);
		response.getWriter().print(confOper.addPhoneuser(token,orgid, user));
		return null;
	}
	/**
	 * 编辑电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String username = request.getParameter("username");
		String userphone = request.getParameter("userphone");
		String olduserphone = request.getParameter("olduserphone");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		PhoneUser user = new PhoneUser();
		user.setConfid(confid);
		user.setName(username);
		user.setPhone(userphone);
		response.getWriter().print(confOper.editPhoneuser(token,orgid, user,olduserphone));
		return null;
	}
	/**
	 * 删除电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String phones = request.getParameter("phones");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		response.getWriter().print(confOper.delPhoneuser(token,orgid, confid, phones));
		return null;
	}
	/**
	 * 呼叫电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward call(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String phones = request.getParameter("phones");
		String call = request.getParameter("call");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		response.getWriter().print(confOper.callPhoneuser(token,orgid, confid, phones, call));
		return null;
	}
	/**
	 * 禁言电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward mute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String phones = request.getParameter("phones");
		String mute = request.getParameter("mute");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		response.getWriter().print(confOper.mutePhoneuser(token,orgid, confid, phones, mute));
		return null;
	}
	/**
	 * 禁言电话所有会议联系人
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward muteall(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String mute = request.getParameter("mute");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		response.getWriter().print(confOper.muteAllPhoneuser(token,orgid, confid, mute));
		return null;
	}
	/**
	 * 发送短信
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward sendmsg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String phones = request.getParameter("phones");
		String msg = request.getParameter("msg");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		response.getWriter().print(confOper.sendTextMessage(token,orgid, confid, phones, msg));
		return null;
	}
	/**
	 * 更新状态
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward status(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		List<PhoneUser> list = confOper.getPhoneUserList(token,orgid, confid);
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(PhoneUser user:list){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("phone", user.getPhone());
			map.put("onlineflag", user.getOnlineflag());
			map.put("joinflag", user.getJoinflag());
			map.put("muteflag", user.getMuteflag());
			map.put("time", user.getTime());
			listMap.add(map);
		}
		String resutlt = JSONArray.toJSONString(listMap);
		response.getWriter().print(resutlt);
		return null;
	}
	/**
	 * 跳转到用户导入界面
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward goimport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
     	String confid = request.getParameter("confid");
     	request.setAttribute("confid", confid);
		return mapping.findForward("goimport");
	}
	
	/**
	 * 用户导入
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward importuser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String confid = request.getParameter("confid");
		request.setAttribute("confid", confid);
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		List<Map<String,String>> okList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> errorList = new ArrayList<Map<String,String>>();
		String msg = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request); //检查输入请求是否为multipart表单数据
		if(isMultipart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			try{					
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    upload.setSizeMax(2*1024*1024); //最大2M
		    List<FileItem> items = upload.parseRequest(request);
		    Iterator<FileItem> itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem) itr.next();
		        //检查当前项目是普通表单项目还是上传文件。
		        if (item.isFormField()) {  //是普通表单项目
		        } else {
		        	if(item.getSize()!=0){   //文件大小不为零
		        		
		        		String filePath = item.getName();
		        		if(filePath!=null&&!filePath.trim().equals("")){ //文件名不为空
		        			int point = filePath.lastIndexOf("."); 
		        			String extension = filePath.substring(point+1,filePath.length()); //获取文件后缀名
		        			if(extension.equals("xls") || extension.equals("xlsx") ){
		        				List<List<String>> list = ExcelUtil.readExcelByStream(item.getInputStream(), 0);
		        				int size = list.size();
		        				for(int i=1;i<size;i++){
		        					List<String> u = list.get(i);
		        					if((u.get(1)==null||u.get(1).trim().equals(""))&&(u.get(0)==null||u.get(0).trim().equals(""))){
		        						continue;
		        					}
		        					PhoneUser user = new PhoneUser();
		        					user.setConfid(confid);
		        					user.setPhone(u.get(1));
		        					user.setName(u.get(0));
		        					int result = Integer.parseInt(confOper.addPhoneuser(token,orgid, user));
		        					Map<String,String> map = new HashMap<String,String>();
		        					map.put("name", u.get(0));
		        					map.put("phone", u.get(1));
		        					if(result==0){
		        						okList.add(map);
		        					}else{
		        						errorList.add(map);
		        					}
		        				}
		        				msg = "导入完成";
		        			}else{
		        				msg = "上传格式错误";
		        			}	
		        		}else{
		        			msg = "上传文件错误";
		        		}
		        	}else{
		        		msg = "文件为空";
		        	}
		        }  
		    }
			}catch (Exception ex) {
				ex.printStackTrace();
	        }
		}else{
			msg = "页面表单错误";
		}
		request.setAttribute("okList", okList);
		request.setAttribute("errorList", errorList);
		request.setAttribute("msg", msg);
		return mapping.findForward("importsuccess");
	}
}
