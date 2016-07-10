package com.seegle.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.form.UserInfoActionForm;
import com.seegle.util.PathUtil;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

/**
 *获取所有需要的SQL
 */
public class SQLOperation {
	private String setName = "set names utf8";
	private PreparedStatement ps = null;
	ResultSet rs = null;
	
    /**
     * 根据配置文件参数名获取操作所需要的SQL
	 **/
	public String getSQL(String operation) {
	    InputStream is = this.getClass().getResourceAsStream("allOperation.properties");
	    Properties pro = new Properties();
	    try {
	        pro.load(is);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	    	try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return pro.getProperty(operation);
	}

    /**
     * 新增用户
     */
    public boolean userAdd(String orgid,String username,String useraccount,String userpass) {
    	String sql = "insert into `user` (user_name,alias,role,name,account_email,account_mobile,account_phone,password_md5,dep_id,lastlogintime,logintag,load_balance,conf_server_ip,proxytype,proxyaddr,proxyport,proxyuser,proxypass,password_text,orgid)" +
    			"values ('"+useraccount+"', '"+username+"','4','','','','',md5('"+userpass+"'),'1',now(),'0','0','','0','','','','','"+userpass+"',orgid) ";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("添加用户：参数："+"orgid:"+orgid+",username:"+username+",useraccount:"+useraccount+",userpass:"+userpass+",sql："+sql);
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
     * 获取用户昵称
     */
    public String getUsername(String orgid, String useraccount) {
    	String sql = "select alias from `user`  where user_name = '"+useraccount+"' and orgid = "+orgid;
    	String alias = "";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户昵称：参数："+"orgid:"+orgid+"，useraccount:"+useraccount+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	rs.next();
            alias = rs.getString("alias");
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户昵称出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户昵称出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return alias;
    }
    
    /**
     * 获取用户权限
     */
    public String getUserrole(String orgid, String useraccount) {
    	String sql = "select role from `user`  where user_name = '"+useraccount+"' and orgid = "+orgid;
    	String userrole = "";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户权限：参数："+"orgid:"+orgid+"，useraccount:"+useraccount+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	while(rs.next()){
        		userrole = rs.getString("role");
        	}
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户权限出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户权限出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return userrole;
    }
    /**
     * 获得用户电话会议权限
     * @author zhangqing
     * @date 2014-3-5 下午03:47:04
     * @param orgid
     * @param useraccount
     * @return
     */
    public String getPhonetype(String orgid, String useraccount){
    	String sql = "select phonetype from `user`  where user_name = '"+useraccount+"' and orgid = "+orgid;
    	String phonetype = "";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户电话会议权限：参数："+"orgid:"+orgid+"，useraccount:"+useraccount+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	while(rs.next()){
        		phonetype = rs.getString("phonetype");
        	}
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户电话会议权限出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户电话会议权限出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return phonetype;
    }

    /**
     * 获取用户总数
     */
    public int getUserCount(String orgid, String key) {
    	String sql = "select count(id) from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') and orgid = "+orgid;
    	int maxNumber = 0;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户总数：参数："+"orgid:"+orgid+"，key:"+key+",sql:"+sql);
        	ps = conn.prepareStatement(sql);  	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	while (rs.next()) {
                 maxNumber = rs.getInt("count(id)");  //总记录数
            }
        } catch (SQLException e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户总数出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户总数出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 
        return maxNumber;  
    }
    
    /**
     * 获取用户列表 （不去掉admin和自身）
     */
    public ArrayList getUserlist(String orgid, String key, String account,int firstIndex,int showNumber) {
    	String sql = "select id, user_name, alias, role from `user` where (user_name like '%"+key+"%' or alias like '%"+key+"%') and orgid="+orgid+" limit "+firstIndex+","+showNumber;
    	String sql1 = "select id, user_name, alias, role from `user` where orgid="+orgid+" limit "+firstIndex+","+showNumber;
    	ArrayList userList = new ArrayList();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	if(key.equals("")){
        		SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户列表：参数："+"orgid:"+orgid+"，key:"+key+"，account:"+account+"，firstIndex:"+firstIndex+"，showNumber:"+showNumber+",sql:"+sql1);
        		ps = conn.prepareStatement(sql1);
        	}else{
        		SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户列表：参数："+"orgid:"+orgid+"，key:"+key+"，account:"+account+"，firstIndex:"+firstIndex+"，showNumber:"+showNumber+",sql:"+sql);
        		ps = conn.prepareStatement(sql);
        	}      	
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
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户列表出错"+e);
            e.printStackTrace();  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户列表出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return userList;  
    }
 
    /**
     * 获取单个用户信息
     */
    public UserInfoActionForm getSingleUser(String orgid,String account) {
    	String sql = "select id, user_name, alias from `user` where user_name = '"+account+"' and orgid = "+orgid;
    	 UserInfoActionForm uif = new UserInfoActionForm();
    	 Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取单个用户信息：参数："+"orgid:"+orgid+"，account:"+account+",sql:"+sql);
        	ps = conn.prepareStatement(sql);    	
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
            if(rs.next()){
            	uif.setUserId(rs.getInt("id"));
            	uif.setUser_name(rs.getString("user_name"));
            	uif.setAlias(rs.getString("alias"));
            	uif.setPassword_md5("Itispass");
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
     * 获取用户密码
     */
    public String getUserpass(String orgid, String useraccount) {
    	String sql = "select password_text from `user`  where user_name = '"+useraccount+"' and orgid = "+orgid;
    	String password_text = "";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取用户密码：参数："+"orgid:"+orgid+"，useraccount:"+useraccount+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	rs.next();
        	password_text = rs.getString("password_text");
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户密码出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取用户密码出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        } 
        return password_text;
    }

    /**
     * 更新用户昵称
     */
    public boolean updateUsername(String orgid,String username,String useraccount) {
    	String sql = "update `user` set alias = '"+username+"' where user_name = '"+useraccount+"' and orgid = "+orgid;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户昵称：参数："+"orgid:"+orgid+"，username:"+username+"，useraccount:"+useraccount+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();  
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新用户昵称出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新用户昵称出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }

    /**
     * 更新企业logo
     */
    public boolean updateOrglogo(String orgid,String filename) {
    	String sql = "update company set orglogo = '"+filename+"' where orgid = '"+orgid+"' ";
    	String sql_logo_query = "select orglogo from company where orgid = '"+orgid+"' ";
    	String filename_ = null;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新企业log：参数："+"orgid:"+orgid+"，filename:"+filename+",sql:"+sql);
        	ps = conn.prepareStatement(sql_logo_query);
        	rs = ps.executeQuery();
        	if(rs.next()){
        		filename_ = rs.getString("orglogo");
        	}
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate(); 
            //如果之前文件存在则删除之前的文件
            String fileRealPath = PathUtil.getProjectPath()+"/"+PropUtil.getInstance().getValue("logo.path")+"/"+orgid+"/"+filename_;
            File realFile = new File(fileRealPath);
            if(realFile.exists()){
            	realFile.delete();
            }
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新企业log出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新企业log出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
    /**
     * 更新企业log原文件
     * @author zhangqing
     * @date 2013-7-5 下午01:38:42
     * @param orgid
     * @param fileByts
     * @return
     */
    public boolean updateOrgLogoBlob(String orgid,InputStream fileByts){
    	String sql = "update company set orglogo_blob = ? where orgid = '"+orgid+"' ";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新企业log：参数："+"orgid:"+orgid+"，filename:"+fileByts+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.setBinaryStream(1, fileByts);
        	ps.executeQuery(setName);
            ps.executeUpdate();             
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新企业log_blob出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新企业log_blob出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }

    /**
     * 获取企业logo
     */
    public String getOrglogo(String orgid) {
    	    	
    	String sql = "select orglogo,orglogo_blob from company where orgid = '"+orgid+"' ";
    	String orglogo = "";
    	InputStream inputStream = null;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获得企业log：参数："+"orgid:"+orgid+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	if(rs.next()){
        		orglogo = rs.getString("orglogo");
        		inputStream = rs.getBinaryStream("orglogo_blob");
        	}else{
        		return orglogo;
        	}
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取企业log出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取企业log出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        
        String filepath = "";
        File imgFile = null;
        File imgPath = null;
        try{
        	filepath = PathUtil.getProjectPath()+"/"+PropUtil.getInstance().getValue("logo.path")+"/"+orgid+"/"+orglogo;
        	imgFile = new File(filepath);
        	imgPath = new File(PathUtil.getProjectPath()+"/"+PropUtil.getInstance().getValue("logo.path")+"/"+orgid+"/");
        	if(!imgFile.exists()){
        		if(inputStream==null){
        			return null;
        		}else{
        			imgPath.mkdir();
        			imgFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(filepath); 
					byte[] buffer = new byte[1];           
					while (inputStream.read(buffer) > 0) {
					    fos.write(buffer);
					}
					fos.flush();
					fos.close(); 
					inputStream.close();
					SeegleLog.getInstance().getLog(this.getClass(), orgid).info("将数据库中图片写入文件成功"+filepath);
        		}
        	}
        }catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取log图片信息出错"+e);
		}
        return orglogo;
    }
    
    
    
    /**
     * 设置或取消用户管理员
     */
    public boolean changeUserrole(String orgid,String useraccount,String operation) {
    	String sql = "";
    	String role = "";
    	String newAccounts[] = useraccount.split(",");
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
            for(int i=0;i<newAccounts.length;i++){
            	int real = Integer.parseInt(this.getUserrole(orgid, newAccounts[i]));
            	if(operation.equals("add")&&(real==2||real==4)){
            		role = String.valueOf(real-1);
                	sql = "update `user` set role = '"+role+"' where user_name = '"+newAccounts[i]+"' and orgid = "+orgid;
            	}
            	if(operation.equals("cancel")&&(real==1||real==3)){ 
            		role = String.valueOf(real+1);
            		sql = "update `user` set role = '"+role+"' where user_name = '"+newAccounts[i]+"' and orgid = "+orgid;
            	}
            	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("设置或取消用户管理员：参数："+"orgid:"+orgid+"，useraccount:"+useraccount+"，operation:"+operation+",sql:"+sql);
            	ps = conn.prepareStatement(sql);
            	ps.executeQuery(setName);
                ps.executeUpdate();
            }
            	return true; 
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("设置或取消管理员出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
            SeegleLog.getInstance().getLog(this.getClass(), orgid).error("设置或取消管理员出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
    
    
    /**
     * 更新用户类型
     */
    public boolean updateUserrole(String orgid,String useraccount,String role) {
    	String sql = "update `user` set role = '"+role+"' where user_name = '"+useraccount+"' and orgid = "+orgid;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
        try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户类型：参数："+"orgid:"+orgid+"，useraccount:"+useraccount+"，role:"+role+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            if(ps.getUpdateCount()==1){
            	return true;
            }  
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更改用户类型出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更改用户类型出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
    /**
     * 更新用户密码
     */
    public boolean updateUserpass(String orgid,String npass,String useraccount) {
    	String sql = "update `user` set password_md5 = md5('"+npass+"'),password_text = '"+npass+"' where user_name = '"+useraccount+"' and orgid = "+orgid;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户密码：参数："+"orgid:"+orgid+"，useraccount:"+useraccount+"，npass:"+npass+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更改用户密码出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更改用户密码出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }

    /**
     * 更新用户信息
     */
    public boolean userMod(String orgid,String alias,String account,String userpass) {
    	String sql = "";
    	if(userpass.equals("Itispass")){
        	sql = "update `user` set alias = '"+alias+"' where user_name = '"+account+"' and orgid = "+orgid;
    	}else{
        	sql = "update `user` set alias = '"+alias+"',password_md5 = md5('"+userpass+"'),password_text = '"+userpass+"' where user_name = '"+account+"' and orgid = "+orgid;
    	}
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("更新用户信息：参数："+"orgid:"+orgid+"，account:"+account+"，alias:"+alias+"，userpass:"+userpass+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更改用户信息出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更改用户信息出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
    
    /*
     * 用户登录验证
     */
    public boolean userLogin(String orgid,String userid,String userpass){
    	boolean state = false;
    	String sql="SELECT user_name,password_md5,alias FROM user WHERE user_name='"+userid+"' and orgid = "+orgid;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("用户登录验证：参数："+"orgid:"+orgid+"，userid:"+userid+"，userpass:"+userpass+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
        	rs = ps.executeQuery();
        	if (rs.next()) {//如果用户存在 验证密码是否正确
        		state = rs.getString(2).equals(userpass);//密码正确返回true 
        	}
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("用户登录验证出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("用户登录验证出错"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
    	return state;
    }
    /**
     * 删除用户
     * @param orgid 企业ID
     * @param account 用户帐号
     * @return
     */
    public boolean userDel(String orgid,String account) {
    	String sql = "delete from `user` where user_name = '"+account+"' and orgid = "+orgid;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("删除用户：参数："+"orgid:"+orgid+"，account:"+account+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("删除用户出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("删除用户出错"+e);
            e.printStackTrace();
        } finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
    
    /**
     * 批量删除用户用户
     * @author zhangqing
     * @date 2013-5-10 上午10:52:19
     * @param orgid
     * @param accounts
     * @return
     */
    public boolean userDelall(String orgid,String accounts){
    	String newAccounts = accounts;
    	if(newAccounts==null||newAccounts.equals("")){
    		return false;
    	}else{
    		newAccounts = newAccounts.replace(",", "','");
    	}
    	String sql = "delete from `user` where user_name in ('"+newAccounts+"') and orgid = "+orgid;
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("批量删除用户：参数："+"orgid:"+orgid+"，accounts:"+accounts+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量删除用户失败"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("批量删除用户失败"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
        return false;
    }
    
    /**
     * 获得用户邮箱列表，只查询存在邮箱的用户
     * @author zhangqing
     * @date 2013-5-7 下午03:52:17
     * @param orgid
     * @return
     */
    @SuppressWarnings("unchecked")
	public JSONArray getUserEmails(String orgid){
    	String sql = "select user_name,alias,account_email from `user` where account_email <> '' and orgid = "+orgid;
    	JSONArray jsonArray = new JSONArray();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获得用户邮箱列表：参数："+"orgid:"+orgid+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	rs = ps.executeQuery();
        	while(rs.next()){
        		JSONObject obj = new JSONObject();
        		obj.put("username", rs.getObject("user_name").toString());
        		obj.put("alias", rs.getObject("alias").toString());
        		obj.put("email", rs.getObject("account_email").toString());
        		jsonArray.add(obj);
        	}
        	return jsonArray;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("查询用户邮箱失败"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("查询用户邮箱失败"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
    	return null;
    }
    
    /**
     * 获得邮件配置对象
     * @author zhangqing
     * @date 2013-5-8 下午06:38:45
     * @param orgid 企业ID
     * @return
     */
    public Map<String,String> emailConfig(String orgid){
    	String sql = "select emailhost,emailloginname,emailloginpwd,emailport,emailalias from company where orgid = ?";
    	Map<String,String> map = new HashMap<String, String>();
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获得邮箱配置信息：参数："+"orgid:"+orgid+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, orgid);
        	rs = ps.executeQuery();
        	while(rs.next()){
        		map.put("emailhost", rs.getString("emailhost"));
        		map.put("emailloginname", rs.getString("emailloginname"));
        		map.put("emailloginpwd", rs.getString("emailloginpwd"));
        		map.put("emailport", rs.getString("emailport"));
        		map.put("emailalias", rs.getString("emailalias"));
        	}
        	return map;
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("查询用户邮箱失败："+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("查询用户邮箱失败："+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
    	return map;
    }
    
    /**
     * 保存或更新邮件配置
     * @author zhangqing
     * @date 2013-5-8 下午06:05:51
     * @param orgid 企业ID
     * @param emailhost 邮件认证服务器
     * @param emailloginname 邮件登录账户
     * @param emailloginpwd 邮件登录密码
     * @param emailport 认证服务器端口
     * @return
     */
    public boolean savaOrUpdateEmailConfig(String orgid,String orgname,String emailhost,String emailloginname,String emailloginpwd,String emailport,String emailalias){
    	String sql = "select * from `company` where orgid=?";
    	String saveOrUpdate = "";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug(orgid+"获取企业信息sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, orgid);
        	rs = ps.executeQuery();
        	if(rs.next()){
        		saveOrUpdate = "update company set emailhost=?,emailloginname=?,emailloginpwd=?,emailport=?,emailalias=? where orgid = ?";
        	}else{
        		saveOrUpdate = "insert into company(emailhost,emailloginname,emailloginpwd,emailport,emailalias,orgid) values(?,?,?,?,?,?)";
        		if(orgname!=null){
        			saveOrUpdate = "insert into company(orgname,emailhost,emailloginname,emailloginpwd,emailport,emailalias,orgid) values('"+orgname+"',?,?,?,?,?,?)";
        		}
        	}
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("保存或更新邮件配置：参数："+"orgid:"+orgid+"，orgname:"+orgname+"，emailhost:"+emailhost+"，emailloginname:"+emailloginname+"，emailloginpwd:"+emailloginpwd+"，emailport:"+emailport+"，emailalias:"+emailalias+",sql:"+saveOrUpdate);
        	ps = conn.prepareStatement(saveOrUpdate);
        	ps.executeQuery(setName);
        	ps.setString(1, emailhost);
        	ps.setString(2, emailloginname);
        	ps.setString(3, emailloginpwd);
        	ps.setString(4, emailport);
        	ps.setString(5, emailalias);
        	ps.setString(6, orgid);
        	if(ps.executeUpdate()>0){
        		return true;
        	}
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("保存或更新邮箱配置失败"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("保存或更新邮箱配置失败"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
    	return false;
    }
    /**
     * 根据企业Id获得其会议列表排序方式
     * @author zhangqing
     * @date 2013-5-22 上午10:44:51
     * @param orgid 企业ID
     * @return map中的键有两个，ordername和ordertype。排序名称和排序方式
     */
    public Map<String,String> getConfOrder(String orgid){
    	Map<String,String> map = new HashMap<String, String>();
    	String sql = "select conf_order_name,conf_order_type from company where orgid=?";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("获取会议排序方式：参数："+"orgid:"+orgid+",sql:"+sql);
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, orgid);
        	rs = ps.executeQuery();
        	if(rs.next()){
        		map.put("ordername", rs.getString("conf_order_name")==null?PropUtil.getInstance().getValue("conforder.defualname"):rs.getString("conf_order_name"));
        		map.put("ordertype", rs.getString("conf_order_type")==null?PropUtil.getInstance().getValue("conforder.defualtype"):rs.getString("conf_order_type"));
        	}else{
        		map.put("ordername", PropUtil.getInstance().getValue("conforder.defualname"));
        		map.put("ordertype", PropUtil.getInstance().getValue("conforder.defualtype"));
        	}
    	}catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("根据企业Id获得其会议列表排序方式出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("根据企业Id获得其会议列表排序方式出错"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
    	return map;
    }
    
    /**
     * 保存或更新会议列表的排序方式
     * @author zhangqing
     * @date 2013-5-22 上午11:17:36
     * @param orgid
     * @param orderanme
     * @param ordertype
     * @return
     */
    public boolean saveOrUpdateConfOrder(String orgid,String ordername,String ordertype,String tip){
    	String saveOrUpdate = "update company set conf_order_name=?,conf_order_type=?,confuser_tip=? where orgid=?";
    	String sql = "select * from `company` where orgid=?";
    	Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
    	try{
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, orgid);
    		rs = ps.executeQuery();
    		if(!rs.next()){
    			saveOrUpdate = "insert into company(orgname,conf_order_name,conf_order_type,confuser_tip,orgid) values('',?,?,?,?)";
    		}
    		SeegleLog.getInstance().getLog(this.getClass(), orgid).debug("保存或更新会议排序方式及并发数提示：参数："+"orgid:"+orgid+",ordername:"+ordername+",ordertype:"+ordertype+",confuser_tip:"+tip+",sql:"+saveOrUpdate);
    		ps = conn.prepareStatement(saveOrUpdate);
    		ps.executeQuery(setName);
    		ps.setString(1, ordername);
    		ps.setString(2, ordertype);
    		ps.setString(3, tip);
    		ps.setString(4, orgid);
    		if(ps.executeUpdate()>0){
    			return true;
    		}
    	}catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("保存或更新会议列表的排序方式及并发数提示出错"+e);
            e.printStackTrace();
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("保存或更新会议列表的排序方式出错"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
    	return false;
    }
    
    /**
     * 测试
     */
//    public static void main(String[] args) {
//    	SQLOperation so = new SQLOperation();
//    	String orgid = "90122";
//    	boolean a = so.createUsertable(orgid);
//    	System.out.println(orgid+"_user表创建"+a);
//    	System.out.println("----------------------");
//    	String insertsql = "INSERT INTO "+orgid+"_user VALUES('1000','admin','Administrator','','','','','21232f297a57a5a743894a0e4a801fc3','1','0000-00-00 00:00:00','0','0','0','','0','','','','','admin')";
//    	boolean b = so.insertSQL(insertsql);
//    	System.out.println("插入数据"+b);
//    	System.out.println("----------------------");
//    }
}
