package com.seegle.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.beans.PhoneConsume;
import com.seegle.beans.PhoneConsumeConf;
import com.seegle.beans.PhoneConsumeCount;
import com.seegle.beans.PhoneConsumeOrgid;
import com.seegle.beans.PhoneConsumePhone;
import com.seegle.util.PropUtil;

public class PhoneConsumeOper {
	private String orgid;
	private HttpClient hc;
	public PhoneConsumeOper(){
		
	}
	public PhoneConsumeOper(String orgid){
		this.orgid = orgid;
		hc = new HttpClient(PropUtil.getInstance().getValue("apiUrl"), orgid);
	}

	/**
	 * 电话会议记录详情
	 */
	public List<PhoneConsume> getPhoneconsume(int begin,int size,String token,String loginFlag,String confType,String area,String confid,String phone,int orgid,String btime,String etime,String orderName,String orderType){
		List<PhoneConsume> list = new ArrayList<PhoneConsume>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("start", begin+""));
		params.add(new BasicNameValuePair("pagesize", size+""));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("conf", confid));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		params.add(new BasicNameValuePair("orderName", orderName));
		params.add(new BasicNameValuePair("orderType", orderType));
		JSONArray jsonarr = hc.getJArray("phonedetaillist", params);
		int jarraySize = jsonarr==null?0:jsonarr.size();
		for(int i=0;i<jarraySize;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneConsume consume = new PhoneConsume();
			consume.setConfid(Integer.parseInt(obj.get("confid")==null?"0":obj.get("confid").toString()));
			consume.setArea(obj.get("area")==null?"":obj.get("area").toString());
			consume.setBtime(obj.get("btime")==null?"":obj.get("btime").toString());
			consume.setBusinessId(obj.get("businessId")==null?"":obj.get("businessId").toString());
			consume.setConfname(obj.get("confname")==null?"":obj.get("confname").toString());
			consume.setConftype(Integer.parseInt(obj.get("conftype")==null?"-1":obj.get("conftype").toString()));
			consume.setConsumeCount(Integer.parseInt(obj.get("consumeCount")==null?"0":obj.get("consumeCount").toString()));
			consume.setConsumeAmount(Integer.parseInt(obj.get("consumeAmount")==null?"0":obj.get("consumeAmount").toString()));
			consume.setId(Integer.parseInt(obj.get("id")==null?"0":obj.get("id").toString()));
			consume.setEtime(obj.get("etime")==null?"":obj.get("etime").toString());
			consume.setJoinnumber(obj.get("joinnumber")==null?"":obj.get("joinnumber").toString());
			consume.setLoginFlag(Integer.parseInt(obj.get("loginFlag")==null?"-1":obj.get("loginFlag").toString()));
			consume.setOrgid(Integer.parseInt(obj.get("orgid")==null?"0":obj.get("orgid").toString()));
			consume.setNickname(obj.get("nickname")==null?"":obj.get("nickname").toString());
			consume.setPhoneNumber(obj.get("phoneNumber")==null?"":obj.get("phoneNumber").toString());
			consume.setFormatCount(obj.get("formatCount")==null?"":obj.get("formatCount").toString());
			consume.setChargingtime(Integer.parseInt(obj.get("chargingtime")==null?"0":obj.get("chargingtime").toString()));
			list.add(consume);
		}
		return list;
	}
	/**
	 * 会议记录详情汇总
	 */
	public PhoneConsumeCount getCount(String token,String loginFlag,String confType,String area,String confid,String phone,int orgid,String btime,String etime){
		PhoneConsumeCount count = new PhoneConsumeCount();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("conf", confid));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		JSONObject json = hc.getJObject("phonedetailcount",params);
		if(json!=null){
			count.setCountData(Integer.parseInt(json.get("countData")==null?"0":json.get("countData").toString()));
			count.setCountLogin(Integer.parseInt(json.get("countLogin")==null?"0":json.get("countLogin").toString()));
			count.setSumCost(Integer.parseInt(json.get("sumCost")==null?"0":json.get("sumCost").toString()));
			count.setSumTime(Integer.parseInt(json.get("sumTime")==null?"0":json.get("sumTime").toString()));
			count.setSumFormatCount(json.get("sumFormatCount")==null?"":json.get("sumFormatCount").toString());
			count.setSumChargingtime(Integer.parseInt(json.get("sumChargingtime")==null?"0":json.get("sumChargingtime").toString()));
		}
		return count;
	}
	/**
	 * 按会议id分组查询
	 */
	public List<PhoneConsumeConf> getPhoneconsumeByConf(int begin,int size,String token,String loginFlag,String confType,String area,String conf,int orgid,String btime,String etime,String orderName,String orderType){
		List<PhoneConsumeConf> list = new ArrayList<PhoneConsumeConf>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("start", begin+""));
		params.add(new BasicNameValuePair("pagesize", size+""));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("conf", conf));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		params.add(new BasicNameValuePair("orderName", orderName));
		params.add(new BasicNameValuePair("orderType", orderType));
		JSONArray jsonarr = hc.getJArray("listbyconf", params);
		int jarraySize = jsonarr==null?0:jsonarr.size();
		for(int i=0;i<jarraySize;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneConsumeConf consume = new PhoneConsumeConf();
			consume.setConfid(Integer.parseInt(obj.get("confid")==null?"0":obj.get("confid").toString()));
			consume.setConfname(obj.get("confname")==null?"0":obj.get("confname").toString());
			consume.setCountLogin(Integer.parseInt(obj.get("countLogin")==null?"0":obj.get("countLogin").toString()));
			consume.setSumCost(Integer.parseInt(obj.get("sumCost")==null?"0":obj.get("sumCost").toString()));
			consume.setSumTime(Integer.parseInt(obj.get("sumTime")==null?"0":obj.get("sumTime").toString()));
			consume.setSumFormatCount(obj.get("sumFormatCount")==null?"":obj.get("sumFormatCount").toString());
			consume.setSumChargingtime(Integer.parseInt(obj.get("sumChargingtime")==null?"0":obj.get("sumChargingtime").toString()));
			list.add(consume);
		}
		return list;
	}
	/**
	 * 按会议id分组查询统计
	 */
	public PhoneConsumeCount getCountByConf(String token,String loginFlag,String confType,String area,String conf,int orgid,String btime,String etime){
		PhoneConsumeCount count = new PhoneConsumeCount();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("conf", conf));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		JSONObject json = hc.getJObject("phonecountbyconf",params);
		if(json!=null){
			count.setCountData(Integer.parseInt(json.get("countData")==null?"0":json.get("countData").toString()));
			count.setCountLogin(Integer.parseInt(json.get("countLogin")==null?"0":json.get("countLogin").toString()));
			count.setSumCost(Integer.parseInt(json.get("sumCost")==null?"0":json.get("sumCost").toString()));
			count.setSumTime(Integer.parseInt(json.get("sumTime")==null?"0":json.get("sumTime").toString()));
			count.setSumFormatCount(json.get("sumFormatCount")==null?"":json.get("sumFormatCount").toString());
			count.setSumChargingtime(Integer.parseInt(json.get("sumChargingtime")==null?"0":json.get("sumChargingtime").toString()));
		}
		return count;
	}
	/**
	 * 按电话号码分组查询
	 */
	public List<PhoneConsumePhone> getPhoneconsumeByPhone(int begin,int size,String token,String loginFlag,String confType,String area,String phone,int orgid,String btime,String etime,String orderName,String orderType){
		List<PhoneConsumePhone> list = new ArrayList<PhoneConsumePhone>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("start", begin+""));
		params.add(new BasicNameValuePair("pagesize", size+""));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		params.add(new BasicNameValuePair("orderName", orderName));
		params.add(new BasicNameValuePair("orderType", orderType));
		JSONArray jsonarr = hc.getJArray("listbyphone", params);
		int jarraySize = jsonarr==null?0:jsonarr.size();
		for(int i=0;i<jarraySize;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneConsumePhone consume = new PhoneConsumePhone();
			consume.setPhoneNumber(obj.get("phoneNumber")==null?"0":obj.get("phoneNumber").toString());
			consume.setNickname(obj.get("nickname")==null?"0":obj.get("nickname").toString());
			consume.setCountLogin(Integer.parseInt(obj.get("countLogin")==null?"0":obj.get("countLogin").toString()));
			consume.setSumCost(Integer.parseInt(obj.get("sumCost")==null?"0":obj.get("sumCost").toString()));
			consume.setSumTime(Integer.parseInt(obj.get("sumTime")==null?"0":obj.get("sumTime").toString()));
			consume.setSumFormatCount(obj.get("sumFormatCount")==null?"":obj.get("sumFormatCount").toString());
			consume.setSumChargingtime(Integer.parseInt(obj.get("sumChargingtime")==null?"0":obj.get("sumChargingtime").toString()));
			list.add(consume);
		}
		return list;
	}
	/**
	 * 按电话分组查询汇总
	 */
	public PhoneConsumeCount getCountByPhone(String token,String loginFlag,String confType,String area,String phone,int orgid,String btime,String etime){
		PhoneConsumeCount count = new PhoneConsumeCount();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("phone", phone));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		JSONObject json = hc.getJObject("phonecountbyphone",params);
		if(json!=null){
			count.setCountData(Integer.parseInt(json.get("countData")==null?"0":json.get("countData").toString()));
			count.setCountLogin(Integer.parseInt(json.get("countLogin")==null?"0":json.get("countLogin").toString()));
			count.setSumCost(Integer.parseInt(json.get("sumCost")==null?"0":json.get("sumCost").toString()));
			count.setSumTime(Integer.parseInt(json.get("sumTime")==null?"0":json.get("sumTime").toString()));
			count.setSumFormatCount(json.get("sumFormatCount")==null?"":json.get("sumFormatCount").toString());
			count.setSumChargingtime(Integer.parseInt(json.get("sumChargingtime")==null?"0":json.get("sumChargingtime").toString()));
		}
		return count;
	}
	/**
	 * 
	 * @author zhangqing
	 * @date 2014-1-24 上午11:34:04
	 * @param begin
	 * @param size
	 * @param token
	 * @param loginFlag
	 * @param confType
	 * @param area
	 * @param orgid
	 * @param year
	 * @param month
	 * @param orderName
	 * @param orderType
	 * @return
	 */
	public List<PhoneConsumeOrgid> getPhoneconsumeByOrg(int begin,int size,String token,String loginFlag,String confType,String area,String orgid,String btime,String etime,String orderName,String orderType){
		List<PhoneConsumeOrgid> list = new ArrayList<PhoneConsumeOrgid>();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("start", begin+""));
		params.add(new BasicNameValuePair("pagesize", size+""));
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		params.add(new BasicNameValuePair("orderName", orderName));
		params.add(new BasicNameValuePair("orderType", orderType));
		JSONArray jsonarr = hc.getJArray("listbyorg", params);
		int jarraySize = jsonarr==null?0:jsonarr.size();
		for(int i=0;i<jarraySize;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			PhoneConsumeOrgid consume = new PhoneConsumeOrgid();
			consume.setCountConf(Integer.parseInt(obj.get("countConf")==null?"0":obj.get("countConf").toString()));
			consume.setCountPhonenumber(Integer.parseInt(obj.get("countPhonenumber")==null?"0":obj.get("countPhonenumber").toString()));
			consume.setOrgid(Integer.parseInt(obj.get("orgid")==null?"0":obj.get("orgid").toString()));
			consume.setCountLogin(Integer.parseInt(obj.get("countLogin")==null?"0":obj.get("countLogin").toString()));
			consume.setSumCost(Integer.parseInt(obj.get("sumCost")==null?"0":obj.get("sumCost").toString()));
			consume.setSumTime(Integer.parseInt(obj.get("sumTime")==null?"0":obj.get("sumTime").toString()));
			consume.setSumFormatCount(obj.get("sumFormatCount")==null?"":obj.get("sumFormatCount").toString());
			consume.setSumChargingtime(Integer.parseInt(obj.get("sumChargingtime")==null?"0":obj.get("sumChargingtime").toString()));
			list.add(consume);
		}
		return list;
	}
	/**
	 * 
	 * @author zhangqing
	 * @date 2014-1-24 上午11:34:10
	 * @param token
	 * @param loginFlag
	 * @param confType
	 * @param area
	 * @param orgid
	 * @param year
	 * @param month
	 * @return
	 */
	public PhoneConsumeCount getCountByOrg(String token,String loginFlag,String confType,String area,String orgid,String btime,String etime){
		PhoneConsumeCount count = new PhoneConsumeCount();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("loginFlag", loginFlag));
		params.add(new BasicNameValuePair("confType", confType));
		params.add(new BasicNameValuePair("area", area));
		params.add(new BasicNameValuePair("orgid", orgid+""));
		params.add(new BasicNameValuePair("btime", btime));
		params.add(new BasicNameValuePair("etime", etime));
		JSONObject json = hc.getJObject("phonecountbyorg",params);
		if(json!=null){
			count.setCountData(Integer.parseInt(json.get("countData")==null?"0":json.get("countData").toString()));
			count.setCountLogin(Integer.parseInt(json.get("countLogin")==null?"0":json.get("countLogin").toString()));
			count.setSumCost(Integer.parseInt(json.get("sumCost")==null?"0":json.get("sumCost").toString()));
			count.setSumTime(Integer.parseInt(json.get("sumTime")==null?"0":json.get("sumTime").toString()));
			count.setSumFormatCount(json.get("sumFormatCount")==null?"":json.get("sumFormatCount").toString());
			count.setSumChargingtime(Integer.parseInt(json.get("sumChargingtime")==null?"0":json.get("sumChargingtime").toString()));
		}
		return count;
	}
	
}
