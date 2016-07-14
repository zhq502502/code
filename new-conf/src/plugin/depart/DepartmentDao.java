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

public class DepartmentDao {
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
				map.put("password", "@@@@@@");
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
				map.put("name", rs.getInt("departname"));
				map.put("pid", rs.getString("departpnum"));
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
	public boolean saveOrUpdateUser(){
		return false;
	}
	public boolean saveOrUpdateDepart(){
		
		return false;
	}
}
