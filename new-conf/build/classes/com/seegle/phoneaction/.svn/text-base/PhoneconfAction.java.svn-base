package com.seegle.phoneaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;

import com.seegle.beans.PhoneConf;
import com.seegle.beans.PhoneFacility;
import com.seegle.data.MeetingOperation;
import com.seegle.data.PhoneConfOperation;
import com.seegle.util.PropUtil;
import com.seegle.util.SGMd5;
/**
 * 会议操作。ajax操作
 * @author zhangqing
 * @date 2013-11-20 下午02:32:40
 */
public class PhoneconfAction extends Action {
	
	PhoneConfOperation confOper = null;
	
	public PhoneconfAction(){
		
	}
	private SGMd5 md5Unity =  new SGMd5();
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String method=request.getParameter("method");
		HttpSession session = request.getSession();
		if(session.getAttribute("token")==null) {
     		return mapping.findForward("error"); //如果token为空，跳转到登录页
		}
     	String orgid = session.getAttribute("orgid").toString();
     	
//     	String bytetype = request.getCharacterEncoding();
//     	String contenttype = request.getContentType();
//     	request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=utf-8");
		confOper = new PhoneConfOperation(orgid);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		request.setAttribute("nowtime", format.format(cal.getTime()));
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, cal1.get(Calendar.MONTH)+6);
		request.setAttribute("nexttime", format.format(cal1.getTime()));
		
		
		if(method==null||method.equals("")){
			String otherorgid1 = request.getParameter("otherorgid");
			if(otherorgid1!=null&&!otherorgid1.equals("")){
				session.setAttribute("otherorgid", otherorgid1);
			}else{
				session.setAttribute("otherorgid", 0);
			}
			String removeother = request.getParameter("removeother");
			if(removeother!=null&&!removeother.equals("")){
				session.removeAttribute("otherorgid");
			}
			return list(mapping, form, request, response);
		}if(method.equals("goadd")){
			return goadd(mapping, form, request, response);
		}if(method.equals("goedit")){
			return goedit(mapping, form, request, response);
		}if(method.equals("add")){
			return add(mapping, form, request, response);
		}if(method.equals("edit")){
			return edit(mapping, form, request, response);
		}if(method.equals("del")){
			return del(mapping, form, request, response);
		}if(method.equals("lock")){
			return lock(mapping, form, request, response);
		}
		return null;
	}
	/**
	 * 添加会议室
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		String confname = request.getParameter("confname");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String room_password = request.getParameter("room_password");
		String manager_password = request.getParameter("manager_password");
		String max_user_count = request.getParameter("max_user_count");
		String description = request.getParameter("description");
		String vop_conf_id = request.getParameter("vop_conf_id");
		String verifycodevisible = request.getParameter("verifycodevisible");
		String join_number = request.getParameter("join_number");
		String srv_id = request.getParameter("srv_id");
		String status = request.getParameter("status");
		String conftype = request.getParameter("conftype");
		PhoneConf conf = new PhoneConf();
		conf.setBeginTime(begintime);
		conf.setConfName(confname);
		conf.setConftype(conftype);
		conf.setDescription(description);
		conf.setEndTime(endtime);
		conf.setJoinNumber(join_number);
		conf.setManagerPassword(md5Unity.getMD5ofStr(manager_password, "ASCII"));
		conf.setMaxUserCount(max_user_count);
		conf.setOrgid(orgid);
		conf.setRoomPassword(md5Unity.getMD5ofStr(room_password, "ASCII"));
		conf.setSrvId(srv_id);
		conf.setVopConfId(vop_conf_id);
		conf.setVerifyCodeVisible(verifycodevisible);
		
		response.getWriter().print(confOper.addPhoneconf(token, conf));
		return null;
	}
	/**
	 * 编辑会议室
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
     	String confid = request.getParameter("confid");
		String confname = request.getParameter("confname");
		String begintime = request.getParameter("begintime");
		String endtime = request.getParameter("endtime");
		String room_password = request.getParameter("room_password");
		String room_password2 = request.getParameter("room_password2");
		String manager_password = request.getParameter("manager_password");
		String manager_password2 = request.getParameter("manager_password2");
		String max_user_count = request.getParameter("max_user_count");
		String description = request.getParameter("description");
		String vop_conf_id = request.getParameter("vop_conf_id");
		String verifycodevisible = request.getParameter("verifycodevisible");
		String join_number = request.getParameter("join_number");
		String srv_id = request.getParameter("srv_id");
		String status = request.getParameter("status");
		String conftype = request.getParameter("conftype");
		PhoneConf conf = new PhoneConf();
		conf.setId(confid);
		conf.setBeginTime(begintime);
		conf.setConfName(confname);
		conf.setConftype(conftype);
		conf.setDescription(description);
		conf.setEndTime(endtime);
		conf.setJoinNumber(join_number);
		conf.setManagerPassword(md5Unity.getMD5ofStr(manager_password, "ASCII"));
		if(manager_password!=null&&manager_password.equals("password")){
			conf.setManagerPassword(manager_password2);
		}
		conf.setMaxUserCount(max_user_count);
		conf.setOrgid(orgid);
		conf.setRoomPassword(md5Unity.getMD5ofStr(room_password, "ASCII"));
		if(room_password!=null&&room_password.equals("password")){
			conf.setRoomPassword(room_password2);
		}
		conf.setSrvId(srv_id);
		conf.setVopConfId(vop_conf_id);
		conf.setVerifyCodeVisible(verifycodevisible);
		
		response.getWriter().print(confOper.editPhoneconf(token, conf));
		return null;
	}
	/**
	 * 删除会议室
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String confids = request.getParameter("confids");
     	String orgid = session.getAttribute("orgid").toString();
     	String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
     	response.getWriter().print(confOper.delPhoneconf(token,orgid, confids));
		return null;
	}
	/**
	 * 锁定会议室
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward lock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
     	String confids = request.getParameter("confids");
     	String lock = request.getParameter("lock");
     	
     	response.getWriter().print(confOper.lockPhoneconf(token,orgid, confids, lock));
		return null;
	}
	/**
	 * 电话会议列表
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
     	String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
     	String account = session.getAttribute("userid").toString();
     	String orgid = session.getAttribute("orgid").toString();
     	String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
     	String page = request.getParameter("page");
     	String conftype = request.getParameter("conftype");
     	
     	if(conftype==null||conftype.equals("")){
     		conftype = "2";
     		request.setAttribute("phoneTitle", "个人电话会议");
     	}else if(conftype.equals("0")){
     		request.setAttribute("phoneTitle", "公共电话会议");
     	}else if(conftype.equals("1")){
     		request.setAttribute("phoneTitle", "绑定电话会议");
     	}else if(conftype.equals("2")){
     		request.setAttribute("phoneTitle", "个人电话会议");
     	}else if(conftype.equals("3")){
     		request.setAttribute("phoneTitle", "所有个人电话");
     	}
     	if(page==null||page.equals("")){
     		page = "1";
     	}
     	int count = confOper.getPhoneConfCount(token, orgid, "", Integer.parseInt(conftype));
     	int onepagesize = Integer.parseInt(PropUtil.getInstance().getValue("SHOW_NUMBER"));
     	String Keyword = request.getParameter("Keyword");
     	request.setAttribute("nowPage", page);  //当前页
     	request.setAttribute("pageNumber", (count+onepagesize-1)/onepagesize);//分页数
     	List<PhoneConf> list = confOper.getPhoneConfList(token, orgid, Keyword,(Integer.parseInt(page)-1)*onepagesize,onepagesize, conftype==null?0:Integer.parseInt(conftype));
     	request.setAttribute("list", list);     	
     	request.setAttribute("Keyword", Keyword);
     	request.setAttribute("conftype", conftype);
     	String top = request.getParameter("top");
     	if(top==null||top.equals("")){
     		return mapping.findForward("list");
     	}else if(top.equals("top")){
     		request.setAttribute("top", top);
     		return mapping.findForward("listTop");
     	}else{
     		return mapping.findForward("list");
     	}
	}
	/**
	 * 跳转到公共电话会议添加页面
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward goadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		String conftype = request.getParameter("conftype");
		List<PhoneFacility> facilityList = new ArrayList<PhoneFacility>();
		facilityList = confOper.getPhoneFacilityList(token);
		request.setAttribute("facilityList", facilityList);
		request.setAttribute("conftype", conftype);
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		if(conftype!=null&&conftype.equals("1")){
			MeetingOperation mo = new MeetingOperation(orgid);
			List conflist = mo.queryMeetingList(null, 0, 1000, token, orgid);
			request.setAttribute("conflist", conflist);
		}
		String top = request.getParameter("top");
		if(top==null||top.equals("")){
     		return mapping.findForward("goadd");
     	}else if(top.equals("top")){
     		request.setAttribute("top", top);
     		return mapping.findForward("goaddTop");
     	}else{
     		return mapping.findForward("goadd");
     	}
	}
	/**
	 * 跳转到公共电话会议编辑页面
	 * @author zhangqing
	 * @date 2013-12-23 下午03:05:00
	 */
	public ActionForward goedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String token = session.getAttribute("token")==null?null:session.getAttribute("token").toString();
		List<PhoneFacility> facilityList = new ArrayList<PhoneFacility>();
		facilityList = confOper.getPhoneFacilityList(token);
		
		String conftype = request.getParameter("conftype");
		String confid = request.getParameter("confid");
		String orgid = session.getAttribute("orgid").toString();
		String otherorgid = session.getAttribute("otherorgid")==null?null:session.getAttribute("otherorgid").toString();
     	if(!(otherorgid==null||otherorgid.equals(""))){
     		orgid = otherorgid;
     	}
		request.setAttribute("facilityList", facilityList);
		request.setAttribute("conftype", conftype);
		PhoneConf conf = confOper.getPhoneConfById(token,orgid, confid);
		request.setAttribute("conf", conf);
		String top = request.getParameter("top");
     	if(top==null||top.equals("")){
     		return mapping.findForward("goadd");
     	}else if(top.equals("top")){
     		request.setAttribute("top", top);
     		return mapping.findForward("goaddTop");
     	}else{
     		return mapping.findForward("goadd");
     	}
	}
	
}
