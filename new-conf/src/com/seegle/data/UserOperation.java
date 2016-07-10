package com.seegle.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.seegle.form.UserInfoActionForm;
import com.seegle.util.SeegleLog;

import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *用户操作类
 **/
public class UserOperation {
	private String setName = "set names utf8";	
	private SQLOperation so = new SQLOperation();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	private String orgid;
	private HttpClient hc ;
	private UserOperation(){
		
	}
	public UserOperation(String orgid){
		this.orgid = orgid;
		hc = new HttpClient(url,orgid);
	}
	
	/**
     * 获取用户总数,可以去掉自身
     * 搜索关键字：key  屏蔽账号：account1，account2
     */
    public int getUserCount(String orgid, String key, String account1,String account2) {
    	String sql = "select count(id) from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') AND (user_name<>'"+account1+"' and  user_name<>'"+account2+"') and orgid = "+orgid;
    	int maxNumber = 0;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户总数：参数："+"orgid:"+orgid+",key:"+key+",account1:"+account1+",account2:"+account2+",sql："+sql);
        	ps = conn.prepareStatement(sql);  	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	while (rs.next()) {
                 maxNumber = rs.getInt("count(id)");  //总记录数
            }
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户总数 出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户总数 出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 
        return maxNumber;  
    }
    
	/**
	 * 从数据库获取用户列表（分页显示） 
	 * 显示记录的起始位置：firstIndex 每页显示记录数：showNumber 企业id：orgid 搜索关键字：key  屏蔽账号：account1，account2
	 **/
	public ArrayList queryUserlist(String orgid, String key, String account1,String account2,int firstIndex,int showNumber) {
		int pageNumber = 1;  //获取分页数，默认分1页
        int maxNumber = 0;  //总记录数        
    	maxNumber = getUserCount(orgid, key,account1,account2);            	
    	pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数
    	String sql = "select id, user_name, alias, role from `user` where (user_name like '%"+key+"%' OR alias like '%"+key+"%') and orgid="+orgid+" AND (user_name<>'"+account1+"' AND user_name<>'"+account2+"') ORDER BY user_name LIMIT "+firstIndex+","+showNumber;
    	ArrayList userList = new ArrayList();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("从数据库获取用户列表：参数："+"orgid:"+orgid+",key:"+key+",account1:"+account1+",account2:"+account2+",firstIndex:"+firstIndex+",showNumber:"+showNumber+",sql："+sql);
    		ps = conn.prepareStatement(sql);      	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
            while (rs.next()) {
            UserInfoActionForm uif = new UserInfoActionForm();
            uif.setUserId(rs.getInt("id"));
            uif.setUser_name(rs.getString("user_name"));
            uif.setAlias(rs.getString("alias"));
            uif.setRoleId(rs.getInt("role")); 
            uif.setPageNumber(pageNumber);
            userList.add(uif);
            }
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从数据库获取用户列表（分页显示） 出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从数据库获取用户列表（分页显示） 出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 	
        return userList;  
    }	
	
	/**
	 * 添加单个用户
	 */
	public boolean addUser(String orgid,String user_name,String alias,String account_email,
			String account_mobile,String account_phone,String password_md5,String flag,
			String load_balance,String conf_server_ip,String proxytype,String proxyaddr,
			String proxyport,String proxyuser,String proxypass,String user_role){
		Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
		PreparedStatement ps = null;
		String addUser = "INSERT INTO user ";
        addUser += so.getSQL("addUser");
        try {      	
        	ps = conn.prepareStatement(addUser);
        	ps.setString(1, user_name);
        	ps.setString(2, alias);
        	ps.setString(3, account_email);
        	ps.setString(4, account_mobile);
        	ps.setString(5, account_phone);
        	ps.setString(6, password_md5);
        	ps.setString(7, flag);
        	ps.setString(8, load_balance);
        	ps.setString(9, conf_server_ip);
        	ps.setString(10, proxytype);
        	ps.setString(11, proxyaddr);
        	ps.setString(12, proxyport);
        	ps.setString(13, proxyuser);
        	ps.setString(14, proxypass);
        	ps.setString(15, password_md5);
        	ps.setString(16, user_role);
        	ps.setString(17, orgid);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;  
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("添加单个用户出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
		return false;
	}
	
	/**
	 * 从中心获取会议室默认与会者列表
	 **/
	public ArrayList queryCommonList(String orgid, String token, String confid) {
    	String sql = "select id, user_name, alias, role from `user` where orgid="+orgid+" ORDER BY user_name ";
    	ArrayList userList = new ArrayList();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取会议室默认与会者列表：参数："+"orgid:"+orgid+",token:"+token+",sql："+sql);
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
    		List<NameValuePair> param1 = new ArrayList<NameValuePair>();
    		param1.add(new BasicNameValuePair("accessKey", token)); 
    		param1.add(new BasicNameValuePair("cid", confid)); 
    		param1.add(new BasicNameValuePair("orgid",orgid));
    		param1.add(new BasicNameValuePair("type","get"));
    		String commonList = hc.getString("confcommonlist", param1);
           int a = commonList.indexOf("[");
           int b = commonList.indexOf("]");
           final String c = commonList.substring(a+1, b);
           final String[] d = c.split(",");

    		if(userList.size()>0){
    			for (int i=0; i<userList.size(); ++i) {
    				UserInfoActionForm uif = new UserInfoActionForm();
    				uif = (UserInfoActionForm) userList.get(i);
    				for(int j=0;j<d.length;j++){
    					if(uif.getUserName().equalsIgnoreCase(d[j])){
    					uif.setSelected(true);	
    				    }
    			    }
    			}				
    		}
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从中心获取会议室默认与会者列表出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从中心获取会议室默认与会者列表出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 	 		
        return userList;  
    }	

	/**
	 * 从中心获取会议室管理员列表
	 **/
	public ArrayList queryAdminList(String orgid, String token, String confid) {
    	String sql = "select id, user_name, alias, role from `user` where orgid="+orgid+" ORDER BY user_name";
    	ArrayList userList = new ArrayList();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取会议室默认与会者列表：参数："+"orgid:"+orgid+",token:"+token+",sql："+sql);
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
    		List<NameValuePair> param1 = new ArrayList<NameValuePair>();
    		param1.add(new BasicNameValuePair("accessKey", token)); 
    		param1.add(new BasicNameValuePair("cid", confid)); 
    		param1.add(new BasicNameValuePair("orgid",orgid));
    		param1.add(new BasicNameValuePair("type","get"));
    		String commonList = hc.getString("confadminlist", param1);
    		System.out.println(commonList);
           int a = commonList.indexOf("[");
           int b = commonList.indexOf("]");
           final String c = commonList.substring(a+1, b);
           final String[] d = c.split(",");

    		if(userList.size()>0){
    			for (int i=0; i<userList.size(); ++i) {
    				UserInfoActionForm uif = new UserInfoActionForm();
    				uif = (UserInfoActionForm) userList.get(i);
    				for(int j=0;j<d.length;j++){
    					if(uif.getUserName().equalsIgnoreCase(d[j])){
    					uif.setSelected(true);			
    				    }
    			    }
    			}				
    		}
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从中心获取会议室默认与会者列表出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从中心获取会议室默认与会者列表出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 		
        return userList;  
    }
	
	/**
	 * 带协同用户--从中心获取用户列表
	 **/
	public ArrayList queryTopUserList(String orgid, String token, String key,int firstIndex,int showNumber) {
		int pageNumber = 1;  //获取分页数，默认分1页
        int maxNumber = 0;  //总记录数        
        ArrayList userList = new ArrayList();
        
        /*begin 获得总记录数 */
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("orgid", orgid)); 
		params1.add(new BasicNameValuePair("accessKey", token));
		params1.add(new BasicNameValuePair("username", key));
		JSONObject json = hc.getJObject("usersum", params1);
    	maxNumber = Integer.parseInt(json.get("usersum").toString());   //获取用户总数                 	
        pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数
        /*end 获得总记录数 */
    	
    	List<NameValuePair> params2 = new ArrayList<NameValuePair>();
    	params2.add(new BasicNameValuePair("accessKey", token.toString())); 
    	params2.add(new BasicNameValuePair("orgid", orgid.toString())); 
    	params2.add(new BasicNameValuePair("username", key)); 
		JSONArray jsonarr = hc.getJArray("userlist", params2);
		
		if(jsonarr!=null){
			if(jsonarr.size()>0){				
				for (int i=0; i<jsonarr.size(); ++i) {
					UserInfoActionForm user = new UserInfoActionForm();
					 JSONObject json2 = (JSONObject)jsonarr.get(i);
					 int uid = Integer.parseInt(json2.get("userid").toString());
					 int usertype = Integer.parseInt(json2.get("usertype").toString());
					 String useraccount = json2.get("useraccount").toString();
					 user.setUserId(uid);
					 user.setUser_name(useraccount);
					 user.setRoleId(usertype);
					 user.setName(json2.get("username").toString());
					 user.setAlias(json2.get("alias").toString());
					 user.setPageNumber(pageNumber);
					 userList.add(user);
				}				
			}
			}  		
        return userList;  
    }
	
	/**
	 * 添加用户--第三方用户添加到数据库
	 **/
	public boolean addUser(String orgid,String username,String useraccount,String userpass,String email,String proxytype,String proxyaddress,String proxyport,String proxyuser,String proxypass) {
    	String sql = "insert into `user` (user_name,alias,role,name,account_email,account_mobile,account_phone,password_md5,dep_id,lastlogintime,logintag,load_balance,conf_server_ip,proxytype,proxyaddr,proxyport,proxyuser,proxypass,password_text,orgid)" +
    			"values ('"+useraccount+"', '"+username+"','4','','"+email+"','','',md5('"+userpass+"'),'1',now(),'0','0','','"+proxytype+"','"+proxyaddress+"','"+proxyport+"','"+proxyuser+"','"+proxypass+"','"+userpass+"',"+orgid+") ";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("添加用户：参数："
        			+"orgid:"+orgid+",username:"+username+",useraccount:"+useraccount+",userpass:"+userpass
        			+"，email:"+email+",proxytype:"+proxytype+",proxyaddress:"+proxyaddress+",proxyport:"+proxyport
        			+"，proxyuser:"+proxyuser+",proxypass:"+proxypass
        			+",sql："+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;  
        } catch (SQLException e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("添加用户出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("添加用户出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;  
    }
	
	/**
     * 更新用户信息--第三方用户更新数据库
     */
    public boolean modUser(String orgid,String alias,String account,String userpass,String email,String proxytype,String proxyaddr,String proxyport,String proxyuser,String proxypass) {
    	String sql = "";
    	if(userpass.equals("Itispass")){
        	sql = "update `user` set alias = '"+alias+"',account_email = '"+email+"',proxytype = '"+proxytype+"',proxyaddr = '"+proxyaddr+"',proxyport = '"+proxyport+"',proxyuser = '"+proxyuser+"',proxypass = '"+proxypass+"' where user_name = '"+account+"' and orgid = "+orgid;
    	}else{
        	sql = "update `user` set alias = '"+alias+"',password_md5 = md5('"+userpass+"'),password_text = '"+userpass+"',account_email = '"+email+"',proxytype = '"+proxytype+"',proxyaddr = '"+proxyaddr+"',proxyport = '"+proxyport+"',proxyuser = '"+proxyuser+"',proxypass = '"+proxypass+"' where user_name = '"+account+"' and orgid = "+orgid;
    	}
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户：参数："
        			+"orgid:"+orgid+",account:"+account+",userpass:"+userpass
        			+"，email:"+email+",proxytype:"+proxytype+",proxyaddress:"+proxyaddr+",proxyport:"+proxyport
        			+"，proxyuser:"+proxyuser+",proxypass:"+proxypass
        			+",sql："+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新用户信息出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新用户信息出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
   
    /**
     * 获取单个用户信息--第三方用户从数据库获取
     */
    public UserInfoActionForm getSingleUser(String orgid,String account) {
    	String sql = "select id, user_name, alias,account_email,proxytype,proxyaddr,proxyport,proxyuser,proxypass from `user` where user_name = '"+account+"' and orgid = "+orgid;
    	 UserInfoActionForm uif = new UserInfoActionForm();
    	 Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取单个用户信息：参数："+"orgid:"+orgid+",account:"+account+",sql："+sql);
        	ps = conn.prepareStatement(sql);    	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
            while(rs.next()){
                uif.setUserId(rs.getInt("id"));
                uif.setUser_name(rs.getString("user_name"));
                uif.setAlias(rs.getString("alias"));
                uif.setPassword_md5("Itispass");
                uif.setAccount_email(rs.getString("account_email"));
                uif.setProxytype(Integer.parseInt(rs.getString("proxytype")));
                uif.setProxyaddr(rs.getString("proxyaddr"));
                uif.setProxyport(rs.getString("proxyport"));
                uif.setProxyuser(rs.getString("proxyuser"));
                uif.setProxypass(rs.getString("proxypass"));
            }           
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取单个用户信息出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取单个用户信息出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return uif;  
    }
    
    /**
     *查询用户列表中最后一条记录ID
     **/
    public int getLastId(String orgid) {
        Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        String lastSql = "";  //获取最后一条记录的SQL
        ResultSet rs = null;
        PreparedStatement ps = null;
        int i = 0;
        try {
        	lastSql = "SELECT * FROM `user` where orgid="+orgid+" ORDER BY ID DESC LIMIT 1";
            ps = conn.prepareStatement(lastSql);
            ps.executeQuery(setName);
            rs = ps.executeQuery();
            while (rs.next()) {
                i = rs.getInt("id");  //总记录数
            }
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("查询用户列表中最后一条记录ID出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return i;
    }
    
    /**
     * 中中心库中获得用户邮箱列表，只查询存在邮箱的用户
     * @author zhangqing
     * @date 2013-5-7 下午03:52:17
     * @param orgid
     * @return
     */
    @SuppressWarnings("unchecked")
	public JSONArray getUserEmails(String orgid,String token){
    	JSONArray jsonArray = new JSONArray();
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("accessKey", token)); 
		params.add(new BasicNameValuePair("orgid", orgid)); 
		JSONArray jsonArray1 = hc.getJArray("userlist", params);	
		if(jsonArray1==null||jsonArray1.size()==0){
			return null;
		}
		for(int i = 0;i<jsonArray1.size();i++){
			JSONObject obj = (JSONObject)jsonArray1.get(i);
			String username = obj.get("userid").toString();
			String email = obj.get("email").toString();
			String alias = obj.get("alias").toString();
			if(!(email==null||email.equals(""))){
				JSONObject obj1 = new JSONObject();
				obj1.put("username", username);
				obj1.put("email", email);
				obj1.put("alias", alias);
				jsonArray.add(obj1);
			}
		}
		SeegleLog.getInstance().getLog(this.getClass(), orgid).debug(jsonArray);
		return jsonArray;
    }
    
    /**
     * 批量更新用户
     * @author zhangqing
     * @date 2013-5-13 下午03:31:44
     * @param orgid 企业ID
     * @param usernames 用户名串如：123，12，455
     * @param userpass 用户密码
     * @param proxytype 代理类型
     * @param proxyaddr 代理地址
     * @param proxyport 代理端口
     * @param proxyuser 代理登录名
     * @param proxypass 代理登录密码
     * @return 操作是否成功标识
     */
    public boolean editall(String orgid,String usernames,String userpass,String proxytype,String proxyaddr,String proxyport,String proxyuser,String proxypass){
    	String newAccounts = usernames;
    	if(newAccounts==null||newAccounts.equals("")){
    		return false;
    	}else{
    		newAccounts = newAccounts.replace(",", "','");
    	}
    	String sql = "update `user` set password_md5=md5(?),password_text=?,proxytype=?,proxyaddr=?,proxyport=?,proxyuser=?,proxypass=? where user_name in ('"+newAccounts+"') and orgid = "+orgid;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户：参数："
        			+"orgid:"+orgid+",usernames:"+usernames+",userpass:"+userpass
        			+",proxytype:"+proxytype+",proxyaddress:"+proxyaddr+",proxyport:"+proxyport
        			+"，proxyuser:"+proxyuser+",proxypass:"+proxypass
        			+",sql："+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
        	ps.setString(1, userpass);
        	ps.setString(2, userpass);
        	ps.setString(3, proxytype);
        	ps.setString(4, proxyaddr);
        	ps.setString(5, proxyport);
        	ps.setString(6, proxyuser);
        	ps.setString(7, proxypass);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量更新用户失败"+e);
            e.printStackTrace();
        } catch (Exception e) {
            SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量更新用户失败"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
    
	public ArrayList queryManagerList(String action,String orgid, String key, String account1,String account2,int firstIndex,int showNumber) {
		String sql = "";
		if(action.equals("confManager")){
	    	sql = "select id, user_name, alias, role from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') AND (user_name<>'"+account1+"' and  user_name<>'"+account2+"') AND (role='1' or role='2') and orgid="+orgid+" limit "+firstIndex+","+showNumber;
		}
		if(action.equals("userManager")){
	    	sql = "select id, user_name, alias, role from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') AND (user_name<>'"+account1+"' and  user_name<>'"+account2+"') AND (role='1' or role='3')  and orgid = "+orgid+" limit "+firstIndex+","+showNumber;
		}
		if(action.equals("uconfManager")){
	    	sql = "select id, user_name, alias, role from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') AND (user_name<>'"+account1+"' and  user_name<>'"+account2+"') AND (role='3' or role='4') and orgid = "+orgid;
		}
		else if(action.equals("uuserManager")){
	    	sql = "select id, user_name, alias, role from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') AND (user_name<>'"+account1+"' and  user_name<>'"+account2+"') AND (role='2' or role='4') and orgid = "+orgid;
		}
    	ArrayList userList = new ArrayList();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("从数据库获取管理员用户列表：参数："+"orgid:"+orgid+",key:"+key+",account1:"+account1+",account2:"+account2+",firstIndex:"+firstIndex+",showNumber:"+showNumber+",sql："+sql);
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
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从数据库获取用户列表（分页显示） 出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("从数据库获取用户列表（分页显示） 出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 	
        return userList;  
    }
	
    public int getListPage(String action,String orgid, String key, String account1,String account2,int showNumber) {
		String sql = "";
		if(action.equals("confManager")){
	    	sql = "select count(id) from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') AND (user_name<>'"+account1+"' and  user_name<>'"+account2+"') AND (role='1' or role='2') and orgid = "+orgid;
		}
		if(action.equals("userManager")){
	    	sql = "select count(id) from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') AND (user_name<>'"+account1+"' and  user_name<>'"+account2+"') AND (role='1' or role='3') and orgid = "+orgid;
		}    	
		int maxNumber = 0;
		Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取管理员用户总数：参数："+"orgid:"+orgid+",key:"+key+",account1:"+account1+",account2:"+account2+",sql："+sql);
        	ps = conn.prepareStatement(sql);  	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	while (rs.next()) {
                 maxNumber = rs.getInt("count(id)");  //总记录数
            }
        } catch (SQLException e) { 
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户总数 出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户总数 出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 
		int pageNumber = (maxNumber + showNumber - 1) / showNumber;  //分页数
        return pageNumber;  
    }
    
    /**
     * 本地用户登录验证
     * @author zhangqing
     * @date 2013-8-27 下午03:39:59
     * @param orgid 企业ID
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean login(String orgid,String username,String password){
    	String sql = "select password_md5 from user where orgid=? and user_name=?";
    	Connection conn = ConnMYSQL.getConnMYSQL();
    	try {
    		SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("本地验证用户信息：参数："+"orgid:"+orgid+",username:"+username+",password:"+password+",sql："+sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, orgid);
			ps.setString(2, username);
			rs = ps.executeQuery();
			if(rs.next()){
				if(password.equals(rs.getString("password_md5"))){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid);
		}
    	return false;
    }
    
}