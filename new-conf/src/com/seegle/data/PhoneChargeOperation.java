package com.seegle.data;

import com.seegle.form.AccountSumForm;
import com.seegle.form.PayHistoryForm;
import com.seegle.form.PriceinfoForm;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PhoneChargeOperation {
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private String orgid;
	HttpClient hc ;
	private PhoneChargeOperation(){
		hc = new HttpClient(url,orgid);
	}
	public PhoneChargeOperation(String orgid){
		this.orgid = orgid;
		hc = new HttpClient(url,orgid);
	}
    
    /**
     *获取企业账户金额信息
    **/
    public AccountSumForm getAccountinfo(String token,String orgid) throws ParseException{
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("orgid", orgid)); 
		JSONObject json = hc.getJObject("getchargeaccountinfo", param);	
		AccountSumForm asf = new AccountSumForm();

		if(json!=null){
			if(json.size()>0){		
				asf.setOrgid(json.get("orgid")==null?"":json.get("orgid").toString());
				//转换格式，把单位从分转换为元
				DecimalFormat sumd=new DecimalFormat("0.00");
				String paymoney = json.get("paymoney")==null?"0":json.get("paymoney").toString();
				String realmoney = json.get("realmoney")==null?"0":json.get("realmoney").toString();
				String lessenmoney = json.get("lessenmoney")==null?"0":json.get("lessenmoney").toString();
				String remainingsum = json.get("remainingsum")==null?"0":json.get("remainingsum").toString();
				String remindsum = json.get("remindsum")==null?"0":json.get("remindsum").toString();
				String reminded = json.get("reminded")==null?"0":json.get("reminded").toString();
				String tactics = json.get("tactics")==null?"0":json.get("tactics").toString();
				String nexttactics = json.get("nexttactics")==null?"0":json.get("nexttactics").toString();
				String overfulfilsum = json.get("overfulfilsum")==null?"0":json.get("overfulfilsum").toString();
					
				asf.setPaymoney(sumd.format((double)Long.parseLong(paymoney)/100));
				asf.setRealmoney(sumd.format((double)Long.parseLong(realmoney)/100));
				asf.setLessenmoney(sumd.format((double)Long.parseLong(lessenmoney)/100));
				asf.setRemainingsum(sumd.format((double)Long.parseLong(remainingsum)/100));
				asf.setRemindsum(sumd.format((double)Long.parseLong(remindsum)/100));
				asf.setOverfulfilsum(sumd.format((double)Long.parseLong(overfulfilsum)/100));
				asf.setReminded(reminded);
				asf.setTactics(tactics);
				asf.setNexttactics(nexttactics);
				asf.setParam(json.get("param")==null?"":json.get("param").toString());
			}
		}                       
        return asf;
    }
    
    /**
     *获取充值记录信息记录数
    **/
    public int getPayHistoryCount(String token,String sorgid, String begintime,String endtime,String recordType){
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token));
		param.add(new BasicNameValuePair("orgid", sorgid)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", "0")); 
		param.add(new BasicNameValuePair("offset", "1"));
		param.add(new BasicNameValuePair("recordType", recordType==null||recordType.equals("")?"0":recordType)); 
		JSONArray jsonarr = hc.getJArray("getpayhistory", param);	
		int count = 0;
		if(jsonarr!=null){
			if(jsonarr.size()>0){
				for (int i=0; i<jsonarr.size(); ++i) {					
					JSONObject json = (JSONObject)jsonarr.get(i);
					count = Integer.parseInt(json.get("count").toString());			    				    						 
				}				
			}
		}                       
        return count;
  	}

    
    /**
     *获取充值记录信息列表
    **/
    @SuppressWarnings("unchecked")
	public ArrayList<PayHistoryForm> getPayHistory(String token,String orgid,String begintime,String endtime,int firstIndex, int showNumber,String recordType) throws ParseException{
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("orgid", orgid)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", firstIndex+"")); 
		param.add(new BasicNameValuePair("offset", showNumber+""));
		param.add(new BasicNameValuePair("recordType", recordType==null||recordType.equals("")?"0":recordType)); 
		JSONArray jsonarr = hc.getJArray("getpayhistory", param);	
		ArrayList<PayHistoryForm> phList = new ArrayList();
		if(jsonarr!=null){
			for (int i=0; i<jsonarr.size(); ++i) {	
				PayHistoryForm phf = new PayHistoryForm();
				JSONObject json = (JSONObject)jsonarr.get(i);
				phf.setId(json.get("id")==null?"":json.get("id").toString());
				phf.setOrgid(json.get("orgid")==null?"":json.get("orgid").toString());
				//转换格式，把单位从分转换为元
				DecimalFormat sumd=new DecimalFormat("0.00");
				String paymoney = json.get("paymoney")==null?"0":json.get("paymoney").toString();
				String realmoney = json.get("realmoney")==null?"0":json.get("realmoney").toString();
				String lessenmoney = json.get("lessenmoney")==null?"0":json.get("lessenmoney").toString();
				String beforpay = json.get("beforpay")==null?"0":json.get("beforpay").toString();
				String afterpay = json.get("afterpay")==null?"0":json.get("afterpay").toString();
				String price = json.get("price")==null?"0":json.get("price").toString();
				String excessprice = json.get("excessprice")==null?"0":json.get("excessprice").toString();

				phf.setPaymoney(sumd.format((double)Long.parseLong(paymoney)/100));
				phf.setRealmoney(sumd.format((double)Long.parseLong(realmoney)/100));
				phf.setLessenmoney(sumd.format((double)Long.parseLong(lessenmoney)/100));
				phf.setBeforpay(sumd.format((double)Long.parseLong(beforpay)/100));
				phf.setAfterpay(sumd.format((double)Long.parseLong(afterpay)/100));
				phf.setPrice(sumd.format((double)(Long.parseLong(price))/100));
				phf.setAllowexcess(json.get("allowexcess")==null?"":json.get("allowexcess").toString());
				phf.setExcessprice(sumd.format((double)(Long.parseLong(excessprice))/100));
				phf.setOperatetime(json.get("operatetime")==null?"":json.get("operatetime").toString());
				phf.setOperateuser(json.get("operateuser")==null?"":json.get("operateuser").toString());
				phf.setParam(json.get("param")==null?"":json.get("param").toString());
				phf.setCount(json.get("count")==null?0:Integer.parseInt(json.get("count").toString()));
				phList.add(phf);
			}
		}                       
        return phList;
    }
    
    /**
     *获取计费单价信息列表
    **/
    @SuppressWarnings("unchecked")
	public ArrayList<PriceinfoForm> getPriceinfo(String token,String corgid) throws ParseException{
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("corgid", corgid));
		JSONArray jsonarr = hc.getJArray("pricelist", param);
		ArrayList<PriceinfoForm> piList = new ArrayList<PriceinfoForm>();
		if(jsonarr!=null){
			for (int i=0; i<jsonarr.size(); ++i) {	
				PriceinfoForm pif = new PriceinfoForm();
				JSONObject json = (JSONObject)jsonarr.get(i);
				pif.setId(json.get("id")==null?"":json.get("id").toString());
				pif.setOrgid(json.get("orgid")==null?"":json.get("orgid").toString());
				String price = json.get("price")==null?"0":json.get("price").toString();
				String overfulfilprice = json.get("overfulfilprice")==null?"0":json.get("overfulfilprice").toString();
				//转换格式，把单位从分转换为元
				DecimalFormat sumd=new DecimalFormat("0.00");
				pif.setPrice(sumd.format((double)(Long.parseLong(price))/100));
				pif.setAllowoverfulfil(json.get("allowoverfulfil")==null?"0":json.get("allowoverfulfil").toString());
				pif.setOverfulfilprice(sumd.format((double)(Long.parseLong(overfulfilprice))/100));
				pif.setBegintime(json.get("begintime")==null?"":json.get("begintime").toString());
				pif.setOperateuser(json.get("operateuser")==null?"":json.get("operateuser").toString());
				pif.setParam(json.get("param")==null?"":json.get("param").toString());
				piList.add(pif);
			}
		}                       
        return piList;
    }
    
    /**
     *添加充值记录
    **/
    public String addPayRecord(String token,String corgid,String paymoney,String realmoney,String lessenmoney,String price,String allowexcess,String excessprice,String operatetime,String operateuser,String param,String beforpay,String afterpay) throws ParseException{
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corgid",corgid)); 
		params.add(new BasicNameValuePair("accessKey", token)); 
		params.add(new BasicNameValuePair("paymoney", paymoney));
		params.add(new BasicNameValuePair("realmoney",realmoney));
		params.add(new BasicNameValuePair("lessenmoney", lessenmoney)); 
	 	params.add(new BasicNameValuePair("price", price)); 
	 	params.add(new BasicNameValuePair("allowexcess", allowexcess)); 
	 	params.add(new BasicNameValuePair("excessprice", excessprice)); 
	 	params.add(new BasicNameValuePair("operatetime", operatetime)); 
	 	params.add(new BasicNameValuePair("operateuser", operateuser)); 
	 	params.add(new BasicNameValuePair("param", param)); 
	 	params.add(new BasicNameValuePair("beforpay", beforpay)); 
	 	params.add(new BasicNameValuePair("afterpay", afterpay)); 	
		params.add(new BasicNameValuePair("recordType", "0"));
	 	JSONObject json = hc.getJObject("payhistoryadd", params);
	 	
	 	String flag = "1";
		if(json!=null){
			if(json.size()>0){		
				flag = json.get("msg").toString();
			}
		}                       
        return flag;
    }
    
    /**
     *修改计费账户金额信息
    **/
    public String updateAccountInfo(String token,String corgid,String paymoney,String realmoney,String lessenmoney,String remainingsum,String remindsum,String reminded,String param) throws ParseException{
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corgid",corgid)); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("paymoney", paymoney));
		params.add(new BasicNameValuePair("realmoney",realmoney));
		params.add(new BasicNameValuePair("lessenmoney", lessenmoney));
		params.add(new BasicNameValuePair("remainingsum", remainingsum)); 
		params.add(new BasicNameValuePair("remindsum", remindsum)); 
		params.add(new BasicNameValuePair("reminded", reminded));
	 	params.add(new BasicNameValuePair("param", param)); 
	 	JSONObject json = hc.getJObject("accountupdate", params);
	 	
	 	String flag = "1";
		if(json!=null){
			if(json.size()>0){		
				flag = json.get("msg").toString();
			}
		}                       
        return flag;
    }
    
    /**
     *添加计费单价记录
    **/
    public String addPriceinfo(String token,String corgid,String price,String  allowoverfulfil,String overfulfilprice,String begintime,String operateuser,String param) throws ParseException{
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corgid",corgid)); 
		params.add(new BasicNameValuePair("accessKey", token)); 
	 	params.add(new BasicNameValuePair("price", price)); 
	 	params.add(new BasicNameValuePair("allowoverfulfil", allowoverfulfil)); 
	 	params.add(new BasicNameValuePair("overfulfilprice", overfulfilprice)); 
	 	params.add(new BasicNameValuePair("begintime", begintime)); 
	 	params.add(new BasicNameValuePair("operateuser", operateuser)); 
	 	params.add(new BasicNameValuePair("param", param)); 
	 	JSONObject json = hc.getJObject("priceadd", params);
	 	
	 	String flag = "1";
		if(json!=null){
			if(json.size()>0){		
				flag = json.get("msg").toString();
			}
		}                       
        return flag;
    }
    
    /**
     *删除计费单价记录
    **/
    public String delPriceinfo(String token,String id) throws ParseException{
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("accessKey", token)); 
	 	params.add(new BasicNameValuePair("id", id)); 
	 	JSONObject json = hc.getJObject("pricedel", params);
	 	
	 	String flag = "1";
		if(json!=null){
			if(json.size()>0){		
				flag = json.get("msg").toString();
			}
		}                       
        return flag;
    }
    
    /**
     *添加充值账户
    **/
    public String addAccountinfo(String token,String corgid,String paymoney,String realmoney,String lessenmoney,String remainingsum,String remindsum,String reminded,String param) throws ParseException{
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corgid",corgid)); 
		params.add(new BasicNameValuePair("accessKey", token)); 
		params.add(new BasicNameValuePair("paymoney", paymoney));
		params.add(new BasicNameValuePair("realmoney",realmoney));
		params.add(new BasicNameValuePair("lessenmoney", lessenmoney));
		params.add(new BasicNameValuePair("remainingsum", remainingsum)); 
		params.add(new BasicNameValuePair("remindsum", remindsum)); 
		params.add(new BasicNameValuePair("reminded", reminded));
	 	params.add(new BasicNameValuePair("param", param)); 
	 	JSONObject json = hc.getJObject("accountadd", params);
	 	
	 	String flag = "1";
		if(json!=null){
			if(json.size()>0){		
				flag = json.get("msg").toString();
			}
		}                       
        return flag;
    }
    /**
     * 充值
     * @param token
     * @param corgid 充值企业
     * @param recordtype 充值类型
     * @param param 充值描述
     * @param realmoney 充值金额
     * @param lessenmoney 优惠金额
     * @param price 单价
     * @param allowexcess 是否允许超额
     * @param excessprice 超额单价
     * @return
     */
    public String pay(String token,String corgid,String recordtype,String param,String realmoney,String lessenmoney,String price,String allowexcess,String excessprice){
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corgid",corgid)); 
		params.add(new BasicNameValuePair("accessKey", token)); 
		params.add(new BasicNameValuePair("realmoney",realmoney));
		params.add(new BasicNameValuePair("lessenmoney", lessenmoney));
	 	params.add(new BasicNameValuePair("param", param)); 
	 	params.add(new BasicNameValuePair("allowexcess", allowexcess)); 
	 	params.add(new BasicNameValuePair("excessprice", excessprice)); 
	 	params.add(new BasicNameValuePair("price", price)); 
	 	params.add(new BasicNameValuePair("recordtype", recordtype)); 
	 	JSONObject json = hc.getJObject("pay", params);
		if(json!=null){
			return json.get("msg").toString();
		}                       
        return "1";
    }
    /**
     * 更改提醒金额
     * @param token
     * @param orgid
     * @param remindsum
     * @return
     */
    public String updateRemind(String token,String orgid,String remindsum){
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corgid",orgid+"")); 
		params.add(new BasicNameValuePair("accessKey", token)); 
		params.add(new BasicNameValuePair("remindsum",remindsum+""));
	 	JSONObject json = hc.getJObject("updateremind", params);
		if(json!=null){
			return json.get("msg").toString();
		}                       
		return "1";
    }
    /**
     * 更改提醒金额
     * @param token
     * @param orgid
     * @param overfulfilsum 超额额度
     * @return
     */
    public String updateOverfulfilsum(String token,String orgid,String overfulfilsum){
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corgid",orgid+"")); 
		params.add(new BasicNameValuePair("accessKey", token)); 
		params.add(new BasicNameValuePair("overfulfilsum",overfulfilsum+""));
	 	JSONObject json = hc.getJObject("updateoverfulfilsum", params);
		if(json!=null){
			return json.get("msg").toString();
		}                       
		return "1";
    }
    
    /**
     * 将单位为（分）或者（分/分钟）的字符串转换成单位为（元）或者（元/分钟）的0.00格式的字符串
     */
    public String fen2yuan(String fen) throws ParseException{
    	String yuan = "0.00";
    	DecimalFormat sumd=new DecimalFormat("0.00");
    	yuan = sumd.format((double)Double.parseDouble(fen)/100);
    	return yuan;
    }
    
    /**
     * 将单位为（分）或者（分/分钟）0.00格式转换成单位为（元）或者（元/分钟）
     */
    public String yuan2fen(String yuan) throws ParseException{
    	long fen = 0;
    	fen = (long) (Double.parseDouble(yuan)*100);
    	return fen+"";
    }

}
