package com.seegle.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seegle.util.SeegleLog;

/**
 *企业操作类
 **/
public class CompanyOperation {
	private String setName = "set names utf8";
	private SQLOperation sqlOperation = new SQLOperation();//读取sql
	
	/**
	 * 判断company表中是否存在此companyID
	 */	
	public boolean existCompany(String orgid){
		Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
		boolean state = false;
		PreparedStatement ps = null;
        ResultSet rs = null;
		String sql = sqlOperation.getSQL("selectCompany");
		SeegleLog.getInstance().getLog(this.getClass(), orgid).error("判断company表中是否存在此companyID:sql"+sql);
		try {
			ps = conn.prepareStatement(sql);
            ps.setString(1, orgid);
            ps.executeQuery(setName);
            rs = ps.executeQuery();            
            if (rs.next()) {
                state = true;
            }
		}catch (SQLException e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("判断company表中是否存在此companyID出错"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
		
		return state;
	}
	
	/**
	 * 向company表中添加一个company
	 */
	public boolean addCompany(String orgid,String orgname){
		Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
		PreparedStatement ps = null;
		String sql = sqlOperation.getSQL("addCompany");
		SeegleLog.getInstance().getLog(this.getClass(), orgid).error("向company表中添加一个company:sql"+sql);
		try {      	
        	ps = conn.prepareStatement(sql);
        	ps.setString(1, orgid);
        	ps.setString(2, orgname);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;  
        } catch (SQLException e) {
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("向company表中添加一个company出错"+e);
            e.printStackTrace();  
        }finally{
        	ConnMYSQL.closeResources(null, ps, conn, orgid);
        }
		return false; 
	}
	
	/**
	 * 获取company表中的企业名称
	 */	
	public String getCompanyName(String orgid){
		Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
		String orgname = "";
		PreparedStatement ps = null;
        ResultSet rs = null;
		String sql = sqlOperation.getSQL("selectCompany");
		SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取company表中的企业名称:sql"+sql);
		try {
			ps = conn.prepareStatement(sql);
            ps.setString(1, orgid);
            ps.executeQuery(setName);
            rs = ps.executeQuery();            
            if (rs.next()) {
            	orgname = rs.getString("orgname");
            }
		}catch (SQLException e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取company表中的企业名称出错"+e);
            e.printStackTrace();
        }finally{
        	ConnMYSQL.closeResources(rs, ps, conn, orgid);
        }
		
		return orgname;
	}
	
	/**
     * 向数据库中添加***_user表
     */
    public boolean createUsertable(String orgid) {
    	/*Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
		PreparedStatement ps = null;
    	String createsql = "CREATE TABLE `"+orgid+"_user` ";
    	createsql += sqlOperation.getSQL("createUserTable");
    	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("向数据库中添加"+orgid+"_user表:sql"+createsql);
        try {      	
        	ps = conn.prepareStatement(createsql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("向数据库中添加"+orgid+"_user表出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(null, ps, conn, orgid);
        }*/
        return false;  
    }
    
    /**
     * 向数据库中添加***_file_info表
     */
    public boolean createFiletable(String orgid) {
    	/*Connection conn = ConnMYSQL.getConnMYSQL();//获取连接
		PreparedStatement ps = null;
    	String createsql = "CREATE TABLE `"+orgid+"_file_info` ";
    	createsql += sqlOperation.getSQL("createFileTable");
    	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("创建"+orgid+"file_info表时:sql"+createsql);
        try {      	
        	ps = conn.prepareStatement(createsql);
        	ps.executeQuery(setName);
            ps.executeUpdate();
            return true;  
        } catch (Exception e) {  
        	SeegleLog.getInstance().getLog(this.getClass(), orgid).error("创建"+orgid+"file_info表时出错"+e);
            e.printStackTrace();  
        } finally{
        	ConnMYSQL.closeResources(null, ps, conn, orgid);
        }*/
        return false;  
    }
    
}
