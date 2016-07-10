package com.seegle.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.seegle.util.SeegleLog;

/**
 * httpclient操作类，用于请求opm4j
 * @author zhangqing
 * @date 2013-5-28 下午02:46:34
 */
public class HttpClient {
	/**最终api的url地址*/
	private String api_url;
	/**url地址指到opm4j服务器*/
	private String url;
	/**企业ID*/
	private String orgid;
	/**未认证前操作*/
	private boolean beforLogin = false;
	/**是否为用户在线日志*/
	private boolean isOnline = false;
	/**未登录前标识*/
	private final String LOGIN_BEFOR = "beforLogin";
	public String getApi_url() {
		return api_url;
	}

	public void setApi_url(String api_url) {
		this.api_url = api_url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpClient(String url,String orgid) {
		this.url = url;
		this.orgid = (orgid==null||orgid.equals(""))?"defual":orgid;
	}

	/**
	 * MD5 加密函数
	 * @author zhangqing
	 * @date 2013-5-28 下午02:50:01
	 * @param str 需要加密的串
	 * @return
	 */
	public static String md5(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
	
	//TODO
	public int getLoginRS(String a,List params) {
		int rs = -5;
		String parm = URLEncodedUtils.format(params, "UTF-8");
		if("login".equals(a)){
			this.api_url = this.url + "/LoginServlet?callback&";
		} 
		else if("confsum".equals(a)){
			this.api_url = this.url + "/apis/conf/sum?callback&";
		}
		SeegleLog.getInstance().getLog(this.getClass(), orgid).debug(this.api_url+parm);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(this.api_url+parm);
			HttpResponse response = null;
			try {
				response = httpclient.execute(httpget);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String jsonstr = EntityUtils.toString(entity,"UTF-8");
				SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("@@@@@@@@@"+jsonstr);
				rs = Integer.parseInt(jsonstr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}		
		return rs;
	}
	
	/**
	 * 获得opm4j返回到额json对象
	 * @author zhangqing
	 * @date 2013-5-28 下午02:51:00
	 * @param a 请求标识。如token：获得登录key，listone：获取单个会议室信息
	 * @param params 请求参数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject getJObject(String a, List params) {
		JSONObject jsonObject = null;
		String parm = URLEncodedUtils.format(params, "UTF-8");
		if ("token".equals(a)) {
			this.api_url = this.url + "/apis/token?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("用户认证:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("listone".equals(a)){
			this.api_url = this.url + "/apis/conf/edit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取当个会议信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("create".equals(a)){
			this.api_url = this.url + "/apis/conf/add?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加会议:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("update".equals(a)){
			this.api_url = this.url + "/apis/conf/edit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("修改会议:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("remove".equals(a)){
			this.api_url = this.url + "/apis/conf/del?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除会议:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("login".equals(a)){
			this.api_url = this.url + "/apis/conf/login?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取会议登录参数:请求参数："+parm+"，请求地址："+this.api_url);
		}	
		else if("adduser".equals(a)){
			this.api_url = this.url + "/apis/user/add?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加用户:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("edituser".equals(a)){
			this.api_url = this.url + "/apis/user/edit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("编辑用户:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("deluser".equals(a)){
			this.api_url = this.url + "/apis/user/delete?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除用户:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("confuseradd".equals(a)){
			this.api_url = this.url + "/apis/conf/confuseradd?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("通过ID添加会议室人员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("confuserdel".equals(a)){
			this.api_url = this.url + "/apis/conf/confuserdel?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("通过ID删除会议室人员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		//TODO
		else if("currentuser".equals(a)){
			this.api_url = this.url + "/apis/user/currentuser?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取当前用户信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("currentusermod".equals(a)){
			this.api_url = this.url + "/apis/user/currentuserMod?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("修改当前用户信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		//TODO
		else if("vopusermod".equals(a)){
			this.api_url = this.url + "/apis/user/vopmod?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取带协同用户信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("logout".equals(a)){
			this.api_url = this.url + "/apis/user/logout?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("用户登出操作:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("companyreg".equals(a)){
			this.api_url = this.url + "/apis/company/reg?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("企业注册:请求参数："+parm+"，请求地址："+this.api_url);
		}
		//TODO
		else if("changeadmin".equals(a)){
			this.api_url = this.url + "/apis/company/mod?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("更改企业管理员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("confuseraddbyname".equals(a)){
			this.api_url = this.url + "/apis/conf/confuseraddbyname?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("通过用户名添加会议室人员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("confuserdelbyname".equals(a)){
			this.api_url = this.url + "/apis/conf/confuserdelbyname?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("通过用户名删除会议室人员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("orgname".equals(a)){
			this.api_url = this.url + "/apis/company/getname?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获得企业名称:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("orgnamemod".equals(a)){
			this.api_url = this.url + "/apis/company/editname?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("更改企业名称:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("confsum".equals(a)){
			this.api_url = this.url + "/apis/conf/sum?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获得会议室总数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("orgtype".equals(a)){ //获取企业类型
			this.api_url = this.url + "/apis/company/gettype?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), LOGIN_BEFOR).info("获得企业类型:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("usersum".equals(a)){
			this.api_url = this.url + "/apis/user/sum?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获得用户总数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("settop".equals(a)){
			this.api_url = this.url + "/apis/conf/settop?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("设置会议置顶:请求参数："+parm+"，请求地址："+this.api_url);
		}	
		else if("concurrent".equals(a)){
			this.api_url = this.url + "/apis/conf/concurrent?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取最大并发数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		//===电话会议接口==
		else if("countphoneconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconf/count?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("电话会总记录数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("addphoneconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconf/add?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加电话会议:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("editphoneconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconf/edit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("编辑电话会议:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("infophoneconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconf/info?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取单个电话会议洗洗脑："+parm+"，请求地址："+this.api_url);
		}
		else if("delphoneconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconf/del?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除电话会议:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("lockphoneconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconf/lock?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("锁定会议室:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("addphonevcode".equals(a)){
			this.api_url = this.url + "/apis/phoneverifycode/add?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加验证码:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("editphonevcode".equals(a)){
			this.api_url = this.url + "/apis/phoneverifycode/edit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("修改验证码:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("disablephonevcode".equals(a)){
			this.api_url = this.url + "/apis/phoneverifycode/disable?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("禁用验证码:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("delphonevcode".equals(a)){
			this.api_url = this.url + "/apis/phoneverifycode/del?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除验证码:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("callphoneuser".equals(a)){
			this.api_url = this.url + "/apis/phoneuser/call?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("呼叫会议联系人:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("addphoneuser".equals(a)){
			this.api_url = this.url + "/apis/phoneuser/add?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加会议联系人:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("editphoneuser".equals(a)){
			this.api_url = this.url + "/apis/phoneuser/edit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("编辑会议联系人:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("delphoneuser".equals(a)){
			this.api_url = this.url + "/apis/phoneuser/del?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除会议联系人:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("mutephoneuser".equals(a)){
			this.api_url = this.url + "/apis/phoneuser/mute?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("禁言会议联系人:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("addPhoneAdmin".equals(a)){
			this.api_url = this.url + "/apis/phoneadminuser/add?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加电话会议管理员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("delPhoneAdmin".equals(a)){
			this.api_url = this.url + "/apis/phoneadminuser/del?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除电话会议管理员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("sendmsg".equals(a)){
			this.api_url = this.url + "/apis/phoneuser/sendmsg?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("发送短信:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phonecountbyconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/countbyconf?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("按会议获取通话记录总数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phonecountbyphone".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/countbyphone?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("按电话获取通话记录总数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phonecountbyorg".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/countbyorg?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("按企业获取通话记录总数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phonedetailcount".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/detailcount?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("通话记录详情总数:请求参数："+parm+"，请求地址："+this.api_url);
		}
		//电话计费
		else if("getchargeaccountinfo".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/accountinfo?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取计费账户金额情况:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phoneoperuseredit".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/operuseredit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("计费操作员信息维护:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phoneoperuserdel".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/operuserdel?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除计费操作员:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("payhistoryadd".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/payhistoryadd?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加充值记录:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("accountupdate".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/accountupdate?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("更新企业账户信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("accountadd".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/accountadd?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加企业账户信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("priceadd".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/priceadd?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("添加计费单价信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("pricedel".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/pricedel?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("删除计费单价信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("pay".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/pay?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("充值:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("updateremind".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/remindsumupdate?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("更改企业提醒金额:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("updateoverfulfilsum".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/overfulfilsumupdate?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("更改企业超额额度:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else {
			
		}
		//是否为登陆前验证企业ID
		beforLogin = "orgtype".equals(a)?true:false;
		
		JSONParser parser = new JSONParser();
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(this.api_url+parm);
			HttpResponse response = null;
			try {
				response = httpclient.execute(httpget);
			} catch (ClientProtocolException e) {
				if(beforLogin){
					SeegleLog.getInstance().getLog(this.getClass(), LOGIN_BEFOR).error("调用opm4j出错"+e+"，请求地址："+this.api_url);
				}else{
					SeegleLog.getInstance().getLog(this.getClass(), orgid).error("调用opm4j出错"+e+"，请求地址："+this.api_url);
				}
				e.printStackTrace();
			} catch (IOException e) {
				if(beforLogin){
					SeegleLog.getInstance().getLog(this.getClass(), LOGIN_BEFOR).error("调用opm4j出错"+e+"，请求地址："+this.api_url);
				}else{
					SeegleLog.getInstance().getLog(this.getClass(), orgid).error("调用opm4j出错"+e+"，请求地址："+this.api_url);
				}
				e.printStackTrace();
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String jsonstr = EntityUtils.toString(entity,"UTF-8");
				if(jsonstr.startsWith("(")){
					jsonstr = jsonstr.substring(1, jsonstr.length()-1);
				}
				if(jsonstr.endsWith(")")){
					jsonstr = jsonstr.substring(0, jsonstr.length()-2);
				}
				if(!(jsonstr==null||"".equals(jsonstr))){
					if(beforLogin){
						SeegleLog.getInstance().getLog(this.getClass(), LOGIN_BEFOR).info("返回信息："+jsonstr);
					}else{
						SeegleLog.getInstance().getLog(this.getClass(), orgid).info("返回信息："+jsonstr);
					}
				}
				Object obj = parser.parse(jsonstr);
				jsonObject = (JSONObject) obj;
			}
		} catch (Exception e) {
			if(beforLogin){
				SeegleLog.getInstance().getLog(this.getClass(), LOGIN_BEFOR).error("opm4j返回信息处理出错"+e);
			}else{
				SeegleLog.getInstance().getLog(this.getClass(), orgid).error("opm4j返回信息处理出错"+e);
			}
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonObject;
	}
	
	/**
	 * 获取omp4j返回的JSONArray对象
	 * @author zhangqing
	 * @date 2013-5-28 下午03:40:54
	 * @param a 请求标识。如token：获得登录key，listone：获取单个会议室信息
	 * @param params 请求参数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONArray getJArray(String a, List params) {
		JSONArray jsonarr = null;
		String parm = URLEncodedUtils.format(params, "UTF-8");
		if("listall".equals(a)){
			this.api_url = this.url + "/apis/conf/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取会议列表:请求参数："+parm+"，请求地址："+this.api_url);
		} 
		else if("group".equals(a)){
			this.api_url = this.url + "/apis/conf/group?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取会议集群信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("online".equals(a)){
			this.api_url = this.url + "/apis/conf/online?callback&";
			//请求次数过多不打出
			//SeegleLog.getInstance().getLog(this.getClass(), orgid).info("在线用户列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("dept".equals(a)){
			this.api_url = this.url + "/apis/conf/dept?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("会议统计:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("logconf".equals(a)){
			this.api_url = this.url + "/apis/log/conf/all?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取所有会议日志:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("loguser".equals(a)){
			this.api_url = this.url + "/apis/log/user/all?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取所有用户日志:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("logconfsingle".equals(a)){
			this.api_url = this.url + "/apis/log/conf/single?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取单个会议室日志:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("logusersingle".equals(a)){
			this.api_url = this.url + "/apis/log/user/single?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取单个用户日志:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("userlist".equals(a)){
			this.api_url = this.url + "/apis/user/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取用户列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("edituser".equals(a)){
			this.api_url = this.url + "/apis/user/edit?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("更新用户信息:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("confuserlist".equals(a)){
			this.api_url = this.url + "/apis/conf/confuserlist?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取会议室人员列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("gettoplist".equals(a)){
			this.api_url = this.url + "/apis/conf/toplist?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取置顶会议列表:请求参数："+parm+"，请求地址："+this.api_url);
		}	
		//电话会议接口
		else if("getphonelist".equals(a)){
			this.api_url = this.url + "/apis/phoneconf/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取电话会议列表:请求参数："+parm+"，请求地址："+this.api_url);
		}	
		else if("getvcodelist".equals(a)){
			this.api_url = this.url + "/apis/phoneverifycode/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取电话会议验证码列表:请求参数："+parm+"，请求地址："+this.api_url);
		}	
		else if("getphoneuserlist".equals(a)){
			this.api_url = this.url + "/apis/phoneuser/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取电话会议人员列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("getphoneadminlist".equals(a)){
			this.api_url = this.url + "/apis/phoneadminuser/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取电话会议人员列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("getphonefacilitylist".equals(a)){
			this.api_url = this.url + "/apis/phonefacility/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取电话会议人员列表:请求参数："+parm+"，请求地址："+this.api_url);
		}	
		else if("listbyconf".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/listbyconf?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("按会议获取通话记录列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("listbyphone".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/listbyphone?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("按电话获取通话记录列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("listbyorg".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/listbyorg?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("按企业获取通话记录列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phonedetaillist".equals(a)){
			this.api_url = this.url + "/apis/phoneconsume/detaillist?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("通话记录详情列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("getpayhistory".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/payhistory?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取计费充值记录:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("serveruser".equals(a)){
			this.api_url = this.url + "/apis/phone/serveruser?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取客服列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("phoneoperuserlist".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/operuserlist?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取操作员列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("reminduserlist".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/reminduserlist?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取企业提醒人列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("pricelist".equals(a)){
			this.api_url = this.url + "/apis/phonecharge/pricelist?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取计费单价列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else if("orglist".equals(a)){
			this.api_url = this.url + "/apis/org/list?callback&";
			SeegleLog.getInstance().getLog(this.getClass(), orgid).info("获取企业列表:请求参数："+parm+"，请求地址："+this.api_url);
		}
		else {
			
		}
		//是否为在线日志
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(this.api_url+parm);
			HttpResponse response = null;
			try {
				response = httpclient.execute(httpget);
			} catch (ClientProtocolException e) {
				SeegleLog.getInstance().getLog(this.getClass(), orgid).error("调用opm4j出错"+e+"，请求地址："+this.api_url);
				e.printStackTrace();
			} catch (IOException e) {
				SeegleLog.getInstance().getLog(this.getClass(), orgid).error("调用opm4j出错"+e+"，请求地址："+this.api_url);
				e.printStackTrace();
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String jsonstr = EntityUtils.toString(entity,"UTF-8");
				if(jsonstr.startsWith("(")){
					jsonstr = jsonstr.substring(1, jsonstr.length()-1);
				}
				if(jsonstr.endsWith(")")){
					jsonstr = jsonstr.substring(0, jsonstr.length()-2);
				}
				if(!(jsonstr==null||"".equals(jsonstr))){
					if("online".equals(a)){//去掉在线日志
					}else if("serveruser".equals(a)){//去掉获取客户列表日志
					}else if("getphoneadminlist".equals(a)){//去掉获取客户列表日志
					}else{
						SeegleLog.getInstance().getLog(this.getClass(), orgid).info("返回信息："+jsonstr);
					}
				}
				Object obj=JSONValue.parse(jsonstr);
				jsonarr = (JSONArray)obj;
			}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("opm4j返回信息处理出错"+e);
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return jsonarr;
	}
	
	public String getString(String a, List params) {
		String rs="";
		String parm = URLEncodedUtils.format(params, "UTF-8");
		if("confadminlist".equals(a)){
			this.api_url = this.url + "/apis/conf/confadminlist?callback&";
		}
		else if("confcommonlist".equals(a)){
			this.api_url = this.url + "/apis/conf/confcommonlist?callback&";
		}
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(this.api_url+parm);
			HttpResponse response = null;
			try {
				response = httpclient.execute(httpget);
			} catch (ClientProtocolException e) {
				SeegleLog.getInstance().getLog(this.getClass(), orgid).error(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				SeegleLog.getInstance().getLog(this.getClass(), orgid).error(e.getMessage());
				e.printStackTrace();
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String jsonstr = EntityUtils.toString(entity,"UTF-8");
				if(jsonstr.startsWith("(")){
					jsonstr = jsonstr.substring(1, jsonstr.length()-1);
				}
				if(jsonstr.endsWith(")")){
					jsonstr = jsonstr.substring(0, jsonstr.length()-2);
				}
				SeegleLog.getInstance().getLog(this.getClass(), orgid).debug(jsonstr);
				rs = jsonstr;
			}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("opm4j返回信息处理出错"+e);
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}		
		return rs;
	}
	
}
