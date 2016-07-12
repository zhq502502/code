package plugin.depart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		sb.append(" select id as realid,-1*id as id,departpnum as pid,departname as name,'' as account,0 as type,'images_gb/045.png' as icon from department where orgid=? ");
		sb.append(" UNION ");
		sb.append(" select id as realid,id as id,-1*dep_id as pid,alias as name,user_name as account,1 as type,'images_gb/005.png' as icon from user where orgid=? and user_name <> 'admin' ");
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
}
