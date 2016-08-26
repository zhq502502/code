package greendog.webtask.dao;

import greendog.webtask.beans.TaskHost;
import greendog.webtask.beans.TaskUrl;
import greendog.webtask.beans.TaskUser;
import greendog.webtask.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Dao
 * @author zhangqing
 * @date 2013-5-21 下午04:56:41
 */
public class Dao {
	Connection con;
	public Dao(){
		con = greendog.webtask.util.Connection.getInstance().getConnection();
	}
	/**
	 * 获得任务列表
	 * @author zhangqing
	 * @date 2013-5-21 下午04:56:16
	 * @return
	 */
	public List<TaskHost> getTaskHost(){
		List<TaskHost> hostList = new ArrayList<TaskHost>();
		String sql = "select * from task_host where flag = "+Constants.FLAG_ON;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				TaskHost host = new TaskHost();
				host.setDescribe(rs.getString("describe"));
				host.setFlag(rs.getInt("flag"));
				host.setHost(rs.getString("host"));
				host.setType(rs.getString("type"));
				host.setId(rs.getInt("id"));
				host.setPort(rs.getInt("port"));
				host.setRuncycle(rs.getString("runcycle"));
				host.setRuntime(rs.getDate("runtime"));
				host.setRuntype(rs.getString("runtype"));
				hostList.add(host);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hostList;
	}
	/**
	 * 根据任务ID获得所有的任务地址
	 * @author zhangqing
	 * @date 2013-5-21 下午05:05:50
	 * @param hostId
	 * @return
	 */
	public List<TaskUrl> getTaskUrlByHostID(int hostId){
		List<TaskUrl> list = new ArrayList<TaskUrl>();
		String sql = "select * from task_url where host_id = ? order by sorting asc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, hostId);
			rs = ps.executeQuery();
			while(rs.next()){
				TaskUrl url = new TaskUrl();
				url.setHostId(rs.getInt("host_id"));
				url.setId(rs.getInt("id"));
				url.setMethodType(rs.getString("method_type"));
				url.setSorting(rs.getInt("sorting"));
				url.setUrl(rs.getString("url"));
				list.add(url);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 获得任务下所有用户
	 * @author zhangqing
	 * @date 2013-5-21 下午05:02:20
	 * @param host
	 * @return
	 */
	public List<TaskUser> getUsersByHost(TaskHost host,List<TaskUrl> urlList){
		List<TaskUser> list = new ArrayList<TaskUser>();
		String sql = "select * from task_user where host_id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, host.getId());
			rs = ps.executeQuery();
			while(rs.next()){
				TaskUser user = new TaskUser();
				user.setHost(host);
				user.setTaskUrl(urlList);
				user.setId(rs.getInt("id"));
				user.setLoginNameName(rs.getString("loginname_name"));
				user.setLoginNameValue(rs.getString("loginname_value"));
				user.setLoginPwdName(rs.getString("loginpwd_name"));
				user.setLoginPwdValue(rs.getString("loginpwd_value"));
				user.setParam1Name(rs.getString("param1_name"));
				user.setParam1Value(rs.getString("param1_value"));
				user.setParam2Name(rs.getString("param2_name"));
				user.setParam2Value(rs.getString("param2_value"));
				user.setLastRunTime(rs.getDate("last_runtime"));
				list.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void changeLastTime(Integer userid){
		String sql = "update task_user set last_runtime = ? where id=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(new Date().getTime()));
			ps.setInt(2, userid);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
