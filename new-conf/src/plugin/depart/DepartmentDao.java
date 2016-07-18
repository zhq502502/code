package plugin.depart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.seegle.data.ConnMYSQL;
import com.seegle.data.HttpClient;

public class DepartmentDao {
	private String setName = "set names utf8";	
	public static DepartmentDao dao = new DepartmentDao();
	/**
	 * 获取部门的用户组合的树结构
	 * @return
	 */
	public List<TreeBean> getDepartAndUser(int orgid){
		List<TreeBean> list = new ArrayList<TreeBean>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select id as realid,-1*id as id,-1*departpnum as pid,departname as name,'' as account,0 as type,'images_gb/045.png' as icon from department where orgid=? ");
		sb.append(" UNION ");
		sb.append(" select id as realid,id as id,-1*departid as pid,alias as name,user_name as account,1 as type,'images_gb/005.png' as icon from user where orgid=? and user_name <> 'admin' ");
		System.out.println(sb.toString());
		Connection conn = ConnMYSQL.getConnMYSQL();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, orgid);
			ps.setInt(2, orgid);
			rs = ps.executeQuery();
			while(rs.next()){
				TreeBean bean = new TreeBean();
				bean.setAccount(rs.getString("account"));
				bean.setIcon(rs.getString("icon"));
				bean.setName(rs.getString("name"));
				bean.setId(rs.getInt("id"));
				bean.setRealid(rs.getInt("realid"));
				bean.setPid(rs.getInt("pid"));
				bean.setType(rs.getInt("type"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid+"");
		}
		return list;
	}
	/**
	 * 获取用户信息
	 * @author zhangqing
	 * @date 2016年7月14日 下午8:45:15
	 * @param userid
	 * @param orgid
	 * @return
	 */
	public Map<String,Object> getUserinfo(int userid,int orgid){
		Map<String,Object> map = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select * from user where orgid=? and id=?");
		Connection conn = ConnMYSQL.getConnMYSQL();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, orgid);
			ps.setInt(2, userid);
			rs = ps.executeQuery();
			while(rs.next()){
				map = new HashMap<String, Object>();
				map.put("id", userid);
				map.put("orgid", orgid);
				map.put("role", rs.getInt("role"));
				map.put("alias", rs.getString("alias"));
				map.put("orders", rs.getInt("orders"));
				map.put("telphone", rs.getString("account_mobile"));
				map.put("email", rs.getString("account_email"));
				map.put("username", rs.getString("user_name"));
				map.put("password", rs.getString("password_text"));
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid+"");
		}
		return map;
	}
	/**
	 * 获取用户信息
	 * @author zhangqing
	 * @date 2016年7月14日 下午8:45:15
	 * @param userid
	 * @param orgid
	 * @return
	 */
	public Map<String,Object> getDepartinfo(int departid,int orgid){
		Map<String,Object> map = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select * from department where orgid=? and id=?");
		Connection conn = ConnMYSQL.getConnMYSQL();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, orgid);
			ps.setInt(2, departid);
			rs = ps.executeQuery();
			while(rs.next()){
				map = new HashMap<String, Object>();
				map.put("id", departid);
				map.put("orgid", orgid);
				map.put("name", rs.getString("departname"));
				map.put("pid", rs.getInt("departpnum"));
				map.put("orders", rs.getInt("orders"));
				return map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid+"");
		}
		return map;
	}
	public boolean saveOrUpdateUser(Map<String,Object> user){
		int id=Integer.parseInt(user.get("id").toString());
		String account=user.get("account").toString();
		String password=user.get("password").toString();
		String alias=user.get("alias").toString();
		String email=user.get("email").toString();
		String phone=user.get("phone").toString();
		int role = Integer.parseInt(user.get("role").toString());
		int departid = Integer.parseInt(user.get("departid").toString());;
		int orders =Integer.parseInt(user.get("orders").toString());;
		int orgid = Integer.parseInt(user.get("orgid").toString());;
		StringBuffer sb = new StringBuffer();
		if(id==0){
			sb.append("insert into user(user_name,alias,role,name,account_email,account_mobile,password_md5,password_text,departid,orders,orgid,lastlogintime,logintag) values(?,?,?,?,?,?,?,?,?,?,?,'2000-12-12 12:12:12',1)");
		}else{
			sb.append("update user set alias=?,role=?,account_email=?,account_mobile=?,password_md5=?,password_text=?,orders=? where id=?");
		}
		Connection conn = ConnMYSQL.getConnMYSQL();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			if(id==0){
				ps.setString(1, account);
				ps.setString(2, alias);
				ps.setInt(3, role);
				ps.setString(4, alias);
				ps.setString(5, email);
				ps.setString(6, phone);
				ps.setString(7, HttpClient.md5(password));
				ps.setString(8, password);
				ps.setInt(9, departid);
				ps.setInt(10, orders);
				ps.setInt(11, orgid);
			}else{
				ps.setString(1, alias);
				ps.setInt(2, role);
				ps.setString(3, email);
				ps.setString(4, phone);
				ps.setString(5, HttpClient.md5(password));
				ps.setString(6, password);
				ps.setInt(7, orders);
				ps.setInt(8, id);
			}
			ps.executeQuery(setName);
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid+"");
		}
		return false;
	}
	public boolean saveOrUpdateDepart(Map<String,Object> depart){
		int id=Integer.parseInt(depart.get("id").toString());
		String name=depart.get("name").toString();
		int pid = Integer.parseInt(depart.get("pid").toString());;
		int orders =Integer.parseInt(depart.get("orders").toString());;
		int orgid = Integer.parseInt(depart.get("orgid").toString());;
		StringBuffer sb = new StringBuffer();
		if(id==0){
			sb.append("insert into department(departpnum,departname,orders,orgid,sys) values(?,?,?,?,0)");
		}else{
			sb.append("update department set departpnum=?,departname=?,orders=? where id=?");
		}
		Connection conn = ConnMYSQL.getConnMYSQL();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, pid);
			ps.setString(2, name);
			ps.setInt(3, orders);
			if(id>0){
				ps.setInt(4, id);
			}else{
				ps.setInt(4, orgid);
			}
			ps.executeQuery(setName);
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid+"");
		}
		return false;
	}
	public boolean deleteUser(String ids,int orgid){
		StringBuffer sb = new StringBuffer();
		sb.append("delete from user where id in("+ids+") and (sys<>1 or isnull(sys) )");
		Connection conn = ConnMYSQL.getConnMYSQL();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid+"");
		}
		return false;
	}
	public boolean deleteDepart(String ids,int orgid){
		StringBuffer sb = new StringBuffer();
		sb.append("delete from department where id in("+ids+")  and  (sys<>1 or isnull(sys) )");
		Connection conn = ConnMYSQL.getConnMYSQL();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sb.toString());
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid+"");
		}
		return false;
	}
}
