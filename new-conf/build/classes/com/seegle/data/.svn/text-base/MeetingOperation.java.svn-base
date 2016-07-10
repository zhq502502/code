package com.seegle.data;

import com.seegle.form.ConfGroupActionForm;
import com.seegle.form.ConfRoomActionForm;
import com.seegle.util.SeegleLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * 会议操作类
 */
public class MeetingOperation {
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private String orgid;
	HttpClient hc ;
	private SQLOperation sqlOper;
	private String setName = "set names utf8";
	private PreparedStatement ps = null;
	ResultSet rs = null;
	private MeetingOperation(){
		
	}
	public MeetingOperation(String orgid){
		this.orgid = orgid;
		hc = new HttpClient(url,orgid);
		sqlOper = new SQLOperation();
	}
    /**
     * 会议列表（分页显示，只显示未过期的会议室） 显示记录的起始位置：firstIndex 每页显示记录数：showNumber
     * @param orderName 排序名称
     * @param orderType 排序方式
     **/
    public ArrayList queryMeetingList(String confName, int firstIndex, int showNumber, String token, String orgid) {
        ArrayList aList = new ArrayList();
        int pageNumber = 1;  //获取分页数，默认分1页
        int maxNumber = 0;  //总记录数

        /*begin 获得总记录数 */
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("orgid", orgid)); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("cname", confName));
		params.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		params.add(new BasicNameValuePair("count", String.valueOf(showNumber)));
		JSONObject json = hc.getJObject("confsum", params);
    	maxNumber = Integer.parseInt(json.get("confsum").toString());
        /*end 获得总记录数 */
        
        /*begin 获得未过期会议室总记录数 */
		List<NameValuePair> params3 = new ArrayList<NameValuePair>();
		params3.add(new BasicNameValuePair("orgid", orgid)); 
		params3.add(new BasicNameValuePair("accessKey", token));
		params3.add(new BasicNameValuePair("cname", confName));
		params3.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		params3.add(new BasicNameValuePair("count", String.valueOf(showNumber)));
		params3.add(new BasicNameValuePair("showlist", "available")); //只计算未过期会议室数
		JSONObject json3 = hc.getJObject("confsum", params3);
		int availableconf = 0;  //未过期会议室总记录数
    	availableconf = Integer.parseInt(json3.get("confsum").toString());            	
        pageNumber = (availableconf + showNumber - 1) / showNumber;  //分页数
        /*end 获得未过期会议室总记录数*/
       
        /*begin 获得未过期会议室列表 */
		List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		params2.add(new BasicNameValuePair("orgid", orgid)); 
		params2.add(new BasicNameValuePair("accessKey", token));
		params2.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		params2.add(new BasicNameValuePair("count", String.valueOf(showNumber)));
		params2.add(new BasicNameValuePair("cname", confName));
		Map<String,String> orderMap = sqlOper.getConfOrder(orgid);
		params2.add(new BasicNameValuePair("ordername", orderMap.get("ordername")));
		params2.add(new BasicNameValuePair("ordertype", orderMap.get("ordertype")));
		params2.add(new BasicNameValuePair("showlist", "available")); //只显示未过期会议室列表
		params2.add(new BasicNameValuePair("maxnum", String.valueOf(maxNumber))); //会议室总数
		JSONArray jsonarr = hc.getJArray("listall", params2);
		/*end 获得未过期会议室列表 */
		
		/*begin 获得置顶列表 */
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("accessKey", token));
		JSONArray jsonarr1 = hc.getJArray("gettoplist", params1);
		/*end 获得置顶列表 */
		
		if(jsonarr1.size()>0){	//如果有置顶会议				
	        if (jsonarr!=null){
				if(jsonarr.size()>0){				
					for (int i=0; i<jsonarr.size(); ++i) {						
						ConfRoomActionForm crf = new ConfRoomActionForm();						
						 JSONObject json1 = (JSONObject)jsonarr.get(i);
						 //System.out.println(json1.get("cid").toString());
						 crf.setId(json1.get("cid").toString());
						 crf.setConf_name(json1.get("confname").toString());			 
						 crf.setConf_begin_time(json1.get("btime").toString());			 
						 crf.setConf_end_time(json1.get("etime").toString());
						 crf.setMax_conf_user(Integer.parseInt(json1.get("maxconfuser").toString()));
						 crf.setPageNumber(pageNumber);  //分页数
						for(int j=0; j<jsonarr1.size(); ++j) {
							 JSONObject json2 = (JSONObject)jsonarr1.get(j);
							 if(json1.get("cid").toString().equals(json2.get("cid").toString())&&Integer.parseInt(json2.get("topValue").toString())>0){
								 crf.setConfExtra("top");
							 }							 
						}
						aList.add(crf);
					}				
				}
	        }
		}else{ 
	        if (jsonarr!=null){
				if(jsonarr.size()>0){				
					for (int i=0; i<jsonarr.size(); ++i) {
						 ConfRoomActionForm crf = new ConfRoomActionForm();
						 JSONObject json1 = (JSONObject)jsonarr.get(i);
						 crf.setId(json1.get("cid").toString());
						 crf.setConf_name(json1.get("confname").toString());			 
						 crf.setConf_begin_time(json1.get("btime").toString());			 
						 crf.setConf_end_time(json1.get("etime").toString());
						 crf.setMax_conf_user(Integer.parseInt(json1.get("maxconfuser").toString()));
						 crf.setPageNumber(pageNumber);  //分页数
						 aList.add(crf);
					}				
				}
	        }
		}
        return aList;
    }

    /**
     * 管理会议列表（分页显示，显示所有会议室） 显示记录的起始位置：firstIndex 每页显示记录数：showNumber
     * @param orderName 排序名称
     * @param orderType 排序方式
     **/
    public ArrayList queryManageMeetingList(String confName, int firstIndex, int showNumber, String token, String orgid) {
        ArrayList aList = new ArrayList();
        int pageNumber = 1;  //获取分页数，默认分1页
        int maxNumber = 0;  //总记录数

        /*begin 获得总记录数 */
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("orgid", orgid)); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("cname", confName));
		JSONObject json = hc.getJObject("confsum", params);
    	maxNumber = Integer.parseInt(json.get("confsum").toString());            	
        pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数
        /*end 获得总记录数 */

		List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		params2.add(new BasicNameValuePair("orgid", orgid)); 
		params2.add(new BasicNameValuePair("accessKey", token));
		params2.add(new BasicNameValuePair("start", String.valueOf(firstIndex))); 
		params2.add(new BasicNameValuePair("count", String.valueOf(showNumber)));
		params2.add(new BasicNameValuePair("cname", confName));
		Map<String,String> orderMap = sqlOper.getConfOrder(orgid);
		params2.add(new BasicNameValuePair("ordername", orderMap.get("ordername")));
		params2.add(new BasicNameValuePair("ordertype", orderMap.get("ordertype")));
		JSONArray jsonarr = hc.getJArray("listall", params2);

		/*begin 获得置顶列表 */
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("accessKey", token));
		JSONArray jsonarr1 = hc.getJArray("gettoplist", params1);
		
		if(jsonarr1.size()>0){	//如果有置顶会议				
	        if (jsonarr!=null){
				if(jsonarr.size()>0){				
					for (int i=0; i<jsonarr.size(); ++i) {						
						ConfRoomActionForm crf = new ConfRoomActionForm();						
						 JSONObject json1 = (JSONObject)jsonarr.get(i);
						 //System.out.println(json1.get("cid").toString());
						 crf.setId(json1.get("cid").toString());
						 crf.setConf_name(json1.get("confname").toString());			 
						 crf.setConf_begin_time(json1.get("btime").toString());			 
						 crf.setConf_end_time(json1.get("etime").toString());
						 crf.setMax_conf_user(Integer.parseInt(json1.get("maxconfuser").toString()));
						 crf.setPageNumber(pageNumber);  //分页数
						for(int j=0; j<jsonarr1.size(); ++j) {
							 JSONObject json2 = (JSONObject)jsonarr1.get(j);
							 if(json1.get("cid").toString().equals(json2.get("cid").toString())&&Integer.parseInt(json2.get("topValue").toString())>0){
								 crf.setConfExtra("top");
							 }							 
						}
						aList.add(crf);
					}				
				}
	        }
		}else{ 
	        if (jsonarr!=null){
				if(jsonarr.size()>0){				
					for (int i=0; i<jsonarr.size(); ++i) {
						 ConfRoomActionForm crf = new ConfRoomActionForm();
						 JSONObject json1 = (JSONObject)jsonarr.get(i);
						 crf.setId(json1.get("cid").toString());
						 crf.setConf_name(json1.get("confname").toString());			 
						 crf.setConf_begin_time(json1.get("btime").toString());			 
						 crf.setConf_end_time(json1.get("etime").toString());
						 crf.setMax_conf_user(Integer.parseInt(json1.get("maxconfuser").toString()));
						 crf.setPageNumber(pageNumber);  //分页数
						 aList.add(crf);
					}				
				}
	        }
		}
        return aList;
    }

    //获取置顶最大值
    public int queryMaxTopValue(String token) {
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("accessKey", token));
		JSONArray jsonarr = hc.getJArray("gettoplist", params1);
		int max = 0;

        if (jsonarr!=null) {
			if(jsonarr.size()>0){
				 JSONObject json1 = (JSONObject)jsonarr.get(0);
				 max = Integer.parseInt(json1.get("topValue").toString());
				for(int i=1;i<jsonarr.size();i++){
					 JSONObject json2 = (JSONObject)jsonarr.get(i);
					 if(Integer.parseInt(json2.get("topValue").toString())>max){
						 max = Integer.parseInt(json2.get("topValue").toString());
					 }
				   }
				}				
			}
        return max+1;
    }
    
    /**
     * 获取会议服务集群
     **/
    public ArrayList queryGroup(String token){    	
    	ArrayList groupList = new ArrayList();
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		JSONArray jsonarr = hc.getJArray("group", params);
		
        if (jsonarr!=null) {
			if(jsonarr.size()>0){				
				for (int i=0; i<jsonarr.size(); ++i) {
					 ConfGroupActionForm cgf = new ConfGroupActionForm();
					 JSONObject json1 = (JSONObject)jsonarr.get(i);
					 cgf.setId(json1.get("id").toString());
					 cgf.setName(json1.get("name").toString());			 
					 groupList.add(cgf);
				}				
			}
        } 
		return groupList;
    	
    }
    
    /**
     * 获取单个会议室信息
     **/
    public ConfRoomActionForm querySingleMeeting(String token, String orgid,String confid) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("orgid", orgid)); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("cid", confid)); 
		params.add(new BasicNameValuePair("type","get"));
		
	    JSONObject json = hc.getJObject("listone", params); 
		ConfRoomActionForm crf = new ConfRoomActionForm();
		crf.setId(confid);
		crf.setConf_name(json.get("confname").toString());
		crf.setConf_begin_time(json.get("confbegintime").toString());
		crf.setConf_end_time(json.get("confendtime").toString());
		crf.setMax_conf_user(Integer.parseInt(json.get("maxconfuser").toString()));
		crf.setMax_conf_spokesman(Integer.parseInt(json.get("maxconfspokesman").toString()));
		crf.setMax_conf_tourist(Integer.parseInt(json.get("maxconftourist").toString()));
		crf.setConf_password_md5(json.get("confpasswordmd5").toString());
		crf.setManage_password_md5(json.get("managepasswordmd5").toString());  
		crf.setConf_server_ip(json.get("confgrouptype").toString());
		crf.setOpen_flag(Integer.parseInt(json.get("open_flag").toString()));		
		crf.setLock_flag(Integer.parseInt(json.get("lock_flag").toString()));
		crf.setAuto_clear_flag(Integer.parseInt(json.get("auto_clear_flag").toString()));		
		crf.setAll_can_visible(Integer.parseInt(json.get("all_can_visible").toString()));	
		crf.setOpen_audit(Integer.parseInt(json.get("open_audit").toString()));	
		crf.setDownload_mode(Integer.parseInt(json.get("download_mode").toString()));	
		crf.setDescription(json.get("description").toString()); 
		String confSetting = json.get("confsetting").toString();
		if(confSetting.startsWith("<sid>=")){
	        crf.setVideo_quality(3);
	        crf.setVRenderer(1);
	        crf.setRecordMode(0);
	        crf.setConfExtra("<H264QP>=3;<VRenderer>=1;<AutoRecord>=0;"+confSetting);
		}else{
	        crf.setVideo_quality(Integer.parseInt(confSetting.substring(9,10)));
	        crf.setVRenderer(Integer.parseInt(confSetting.substring(23,24)));
	        String a[]=confSetting.split(";");
	        if(a.length>2){	        	
	        	if(a[2].startsWith("<AutoRecord>")){
	                crf.setRecordMode(Long.parseLong(a[2].substring(13)));
	        	}
	        	else{
	        		crf.setRecordMode(0);
	        	}
	        }else{
	        	crf.setRecordMode(0);
	        } 
	        crf.setConfExtra(confSetting);//保存confsetting完整字符串
		}
		
        return crf;
    }
    
    //获取最大并发数及提示选择
    public Map<String,String> getConfMaxuser(String orgid,String token){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("orgid", orgid)); 
		params.add(new BasicNameValuePair("accessKey", token));
		JSONObject json = hc.getJObject("concurrent", params);
		String concurrent = json.get("num").toString();
    	
    	Map<String,String> map = new HashMap<String, String>();
    	String sql = "select confuser_tip from company where orgid=?";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取最大并发数提示选择：参数："+"orgid:"+orgid+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, orgid);
        	rs = ps.executeQuery();
        	if(rs.next()){
        		String tip = "0";
        		if(rs.getString("confuser_tip")!=null){
        			tip = rs.getString("confuser_tip");
        		}
        		System.out.println("tip:"+tip);
        		map.put("tip", tip);
        		map.put("maxuser", concurrent);
        	}else{
        		map.put("ordername","0");
        		map.put("maxuser", concurrent);
        	}
    	}catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("根据企业Id获得其会议并发数提示出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("根据企业Id获得其会议并发数提示出错"+e);
            e.printStackTrace();
        }finally{
        	closeResources(rs, ps, conn, orgid);
        }
    	return map;
    }
    
    //关闭资源
    public void closeResources(ResultSet rs,PreparedStatement ps,Connection conn,String orgid){
    	try {
    		if(rs!=null){
    			rs.close();
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("rs关闭失败"+e);
		}
		try {
			if(ps!=null){
    			ps.close();
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("ps关闭失败"+e);
		}
		try {
			if(conn!=null){
    			conn.close();
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("conn关闭失败"+e);
		}
    }
}
