package com.seegle.data;

import com.seegle.form.CountUserDetailActionForm;
import com.seegle.form.MeetingCountConfActionForm;
import com.seegle.form.MeetingCountConfDetailActionForm;
import com.seegle.form.MeetingCountUserActionForm;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConfCountOperation {
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private String orgid;
	HttpClient hc ;
	private ConfCountOperation(){
		hc = new HttpClient(url,orgid);
	}
	public ConfCountOperation(String orgid){
		this.orgid = orgid;
		hc = new HttpClient(url,orgid);
	}
    /**
     *统计所有会议室日志记录数
    **/
    public int getConfLogCount(String token,String begintime,String endtime){
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", "0")); 
		param.add(new BasicNameValuePair("offset", "1"));	 
		JSONArray jsonarr = hc.getJArray("logconf", param);	
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
     *统计所有会议室日志
    **/
    public ArrayList getConfLogInfo(String token,String begintime,String endtime,int firstIndex, int showNumber){
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime));
		param.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		param.add(new BasicNameValuePair("offset", String.valueOf(showNumber)));		
		JSONArray jsonarr = hc.getJArray("logconf", param);	
		ArrayList ccList = new ArrayList();	

		if(jsonarr!=null){
			if(jsonarr.size()>0){				
				for (int i=0; i<jsonarr.size(); ++i) {
					MeetingCountConfActionForm mccf = new MeetingCountConfActionForm();
					JSONObject json = (JSONObject)jsonarr.get(i);
			    	mccf.setConfid(Integer.parseInt(json.get("confid").toString()));
			    	mccf.setConfname(json.get("confname").toString());
			    	mccf.setUsernum(Integer.parseInt(json.get("confusernum").toString()));
			    	mccf.setConftotalnum(Integer.parseInt(json.get("usenum").toString()));
			    	mccf.setConftotaltime(json.get("usetotaltime").toString()); //使用总时长
			    	mccf.setUsertotaltime(json.get("useavgtime").toString());//平均时间
			    	ccList.add(mccf);				    						 
				}				
			}
		}                       
        return ccList;
  	}
    
    /**
     *统计单个会议室日志记录数
     * @throws ParseException 
    **/
    public int getSingleConfLogCount(String token,String begintime,String endtime,String confid) throws ParseException{
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("id", confid)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", "0")); 
		param.add(new BasicNameValuePair("offset", "1"));
		JSONArray jsonarr = hc.getJArray("logconfsingle", param);	
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
     *统计单个会议室所有日志
     * @throws ParseException 
    **/
    public ArrayList getSingleConfLogInfo(String token,String begintime,String endtime,String confid,int firstIndex, int showNumber) throws ParseException{
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("id", confid)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		param.add(new BasicNameValuePair("offset", String.valueOf(showNumber)));
		JSONArray jsonarr = hc.getJArray("logconfsingle", param);	
		ArrayList cdList = new ArrayList();	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(jsonarr!=null){
			if(jsonarr.size()>0){				
				for (int i=0; i<jsonarr.size(); ++i) {
	        		MeetingCountConfDetailActionForm mccdf = new MeetingCountConfDetailActionForm();
					JSONObject json = (JSONObject)jsonarr.get(i);
					mccdf.setUseraccount(json.get("useraccount")==null?"":json.get("useraccount").toString());
	        		mccdf.setUserid(Integer.parseInt(json.get("userid").toString()));
	        		mccdf.setUsername(json.get("username").toString());
	        		mccdf.setUserlogintime(json.get("intime").toString());
	        		mccdf.setUserlogouttime(json.get("outtime").toString());
	        		Date d1 = df.parse(json.get("intime").toString());
	        		Date d2 = df.parse(json.get("outtime").toString());
	        		long onlinetime = (d2.getTime()-d1.getTime())/1000;
	        		mccdf.setUseronlinetime(timeFormat((int)onlinetime,"小时","分钟","秒"));
	        		cdList.add(mccdf);				    						 
				}				
			}
		}                       
        return cdList;
    }

    /**
     *统计所有会议人员日志记录数
    **/
    public int getUserLogCount(String token,String begintime,String endtime){
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", "0")); 
		param.add(new BasicNameValuePair("offset", "1"));
		JSONArray jsonarr = hc.getJArray("loguser", param);	
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
     *统计所有会议人员日志
    **/
    public ArrayList getUserLogInfo(String token,String begintime,String endtime,int firstIndex, int showNumber){ 		
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime));
		param.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		param.add(new BasicNameValuePair("offset", String.valueOf(showNumber)));
		JSONArray jsonarr = hc.getJArray("loguser", param);	
		ArrayList cuList = new ArrayList();	

		if(jsonarr!=null){
			if(jsonarr.size()>0){				
				for (int i=0; i<jsonarr.size(); ++i) {
					JSONObject json = (JSONObject)jsonarr.get(i);
	        		MeetingCountUserActionForm mcuf = new MeetingCountUserActionForm();
	        		mcuf.setUseraccount(json.get("useraccount")==null?"":json.get("useraccount").toString());
	        		mcuf.setUserid(Integer.parseInt(json.get("userid").toString()));
	        		mcuf.setUsername(json.get("username").toString());
	        		mcuf.setUseronlinenum(json.get("usenum").toString());
	        		mcuf.setUsertotaltime(json.get("usetotaltime").toString());
	        		mcuf.setAvglogintime(json.get("useavgtime").toString());
	        		mcuf.setLogintime(json.get("lastlogintime").toString());  
	        		cuList.add(mcuf);			    						 
				}				
			}
		}                       
        return cuList;
  	}

    /**
     *获取会议人员详细日志总数
    **/
    public int getSingleUserLogCount(String token,String begintime,String endtime,String userid){  		  		
		HttpClient hc = new HttpClient(url,orgid);
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token.toString())); 
		param.add(new BasicNameValuePair("id", userid)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", "0")); 
		param.add(new BasicNameValuePair("offset", "1"));
		JSONArray jsonarr = hc.getJArray("logusersingle", param);	
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
     *获取会议人员详细日志
     * @throws ParseException 
    **/
    public ArrayList getSingleUserLogInfo(String token,String begintime,String endtime,String userid,int firstIndex, int showNumber) throws ParseException{
		HttpClient hc = new HttpClient(url,orgid);
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("accessKey", token.toString())); 
		param.add(new BasicNameValuePair("id", userid)); 
		param.add(new BasicNameValuePair("btime", begintime)); 
		param.add(new BasicNameValuePair("etime", endtime)); 
		param.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		param.add(new BasicNameValuePair("offset", String.valueOf(showNumber)));
		JSONArray jsonarr = hc.getJArray("logusersingle", param);	
  		ArrayList udList = new ArrayList();	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(jsonarr!=null){
			if(jsonarr.size()>0){				
				for (int i=0; i<jsonarr.size(); ++i) {
					JSONObject json = (JSONObject)jsonarr.get(i);
	        		CountUserDetailActionForm cudf = new CountUserDetailActionForm();
	        		cudf.setConfid(Integer.parseInt(json.get("confid").toString()));
	        		cudf.setConfname(json.get("confname").toString());
	        		cudf.setLogintime(json.get("intime").toString());
	        		cudf.setLogouttime(json.get("outtime").toString());
	        		Date d1 = df.parse(json.get("outtime").toString());
	        		Date d2 = df.parse(json.get("intime").toString());
	        		long onlinetime = (d1.getTime()-d2.getTime())/1000;
	        		cudf.setOnlinetime(timeFormat((int)onlinetime,"小时","分钟","秒"));
	        		//cudf.setOnlinetime(timeFormat(Integer.parseInt(json.get("onlinetime").toString()),"小时","分","秒"));
	        		udList.add(cudf);
				}				
			}
			}
        return udList;
  	}
    
    /**
     * 时间长度格式化     
     **/
    public String timeFormat(int second,String h,String m,String s){
    	String t = "";
    	int hour = second/3600;
    	if(hour>0){
    		t+=hour + " " + h + " ";
    	}
    	int min = (second/60)%60;
    	if(hour>0 || min>0){
    		t+=min + m + " ";
    	}
    	second = second%60;
    	t+=second + s + " ";
    	return t;
    }
}
