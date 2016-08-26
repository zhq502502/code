package greendog.webtask.util;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * 数据库链接
 * @author 张青
 * @date 2013-4-26 上午10:20:08
 */
public class Connection {

	private static Connection con;
	private java.sql.Connection sql_con;
	private String url="";
	private String username = "";
	private String password = "";
	private String drivename = "";
	private Connection(){
		try {
			url = TaskPropUtil.getInstance().getValue("db.url");
			username = TaskPropUtil.getInstance().getValue("db.user");
			password = TaskPropUtil.getInstance().getValue("db.password");
			drivename = TaskPropUtil.getInstance().getValue("db.driver");
			Class.forName(drivename);
			sql_con = java.sql.DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("未找到"+drivename+"驱动类");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库链接初始化异常");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("获得数据库配置出错");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库链实例
	 * @author 张青
	 * @date 2013-4-26 上午10:24:04
	 * @return
	 */
	public static Connection getInstance(){
		if(con==null){
			con = new Connection();
		}
		return con;
	}
	
	/**
	 * 获得数据库链接
	 * @author 张青
	 * @date 2013-4-26 上午10:37:30
	 * @return
	 */
	public java.sql.Connection getConnection(){
		return sql_con;
	}
	
	/**
	 * 关闭数据库链接
	 * @author 张青
	 * @date 2013-4-26 上午10:39:36
	 */
	public void closeConnection(){
		try {
			sql_con.close();
		} catch (SQLException e) {
			System.out.println("未能成功关闭数据库链接");
			e.printStackTrace();
		}
	}
	
	public static void main(String arg[]){
		new Connection();
	}
}