package plugin.depart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.seegle.data.ConnMYSQL;
import com.seegle.util.PropUtil;
import com.seegle.util.SeegleLog;

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
	private String insertdepart = "insert into department(id,departnum,departpnum,departname,orders,orgid,sys) values(?,?,?,?,?,"+orgid+",1)";
	/**更新本地库的组织架构*/
	private String updatedepart = "update department set departname=?,departpnum=?,orders=? where id=?";
	/**删除本地库的组织架构*/
	private String deletedepart = "delete department where sys=1 and departpnum not in(%s)";
	
	/**查询本地库的用户*/
	private String selectuser = "select * from user where user_name=?";
	/**插入本地库的用户*/
	private String insertuser = "insert into user(user_name,alias,role,password_md5,departid,account_mobile,account_email,lastlogintime,logintag,orders,orgid,sys,proxyaddr,proxyport,proxyuser,proxypass) values(?,?,4,?,?,?,?,'2000-12-12 12:12:12',1,?,"+orgid+",1,'','','','')";
	/**更新本地库的用户*/
	private String updateuser = "update user set alias=?,departid=?,account_mobile=?,account_email=?,orders=? where user_name=?";
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
		Logger log = SeegleLog.getInstance().getLog(this.getClass(), orgid);
		for(;;){
			log.info("开始同步");
			Connection myconn = ConnMYSQL.getConnMYSQL();
			Connection sqlconn = SqlServerUtil.getCon();
			PreparedStatement myps = null;
			ResultSet myrs = null;
			PreparedStatement sqlps = null;
			ResultSet sqlrs = null;
			int countInsertDepart=0;
			int countUpdateDepart=0;
			int countInsertUser=0;
			int countUpdateUser=0;
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
						String m_departname = myrs.getString("departname");
						int m_departpnum = myrs.getInt("departpnum");
						int m_deptorders = myrs.getInt("orders");
						if(departname.equals("人力资源部1")){
							System.out.println(departname);
						}
						if(!(isEq(departname,m_departname)&&(departpnum==m_departpnum)&&(deptorders==m_deptorders))){
							//有字段更新
							myps = myconn.prepareStatement(updatedepart);
							myps.setString(1, departname);
							myps.setInt(2, departpnum);
							myps.setInt(3, deptorders);
							myps.setInt(4, departid);
							myps.executeQuery(setName);
							myps.executeUpdate();
							countUpdateDepart++;
							log.info("更新部门:"+departname);
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
						countInsertDepart++;
						log.info("添加部门:"+departname);
					}
				}
				//删除用户库中没有的组织架构
				
				/**
				 * 遍历用户
				 */
				sqlps = sqlconn.prepareStatement(sql_selectuser);
				sqlrs = sqlps.executeQuery();
				String users = "";
				while(sqlrs.next()){
					try{
					
					String account = sqlrs.getString("account");
					users+=",'"+account+"'";
					String alias = sqlrs.getString("name");
					int departid = sqlrs.getInt("departnum");
					int orders = 0;
					try{
						orders = sqlrs.getInt("orders");
					}catch(Exception e1){
						log.info("用户同步出错:"+account);
					}
					String tel = sqlrs.getString("tel");
					String email = sqlrs.getString("email");
					String password = sqlrs.getString("password");
					
					myps = myconn.prepareStatement(selectuser);
					myps.setString(1, account);
					myrs = myps.executeQuery();
					if(myrs.next()){
						/*if(alias.equals("肖光宁1")){
							System.out.println(alias);
						}*/
						int m_departid = myrs.getInt("departid");
						String m_user_name = myrs.getString("user_name");
						String m_alias = myrs.getString("alias");
						int m_orders = myrs.getInt("orders");
						String m_email = myrs.getString("account_email");
						log.info("服务器用户邮箱："+email+",本地用户邮箱："+m_email);
						if(!(isEq(alias,m_alias)&&(departid==m_departid)&&(orders==m_orders)&&(isEq(email,m_email)))){
							//有字段更新
							myps = myconn.prepareStatement(updateuser);
							myps.setString(1, alias);
							myps.setInt(2, departid);
							myps.setString(3, tel);
							myps.setString(4, email);
							myps.setInt(5, orders);
							myps.setString(6, account);
							myps.executeQuery(setName);
							myps.executeUpdate();
							countUpdateUser++;
							log.info("更新用户:"+account+","+alias);
						}
					}else{
						//没有字段更新新增一条记录
						myps = myconn.prepareStatement(insertuser);
						myps.setString(1, account);
						myps.setString(2, alias);
						myps.setString(3, password);
						myps.setInt(4, departid);
						myps.setString(5, tel);
						myps.setString(6, email);
						myps.setInt(7, orders);
						myps.executeQuery(setName);
						myps.executeUpdate();
						countInsertUser++;
						log.info("添加用户:"+account+","+alias);
					}
				}
				catch (Exception e2) {
					
				}
			}
				
				//删除用户库中没有的用户
				
				
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			log.info("===== 数据同步信息======");
			log.info("本次同步更新部门:"+countUpdateDepart);
			log.info("本次同步新建部门:"+countInsertDepart);
			log.info("本次同步更新用户:"+countUpdateUser);
			log.info("本次同步新建用户:"+countInsertUser);
			
			
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
