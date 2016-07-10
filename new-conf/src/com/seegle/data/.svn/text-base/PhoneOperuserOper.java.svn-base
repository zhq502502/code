package com.seegle.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.beans.PhoneOperuser;
import com.seegle.util.PropUtil;

public class PhoneOperuserOper {
	private HttpClient hc;
	public PhoneOperuserOper(String orgid){
		hc = new HttpClient(PropUtil.getInstance().getValue("apiUrl"), orgid);
	}
	public List<PhoneOperuser> getPhoneOperuserList(int begin,int size,String token){
		List<PhoneOperuser> list = new ArrayList<PhoneOperuser>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("start", begin+""));
		params.add(new BasicNameValuePair("count", size+""));
		params.add(new BasicNameValuePair("accessKey", token));
		JSONArray jsonarr = hc.getJArray("phoneoperuserlist", params);
		int jarraySize = jsonarr==null?0:jsonarr.size();
		for(int i=0;i<jarraySize;i++){
			PhoneOperuser user = new PhoneOperuser();
			JSONObject obj = (JSONObject)jsonarr.get(i);
			user.setAccount(Integer.parseInt(obj.get("account").toString()));
			user.setAlias(obj.get("alias").toString());
			user.setCount(Integer.parseInt(obj.get("count").toString()));
			user.setEmail(obj.get("email").toString());
			user.setOrgid(Integer.parseInt(obj.get("orgid").toString()));
			user.setParam(obj.get("param").toString());
			user.setPhone(obj.get("phone").toString());
			user.setType(Integer.parseInt(obj.get("type").toString()));
			list.add(user);
		}
		return list;
	}
	
	public boolean save(String token,String account,String orgid,String phone,String email,String alias,String type){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("account",account ));
		params.add(new BasicNameValuePair("orgid",orgid ));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("type",type ));
		params.add(new BasicNameValuePair("alias",alias ));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("method", "add"));
		JSONObject jobj = hc.getJObject("phoneoperuseredit", params);
		if(jobj!=null&&jobj.get("msg").equals("0")){
			return true;
		}
		return false;
	}
	public boolean update(String token,String account,String orgid,String phone,String email,String alias,String type){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("account",account ));
		params.add(new BasicNameValuePair("orgid",orgid ));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("type",type ));
		params.add(new BasicNameValuePair("alias",alias ));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("method", "set"));
		JSONObject jobj = hc.getJObject("phoneoperuseredit", params);
		if(jobj!=null&&jobj.get("msg").equals("0")){
			return true;
		}
		return false;
	}
	public Object getOne(String token,String account,String orgid,boolean json){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("account",account ));
		params.add(new BasicNameValuePair("orgid",orgid ));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("method", "get"));
		JSONObject jobj = hc.getJObject("phoneoperuseredit", params);
		if(json){
			return jobj;
		}
		if(jobj!=null&&jobj.get("msg").equals("0")){
			PhoneOperuser user = new PhoneOperuser();
			user.setAccount(Integer.parseInt(jobj.get("account").toString()));
			user.setAlias(jobj.get("alias").toString());
			user.setEmail(jobj.get("email").toString());
			user.setOrgid(Integer.parseInt(jobj.get("alias").toString()));
			user.setPhone(jobj.get("phone").toString());
			user.setParam(jobj.get("param").toString());
			user.setType(Integer.parseInt(jobj.get("type").toString()));
			return user;
		}
		return null;
	}
	public Object search(String token,String account,String orgid){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("useraccount",account ));
		params.add(new BasicNameValuePair("orgid",orgid ));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("method", "get"));
		JSONArray jsonarr = hc.getJArray("userlist", params);
		int jarraySize = jsonarr==null?0:jsonarr.size();
		if(jarraySize>0){
			if(((JSONObject)jsonarr.get(0)).get("useraccount").equals(account)){
				return jsonarr.get(0);
			}
		}
		return new JSONObject();
	}
	public boolean del(String token,String accounts,String orgid){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accounts",accounts ));
		params.add(new BasicNameValuePair("orgid",orgid ));
		params.add(new BasicNameValuePair("accessKey", token));
		JSONObject jobj = hc.getJObject("phoneoperuserdel", params);
		if(jobj!=null&&jobj.get("msg").equals("0")){
			return true;
		}
		return false;
	}
	public List<PhoneOperuser> getRemindUserlist(String token,String orgid){
		List<PhoneOperuser> list = new ArrayList<PhoneOperuser>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("orgid", orgid));
		params.add(new BasicNameValuePair("accessKey", token));
		JSONArray jsonarr = hc.getJArray("reminduserlist", params);
		int jarraySize = jsonarr==null?0:jsonarr.size();
		for(int i=0;i<jarraySize;i++){
			PhoneOperuser user = new PhoneOperuser();
			JSONObject obj = (JSONObject)jsonarr.get(i);
			user.setAccount(Integer.parseInt(obj.get("account").toString()));
			user.setAlias(obj.get("alias").toString());
			user.setCount(Integer.parseInt(obj.get("count").toString()));
			user.setEmail(obj.get("email").toString());
			user.setOrgid(Integer.parseInt(obj.get("orgid").toString()));
			user.setParam(obj.get("param").toString());
			user.setPhone(obj.get("phone").toString());
			user.setType(Integer.parseInt(obj.get("type").toString()));
			list.add(user);
		}
		return list;
	}
}
