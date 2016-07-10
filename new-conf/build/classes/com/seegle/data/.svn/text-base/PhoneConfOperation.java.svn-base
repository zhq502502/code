package com.seegle.data;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.beans.PhoneConf;
import com.seegle.beans.PhoneFacility;
import com.seegle.beans.PhoneUser;
import com.seegle.beans.PhoneVerifycode;
import com.seegle.util.PropUtil;

public class PhoneConfOperation {
	private String orgid;
	private HttpClient hc;
	
	public PhoneConfOperation(String orgid) throws UnsupportedEncodingException{
		hc = new HttpClient(PropUtil.getInstance().getValue("apiUrl"), orgid);
	}
	/**
	 * 获取设备列表
	 * @author zhangqing
	 * @date 2013-12-20 下午05:38:44
	 * @param token
	 * @return
	 */
	public List<PhoneFacility> getPhoneFacilityList(String token){
		List<PhoneFacility> list = new ArrayList<PhoneFacility>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		JSONArray jsonarr = hc.getJArray("getphonefacilitylist", params);
		int size = jsonarr.size();
		for(int i = 0;i<size;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneFacility facility = new PhoneFacility();
			facility.setId(obj.get("id")==null?"":obj.get("id").toString());
			facility.setJoinnumber(obj.get("joinnumberlist")==null?"":obj.get("joinnumberlist").toString());
			facility.setSrvname(obj.get("srvname")==null?"":obj.get("srvname").toString());
			list.add(facility);
		}
		return list;
	}
	/**
	 * 获得会议的总记录数
	 * @author zhangqing
	 * @date 2013-12-30 上午10:51:35
	 * @param token
	 * @param orgid
	 * @param confName
	 * @param type 会议类型
	 * @return
	 */
	public int getPhoneConfCount(String token,String orgid,String confName,int type){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		//params.add(new BasicNameValuePair("confName", token));
		params.add(new BasicNameValuePair("conftype", type+""));
		params.add(new BasicNameValuePair("orgid", orgid));
		JSONObject jsonarr = hc.getJObject("countphoneconf", params);
		return getIntString(jsonarr.get("msg")+"");
		
	}
	/**
	 * 电话会议列表
	 * @author zhangqing
	 * @date 2013-11-20 下午04:18:08
	 * @param token 密钥
	 * @param orgid 企业id
	 * @param confName 模糊查询电话会议名称
	 * @param start 起始记录
	 * @param count 总记录数
	 * @param type 电话会议类型
	 * @return
	 */
	public List<PhoneConf> getPhoneConfList(String token,String orgid,String confName,int start,int count,int type){
		List<PhoneConf> list = new ArrayList<PhoneConf>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		//params.add(new BasicNameValuePair("confName", token));
		params.add(new BasicNameValuePair("start", ""+start));
		params.add(new BasicNameValuePair("count", ""+count));
		params.add(new BasicNameValuePair("orgid", ""+orgid));
		params.add(new BasicNameValuePair("conftype", type+""));
		JSONArray jsonarr = hc.getJArray("getphonelist", params);
		int size = jsonarr.size();
		for(int i = 0;i<size;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneConf conf = new PhoneConf();
			conf.setConfName(obj.get("confname")==null?"":obj.get("confname").toString());
			conf.setBeginTime(obj.get("begintime")==null?"":obj.get("begintime").toString());
			conf.setEndTime(obj.get("endtime")==null?"":obj.get("endtime").toString());
			conf.setId(obj.get("cid")==null?"":obj.get("cid").toString());
			conf.setJoinNumber(obj.get("joinnumber")==null?"":obj.get("joinnumber").toString());
			conf.setVerifyCodeVisible(obj.get("verifycodevisible")==null?"":obj.get("verifycodevisible").toString());
			conf.setVerifyCode(obj.get("verifycode")==null?"":obj.get("verifycode").toString());
			String userid = "";
			if(obj.get("useraccount")==null||obj.get("useraccount").toString().equals("")){
				userid = obj.get("userid").toString();
				if(getOrgType(token, orgid)==0){
					if(userid.equals("1000")){
						userid = "admin";
					}else{
						userid = obj.get("useraccount")==null?"":obj.get("useraccount").toString();
					}
				}
			}else{
				userid = obj.get("useraccount").toString();
			}
			conf.setUserid(userid);
			conf.setStatus(obj.get("status")==null?"":obj.get("status").toString());
			list.add(conf);
		}
		return list;
	}
	/**
	 * 获取会议验证码
	 * @author zhangqing
	 * @date 2013-12-18 上午10:36:39
	 * @param token
	 * @param orgid
	 * @param confid
	 * @return
	 */
	public List<PhoneVerifycode> getVerifycodeList(String token,String orgid,String confid,String codetype){
		List<PhoneVerifycode> list = new ArrayList<PhoneVerifycode>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("codetype", codetype));
		JSONArray jsonarr = hc.getJArray("getvcodelist", params);
		int size = jsonarr.size();
		for(int i = 0;i<size;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneVerifycode code = new PhoneVerifycode();
			code.setId(obj.get("id")==null?"":obj.get("id").toString());			
//			String codeString = "";
//			if(obj.get("code")!=null){
//				codeString = obj.get("code").toString();
//				if(codeString.length()==15&&codeString.startsWith("70")){
//					int start = codeString.charAt(2)-48;
//					codeString = codeString.substring(15-start);
//				}
//				if(codeString.length()==15&&codeString.startsWith("71")){
//					int start = Integer.parseInt(codeString.substring(1, 2));
//					codeString = codeString.substring(15-start);
//				}
//			}
			code.setCode(obj.get("code")==null?"":obj.get("code").toString());
			code.setConfid(obj.get("confid")==null?"":obj.get("confid").toString());
			code.setStatus(obj.get("status")==null?"":obj.get("status").toString());
			code.setBegintime(obj.get("begintime")==null?"":obj.get("begintime").toString());
			code.setEndtime(obj.get("endtime")==null?"":obj.get("endtime").toString());
			list.add(code);
		}
		return list;
	}
	/**
	 * 获得会议人员列表
	 * @author zhangqing
	 * @date 2013-12-18 上午11:54:33
	 * @param token
	 * @param confid
	 * @return
	 */
	public List<PhoneUser> getPhoneUserList(String token,String orgid,String confid){
		List<PhoneUser> list = new ArrayList<PhoneUser>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("orgid", orgid));
		JSONArray jsonarr = hc.getJArray("getphoneuserlist", params);
		int size = jsonarr.size();
		for(int i = 0;i<size;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneUser user = new PhoneUser();
			user.setId(obj.get("id")==null?"":obj.get("id").toString());
			user.setConfid(obj.get("confid")==null?"":obj.get("confid").toString());
			user.setFlag(obj.get("flag")==null?"":obj.get("flag").toString());
			user.setJoinflag(obj.get("joinflag")==null?"":obj.get("joinflag").toString());
			user.setMuteflag(obj.get("muteflag")==null?"":obj.get("muteflag").toString());
			user.setName(obj.get("name")==null?"":obj.get("name").toString());
			user.setOnlineflag(obj.get("onlineflag")==null?"":obj.get("onlineflag").toString());
			user.setPhone(obj.get("phone")==null?"":obj.get("phone").toString());
			user.setTime(obj.get("time")==null?"":obj.get("time").toString());
			list.add(user);
		}
		return list;
	}
	/**
	 * 添加电话会议
	 * @author zhangqing
	 * @date 2013-12-20 下午02:08:41
	 * @param token 
	 * @param conf 
	 * @return
	 */
	public String addPhoneconf(String token,PhoneConf conf){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confname", conf.getConfName()));
		params.add(new BasicNameValuePair("orgid", conf.getOrgid()));
		params.add(new BasicNameValuePair("begintime", conf.getBeginTime()));
		params.add(new BasicNameValuePair("endtime", conf.getEndTime()));
		params.add(new BasicNameValuePair("room_password", hc.md5(conf.getRoomPassword())));
		params.add(new BasicNameValuePair("manager_password", hc.md5(conf.getManagerPassword())));
		params.add(new BasicNameValuePair("max_user_count", conf.getMaxUserCount()));
		params.add(new BasicNameValuePair("description", conf.getDescription()));
		params.add(new BasicNameValuePair("vop_conf_id", conf.getVopConfId()));
		//TODO 处理会议类型。用户如果是个人电话会议还需要写入userid
		params.add(new BasicNameValuePair("conftype", conf.getConftype()));
		params.add(new BasicNameValuePair("verifycodevisible", conf.getVerifyCodeVisible()));
		params.add(new BasicNameValuePair("join_number", conf.getJoinNumber()));
		params.add(new BasicNameValuePair("srv_id", conf.getSrvId()));
		params.add(new BasicNameValuePair("conftype", conf.getConftype()));
		String result = "1";
		JSONObject json = hc.getJObject("addphoneconf",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 编辑电话会议
	 * @author zhangqing
	 * @date 2013-12-20 下午03:32:54
	 * @param token
	 * @param conf
	 * @return
	 */
	public String editPhoneconf(String token,PhoneConf conf){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", conf.getId()));
		params.add(new BasicNameValuePair("confname", conf.getConfName()));
		params.add(new BasicNameValuePair("orgid", conf.getOrgid()));
		params.add(new BasicNameValuePair("begintime", conf.getBeginTime()));
		params.add(new BasicNameValuePair("endtime", conf.getEndTime()));
		params.add(new BasicNameValuePair("room_password", conf.getRoomPassword()));
		params.add(new BasicNameValuePair("manager_password", conf.getManagerPassword()));
		params.add(new BasicNameValuePair("max_user_count", conf.getMaxUserCount()));
		params.add(new BasicNameValuePair("description", conf.getDescription()));
		params.add(new BasicNameValuePair("vop_conf_id", conf.getVopConfId()));
		//TODO 处理会议类型。用户如果是个人电话会议还需要写入userid
		params.add(new BasicNameValuePair("conftype", conf.getConftype()));
		params.add(new BasicNameValuePair("verifycodevisible", conf.getVerifyCodeVisible()));
		params.add(new BasicNameValuePair("join_number", conf.getJoinNumber()));
		params.add(new BasicNameValuePair("srv_id", conf.getSrvId()));
		String result = "1";
		JSONObject json = hc.getJObject("editphoneconf",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 锁定电话会议
	 * @author zhangqing
	 * @date 2013-12-20 下午03:32:54
	 * @param token
	 * @param conf
	 * @param lock 1：锁定，0：解除锁定
	 * @return
	 */
	public String lockPhoneconf(String token,String orgid,String confids,String lock){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confids", confids));
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("lock", lock));
		String result = "1";
		JSONObject json = hc.getJObject("lockphoneconf",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 删除电话会议
	 * @author zhangqing
	 * @date 2013-12-20 下午03:32:54
	 * @param token
	 * @param conf
	 * @return
	 */
	public String delPhoneconf(String token,String orgid,String confids){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confids", confids));
		params.add(new BasicNameValuePair("orgid", orgid));
		String result = "1";
		JSONObject json = hc.getJObject("delphoneconf",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 添加验证码
	 * @author zhangqing
	 * @date 2013-12-20 下午03:38:20
	 * @param token
	 * @param code
	 * @return
	 */
	public String addPhoneVerifycode(String token,String orgid,PhoneVerifycode code){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("confid", code.getConfid()));
		params.add(new BasicNameValuePair("begintime", code.getBegintime()));
		params.add(new BasicNameValuePair("endtime", code.getEndtime()));
		params.add(new BasicNameValuePair("type", code.getType()));
		params.add(new BasicNameValuePair("verifycode", code.getCode()));
		String result = "1";
		JSONObject json = hc.getJObject("addphonevcode",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 修改验证码
	 * @param token
	 * @param code
	 * @return
	 */
	public String editPhoneVerifycode(String token,String orgid,PhoneVerifycode code){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("confid", code.getConfid()));
		params.add(new BasicNameValuePair("id", code.getId()));		
		params.add(new BasicNameValuePair("status", code.getStatus()));	
		params.add(new BasicNameValuePair("begintime", code.getBegintime()));
		params.add(new BasicNameValuePair("endtime", code.getEndtime()));
		params.add(new BasicNameValuePair("type", code.getType()));
		params.add(new BasicNameValuePair("verifycode", code.getCode()));
		String result = "1";
		JSONObject json = hc.getJObject("editphonevcode",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 禁用验证码
	 * @author zhangqing
	 * @date 2013-12-20 下午03:38:20
	 * @param token
	 * @param code
	 * @return
	 */
	public String disablePhoneVerifycode(String token,String orgid,String codes,String confid){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("codes", codes));
		String result = "1";
		JSONObject json = hc.getJObject("disablephonevcode",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 删除验证码
	 * @author zhangqing
	 * @date 2013-12-20 下午03:38:20
	 * @param token
	 * @param codes
	 * @return
	 */
	public String delPhoneVerifycode(String token,String orgid,String codes,String confid){ 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("codes", codes));
		params.add(new BasicNameValuePair("orgid", orgid));
		String result = "1";
		JSONObject json = hc.getJObject("delphonevcode",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 添加电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-20 下午03:43:47
	 * @param token
	 * @param user
	 * @return
	 */
	public String addPhoneuser(String token,String orgid,PhoneUser user){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", user.getConfid()));
		params.add(new BasicNameValuePair("phone", user.getPhone()));
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("username", user.getName()));
		String result = "1";
		JSONObject json = hc.getJObject("addphoneuser",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 编辑电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-20 下午03:43:59
	 * @param token
	 * @param user
	 * @return
	 */
	public String editPhoneuser(String token,String orgid,PhoneUser user,String oldphone){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", user.getConfid()));
		params.add(new BasicNameValuePair("newphone", user.getPhone()));
		params.add(new BasicNameValuePair("oldphone", oldphone));
		params.add(new BasicNameValuePair("username", user.getName()));
		String result = "1";
		JSONObject json = hc.getJObject("editphoneuser",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 删除电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-20 下午03:44:16
	 * @param token
	 * @param confid
	 * @param phones
	 * @return
	 */
	public String delPhoneuser(String token,String orgid,String confid,String phones){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("phones", phones));
		String result = "1";
		JSONObject json = hc.getJObject("delphoneuser",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 禁言电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-20 下午03:44:24
	 * @param token
	 * @param confid
	 * @param phones
	 * @param mute 是否禁用，0：禁用，1：启用
	 * @return
	 */
	public String mutePhoneuser(String token,String orgid,String confid,String phones,String mute){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("phones", phones));
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("mute", mute));
		String result = "1";
		JSONObject json = hc.getJObject("mutephoneuser",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 禁言所有电话会议联系人
	 * @author zhangqing
	 * @date 2013-12-20 下午03:44:24
	 * @param token
	 * @param confid
	 * @param mute 是否禁用，0：禁用，1：启用
	 * @return
	 */
	public String muteAllPhoneuser(String token,String orgid,String confid,String mute){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("mute", mute));
		params.add(new BasicNameValuePair("muteall", "0"));
		params.add(new BasicNameValuePair("orgid", orgid));
		String result = "1";
		JSONObject json = hc.getJObject("mutephoneuser",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 呼叫用户或挂断
	 * @author zhangqing
	 * @date 2013-12-23 上午11:13:19
	 * @param token
	 * @param confid
	 * @param phones
	 * @param call 呼叫或挂断。0：呼叫，1：挂断
	 * @return
	 */
	public String callPhoneuser(String token,String orgid,String confid,String phones,String call){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("phones", phones));
		params.add(new BasicNameValuePair("call", call));
		params.add(new BasicNameValuePair("orgid", orgid));
		String result = "1";
		JSONObject json = hc.getJObject("callphoneuser",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 发送短消息
	 * @author zhangqing
	 * @date 2013-12-25 下午03:21:08
	 * @param token
	 * @param confid
	 * @param phones
	 * @param msg
	 * @return
	 */
	public String sendTextMessage(String token,String orgid,String confid,String phones,String msg){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("phones", phones));
		params.add(new BasicNameValuePair("msg", msg));
		params.add(new BasicNameValuePair("orgid", orgid));
		String result = "1";
		JSONObject json = hc.getJObject("sendmsg",params);
		if(json!=null){
			result = json.get("msg").toString();
		}
		return result;
	}
	/**
	 * 获得企业类型
	 * @author zhangqing
	 * @date 2014-1-2 下午02:12:56
	 * @param token
	 * @param orgid
	 * @return
	 */
	public int getOrgType(String token,String orgid){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("orgid", orgid));
		int result = 0;
		JSONObject json = hc.getJObject("orgtype", params);
		if(json==null||json.get("orgtype")==null){
			result = -3;
		}else{
			result = getIntString(json.get("orgtype").toString());
		}
		return result;
	}
	/**
	 * 通过会议id获得单个会议信息
	 * @author zhangqing
	 * @date 2014-2-11 下午01:35:02
	 * @param token 反问令牌
	 * @param confid 会议id
	 * @return
	 */
	public PhoneConf getPhoneConfById(String token,String orgid,String confid){
		PhoneConf conf = new PhoneConf();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("confid", confid));
		params.add(new BasicNameValuePair("orgid", orgid));
		JSONObject json = hc.getJObject("infophoneconf",params);
		if(json!=null){
			conf.setId(json.get("confid")==null?null:json.get("confid").toString());
			conf.setConfName(json.get("confname")==null?null:json.get("confname").toString());
			conf.setBeginTime(json.get("begintime")==null?null:json.get("begintime").toString());
			conf.setDescription(json.get("description")==null?null:json.get("description").toString());
			conf.setEndTime(json.get("endtime")==null?null:json.get("endtime").toString());
			conf.setJoinNumber(json.get("join_number")==null?null:json.get("join_number").toString());
			conf.setManagerPassword(json.get("manager_password")==null?null:json.get("manager_password").toString());
			conf.setRoomPassword(json.get("room_password")==null?null:json.get("room_password").toString());
//			conf.setManagerPassword("password");
//			conf.setRoomPassword("password");
			conf.setMaxUserCount(json.get("max_user_count")==null?null:json.get("max_user_count").toString());
			conf.setOrgid(json.get("orgid")==null?null:json.get("orgid").toString());
			conf.setSrvId(json.get("srv_id")==null?null:json.get("srv_id").toString());
			conf.setVerifyCodeVisible(json.get("verifycodevisible")==null?null:json.get("verifycodevisible").toString());
			conf.setVopConfId(json.get("vop_conf_id")==null?null:json.get("vop_conf_id").toString());
			conf.setUserid(json.get("userid")==null?null:json.get("userid").toString());
		}
		return conf;
	}
	
	/**
	 * string转int。避免异常
	 * @param str
	 * @return
	 */
	private int getIntString(String str){
		if(str==null){
			return 0;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return 0;
        }else{
        	return Integer.parseInt(str);
        }
	}
}
