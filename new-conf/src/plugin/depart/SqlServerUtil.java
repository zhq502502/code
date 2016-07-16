package plugin.depart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.seegle.data.ConnMYSQL;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

public class SqlServerUtil {
	private static Connection conn;
	public static Connection getCon(){
		if(conn==null){
			String driverName  = PropUtil.getInstance().getValue("zh.className");
			String url = PropUtil.getInstance().getValue("zh.url");
			String user = PropUtil.getInstance().getValue("zh.user");
			String password = PropUtil.getInstance().getValue("zh.password");
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("Connection Successful!"); // 如果连接成功
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			SeegleLog.getInstance().getLog(ConnMYSQL.class, orgid).error("slqserver-rs关闭失败"+e);
		}
		try {
			if(ps!=null){
    			ps.close();
    			ps = null;
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(ConnMYSQL.class, orgid).error("slqserver-ps关闭失败"+e);
		}
		try {
			if(conn!=null){
    			conn.close();
    			conn = null;
    		}
		} catch (Exception e) {
			SeegleLog.getInstance().getLog(ConnMYSQL.class, orgid).error("slqserver-conn关闭失败"+e);
		}
    }
}
