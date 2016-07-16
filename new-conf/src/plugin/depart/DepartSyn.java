package plugin.depart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seegle.data.ConnMYSQL;
import com.seegle.util.PropUtil;

public class DepartSyn extends Thread {
	//同步时间设置
	private int hour;//时
	private int min;//分
	private int sec;//秒
	private String orgid = PropUtil.getInstance().getValue("orgid");
	private String setName = "set names utf8";	
	
	/**查询sqlserver库的组织架构*/
	private String sql_selectdepart = "select * from v_dept";
	/**查询sqlserver库的用户*/
	private String sql_selectuser = "select * from v_user";
	/**查询本地库的组织架构*/
	private String selectdepart = "select * from department where id=?";
	/**插入本地库的组织架构*/
	private String insertdepart = "insert into department(id,departnum,departpnum,departname,orders,sys) values(?,?,?,?,orgid,1)";
	/**更新本地库的组织架构*/
	private String updatedepart = "update department set departname=?,departpnum=?,orders=? where id=?";
	/**删除本地库的组织架构*/
	private String deletedepart = "delete department where sys=1 and departpnum not in(%s)";
	/**查询本地库的用户*/
	private String selectuser = "select * from user where user_name=?";
	/**插入本地库的用户*/
	private String insertuser = "insert into user(user_name,alias,role,password_md5,dep_id,account_mobile,account_email,lastlogintime,logintag,orders,orgid,sys) values(?,?,4,?,?,'2000-12-12 12:12:12',1,1)";
	/**更新本地库的用户*/
	private String updateuser = "update user set alias=?,dep_id=?,account_mobile=?,account_email=?,orders";
	/**删除本地库的用户*/
	private String deleteuser = "delete from user where sys=1 and user_name not in(%s)";
	
	
	public DepartSyn(){
		String time = PropUtil.getInstance().getValue("zh.syntime");
		String times[] = time.split(":");
		hour = Integer.parseInt(times[0]);
		min = Integer.parseInt(times[1]);
		sec = Integer.parseInt(times[2]);
	}
	@SuppressWarnings({ "static-access", "resource" })
	@Override
	public void run() {
		if(orgid==null||"".equals(orgid)){
			System.out.println("数据同步未开始，企业ID未配置，请在config.properties文件中配置");
			return;
		}
		for(;;){
			
			Connection myconn = ConnMYSQL.getConnMYSQL();
			Connection sqlconn = SqlServerUtil.getCon();
			PreparedStatement myps = null;
			ResultSet myrs = null;
			PreparedStatement sqlps = null;
			ResultSet sqlrs = null;
			/**
			 * 1、查询sqlsql数据库数据
			 * 2、遍历循环查询本地数据库数据对比有差异就更新
			 */
			try {
				/**
				 * 遍历组织架构
				 */
				sqlps = sqlconn.prepareStatement(sql_selectdepart);
				sqlrs = sqlps.executeQuery();
				String departs = "";
				while(sqlrs.next()){
					int departid = sqlrs.getInt("departnum");
					departs+=","+departid;
					String departname = sqlrs.getString("departname");
					int departpnum = sqlrs.getInt("departpnum");
					int deptorders = sqlrs.getInt("deptorders");
					
					myps = myconn.prepareStatement(selectdepart);
					myps.setInt(1, departid);
					myrs = myps.executeQuery();
					if(myrs.next()){
						int m_departid = myrs.getInt("departnum");
						String m_departname = sqlrs.getString("departname");
						int m_departpnum = sqlrs.getInt("departpnum");
						int m_deptorders = sqlrs.getInt("deptorders");
						if(!(isEq(departname,m_departname)&&(departpnum==m_departpnum)&&(deptorders==m_deptorders))){
							//有字段更新
							myps = myconn.prepareStatement(updatedepart);
							myps.setString(1, departname);
							myps.setInt(2, departpnum);
							myps.setInt(3, deptorders);
							myps.setInt(4, departpnum);
							myps.executeQuery(setName);
							myps.executeUpdate();
							
						}
					}else{
						//没有字段更新新增一条记录
						myps = myconn.prepareStatement(insertdepart);
						myps.setInt(1, departid);
						myps.setInt(2, departid);
						myps.setInt(3, departpnum);
						myps.setString(4, departname);
						myps.setInt(5, deptorders);
						myps.executeQuery(setName);
						myps.executeUpdate();
					}
				}
				
				/**
				 * 遍历用户
				 */
				
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			
			
			
			ConnMYSQL.closeResources(myrs, myps, myconn, orgid);
			//获取下次线程执行时间
			Calendar now = Calendar.getInstance();
			Calendar next = Calendar.getInstance();
			next.set(Calendar.HOUR_OF_DAY,hour);
			next.set(Calendar.MINUTE,min);
			next.set(Calendar.SECOND,sec);
			if(next.getTimeInMillis()<now.getTimeInMillis()){
				next.set(Calendar.DAY_OF_MONTH,next.get(Calendar.DAY_OF_MONTH)+1);
			}
			long sleepTime = next.getTimeInMillis()-now.getTimeInMillis();
			try {
				this.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isEq(String str1,String str2){
		if(str1==null||str2==null){
			if(str1==null&&str2==null){
				return true;
			}else{
				return false;
			}
		}else{
			return str1.equals(str2);
		}
	}
	
}
