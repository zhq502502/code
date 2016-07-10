package com.seegle.data;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.seegle.beans.PhoneAdmin;
import com.seegle.form.UserInfoActionForm;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class PhoneUserOperation {
	private String orgid;
	private HttpClient hc;
	private String setName = "set names utf8";	
	private SQLOperation so = new SQLOperation();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public PhoneUserOperation(String orgid) throws UnsupportedEncodingException{
		hc = new HttpClient(PropUtil.getInstance().getValue("apiUrl"), orgid);
	}
		
	/**
	 * 获取公共电话会议管理员列表
	 **/
	public ArrayList getPhoneAdminList(String orgid, String token,String type) {
    	String sql = "select id, user_name, alias, role from `user` where orgid="+orgid+" ORDER BY user_name";
    	ArrayList userList = new ArrayList();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取电话会议管理员列表：参数："+"orgid:"+orgid+",token:"+token+",sql："+sql);
    		ps = conn.prepareStatement(sql);      	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
            while (rs.next()) {
            UserInfoActionForm uif = new UserInfoActionForm();
            uif.setUserId(rs.getInt("id"));
            uif.setUser_name(rs.getString("user_name"));
            uif.setAlias(rs.getString("alias"));
            uif.setRoleId(rs.getInt("role")); 
            userList.add(uif);
            }
            
    		List<PhoneAdmin> list = new ArrayList<PhoneAdmin>();
    		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
    		params.add(new BasicNameValuePair("accessKey", token));
    		params.add(new BasicNameValuePair("usertype", type));

    		JSONArray jsonarr = hc.getJArray("getphoneadminlist", params);
    		int size = jsonarr.size();

    		if(userList.size()>0){
    			for (int i=0; i<userList.size(); ++i) {
    				UserInfoActionForm uif = new UserInfoActionForm();
    				uif = (UserInfoActionForm) userList.get(i);
    				for(int j=0;j<size;j++){
    					JSONObject obj = (JSONObject)jsonarr.get(j);
    					if(uif.getUserName().equals(obj.get("account").toString())){
    					uif.setSelected(true);	
    				    }
    			    }
    			}				
    		}
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取电话会议管理员列表出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取电话会议管理员列表出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 		
        return userList;  
    }

	/**
	 * 获取个人电话会议管理员列表
	 **/
	public ArrayList getPersonalAdminList(String orgid, String token) {
    	String sql = "select id, user_name, alias, role from `user` where orgid="+orgid+" ORDER BY user_name";
    	ArrayList userList = new ArrayList();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取个人电话会议管理员列表：参数："+"orgid:"+orgid+",token:"+token+",sql："+sql);
    		ps = conn.prepareStatement(sql);      	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
            while (rs.next()) {
            UserInfoActionForm uif = new UserInfoActionForm();
            uif.setUserId(rs.getInt("id"));
            uif.setUser_name(rs.getString("user_name"));
            uif.setAlias(rs.getString("alias"));
            uif.setRoleId(rs.getInt("role")); 
            userList.add(uif);
            }
            
    		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
    		params.add(new BasicNameValuePair("accessKey", token));
    		params.add(new BasicNameValuePair("usertype", "2"));
    		JSONArray jsonarr = hc.getJArray("getphoneadminlist", params);
    		int size = jsonarr.size();

    		List<NameValuePair> params1 = new ArrayList<NameValuePair>(); 
    		params1.add(new BasicNameValuePair("accessKey", token));
    		params1.add(new BasicNameValuePair("usertype", "1"));

    		JSONArray jsonarr1 = hc.getJArray("getphoneadminlist", params1);
    		int size1 = jsonarr1.size();

    		if(userList.size()>0){
    			for (int i=0; i<userList.size(); ++i) {
    				UserInfoActionForm uif = new UserInfoActionForm();
    				uif = (UserInfoActionForm) userList.get(i);
    				for(int j=0;j<size;j++){
    					JSONObject obj = (JSONObject)jsonarr.get(j);
    					if(uif.getUserName().equals(obj.get("account").toString())){
    					uif.setSelected(true);	
    				    }
    			    }
    				for(int k=0;k<size1;k++){
    					JSONObject obj = (JSONObject)jsonarr1.get(k);
    					if(uif.getUserName().equals(obj.get("account").toString())){
    					userList.remove(uif);
    				    }
    			    }
    			}				
    		}
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取电话会议管理员列表出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取电话会议管理员列表出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 		
        return userList;  
    }
	
	/**
	 * 添加电话会议管理员
	 **/
	public int addPhoneAdmin(String orgid, String token,String accounts,String type) {
		if(type.equals("1")){   //添加公共电话会议管理员前先判断其是否为个人管理员，若为个人管理员，则删除个人管理员身份
			String a[] = accounts.split(";");
			for(int i=0;i<a.length;i++){
	    		List<NameValuePair> params1 = new ArrayList<NameValuePair>(); 
	    		params1.add(new BasicNameValuePair("accessKey", token));
	    		params1.add(new BasicNameValuePair("usertype", "2"));
	    		params1.add(new BasicNameValuePair("userid", a[i]));
	    	    JSONArray jsonarr1 = hc.getJArray("getphoneadminlist", params1);
	    	    if(jsonarr1.size()>0){
	    	    	for(int j=0;j<jsonarr1.size();j++){
	    	    		JSONObject obj = (JSONObject)jsonarr1.get(j);
	    	    		if(obj.get("account").toString().equals(a[i])){
	    	    			delPhoneAdmin(orgid,token,a[i]);
	    	    		}
	    	    	}	    	    	
	    	    }
			}

		}
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("usertype", type));
		params.add(new BasicNameValuePair("userids", accounts));

		JSONObject json = hc.getJObject("addPhoneAdmin", params);
		int flag = Integer.parseInt(json.get("msg").toString());
		if(flag==0){
			String newAccounts = accounts.replace(";", "','");
	    	String sql = "update `user` set phonetype=? where user_name in ('"+newAccounts+"') and orgid = "+orgid;
	    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
	    	try {
	        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户：参数："
	        			+"orgid:"+orgid+",usernames:"+accounts+",phonetype:"+type+",sql："+sql);
	        	ps = conn.prepareStatement(sql);
	        	ps.executeQuery(setName);
	        	ps.setString(1, type);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量更新用户失败"+e);
	            e.printStackTrace();
	        } catch (Exception e) {
	            SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量更新用户失败"+e);
	            e.printStackTrace();
	        } finally{
	        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
	        }
		}
        return flag;  
    }
	
	/**
	 * 删除电话会议管理员
	 **/
	public int delPhoneAdmin(String orgid, String token,String accounts) {           
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("userids", accounts));

		JSONObject json = hc.getJObject("delPhoneAdmin", params);
	    System.out.println("################"+json.toString());
		int flag = Integer.parseInt(json.get("msg").toString());
		if(flag==0){
			String newAccounts = accounts.replace(";", "','");
	    	String sql = "update `user` set phonetype=? where user_name in ('"+newAccounts+"') and orgid = "+orgid;
	    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
	    	try {
	        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户：参数："
	        			+"orgid:"+orgid+",usernames:"+accounts+",phonetype:"+"0"+",sql："+sql);
	        	ps = conn.prepareStatement(sql);
	        	ps.executeQuery(setName);
	        	ps.setString(1, "0");
	            ps.executeUpdate();
	        } catch (SQLException e) {
	        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量更新用户失败"+e);
	            e.printStackTrace();
	        } catch (Exception e) {
	            SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量更新用户失败"+e);
	            e.printStackTrace();
	        } finally{
	        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
	        }
		}
        return flag;  
    }
	/**
	 * 获取用户电话会议类型
	 * @author zhangqing
	 * @date 2014-3-20 下午03:37:21
	 * @param token
	 * @param orgid
	 * @param useraccount
	 * @param orgtype
	 * @return
	 */
	 public int getPhonetype(String token,String orgid, String useraccount,int orgtype){
		for(int i=1;i<3;i++){
			List<NameValuePair> params = new ArrayList<NameValuePair>(); 
			params.add(new BasicNameValuePair("accessKey", token));
			params.add(new BasicNameValuePair("usertype", ""+i));
			JSONArray jsonarr = hc.getJArray("getphoneadminlist", params);
			for(int j = 0;j<jsonarr.size();j++){
				JSONObject obj = (JSONObject)jsonarr.get(j);
				if(orgtype==0){
					if((obj.get("account")+"").equals(useraccount)){
						return i;
					}
				}else{
					if((obj.get("id")+"").equals(useraccount)){
						return i;
					}
				}
			}
		}
 		
 		return 0;
	 }
	 /**
	 * 是否为客户人员
	 * @author zhangqing
	 * @date 2014-8-15 上午10:42:23
	 * @param userid
	 * @return
	 */
	public boolean isServeruser(String token,String userid){
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("accessKey", token));
		params.add(new BasicNameValuePair("orgid", orgid));
		JSONArray jsonarr = hc.getJArray("serveruser", params);
		int size = jsonarr.size();
		for(int i = 0;i<size;i++){
			JSONObject obj = (JSONObject)jsonarr.get(i);
			if(userid.equals(obj.get("userid").toString())){
				return true;
			}
		}
		return false;
	}
}
