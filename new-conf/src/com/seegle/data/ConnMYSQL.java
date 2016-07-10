package com.seegle.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.seegle.util.SeegleLog;

/**
 *获取MYSQL连接
 */
public class ConnMYSQL {

    private static Connection conn;
    private static String[] info;
    private ConnMYSQL() {
    	
    }
    static{
    	ReadConfigFile readInfo = new ReadConfigFile();
    	info = readInfo.getDatabaseInfo();
    	try {
			Class.forName(info[0]);
		} catch (ClassNotFoundException e) {
			SeegleLog.getInstance().getLog(ConnMYSQL.class, "default").error("mysql驱动类未找到"+e);
			e.printStackTrace();
		}
    }
    
    public static Connection getConnMYSQL() {
        try {
           conn = DriverManager.getConnection(info[1], info[2], info[3]);
        } catch (Exception e) {
        	SeegleLog.getInstance().getLog(ConnMYSQL.class, "default").error("获取数据库链接失败"+e);
            e.printStackTrace();
        }
        return conn;
    }
    
    /**
     * 关闭资源
     * @author zhangqing
     * @date 2013-6-17 上午09:17:18
     * @param rs
     * @param ps
     * @param conn
     * @param orgid
     */
    public static void closeResources(ResultSet rs,PreparedStatement ps,Connection conn,String orgid){
    	try {
    		if(rs!=null){
    			rs.close();
    			rs = null;
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(ConnMYSQL.class, orgid).error("rs关闭失败"+e);
		}
		try {
			if(ps!=null){
    			ps.close();
    			ps = null;
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(ConnMYSQL.class, orgid).error("ps关闭失败"+e);
		}
		try {
			if(conn!=null){
    			conn.close();
    			conn = null;
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(ConnMYSQL.class, orgid).error("conn关闭失败"+e);
		}
    }
    
    //测试连接数据库
//	  public static void main(String[] args) {
//	      ConnMYSQL c = new ConnMYSQL();
//	      System.out.println(c.getConnMYSQL().toString());
//	  }
}
