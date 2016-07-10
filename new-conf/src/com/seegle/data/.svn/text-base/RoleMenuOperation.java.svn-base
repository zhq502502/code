package com.seegle.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.seegle.beans.Menu;
import com.seegle.util.SeegleLog;

/**
 * 菜单操作类
 * @author zhangqing
 * @date 2013-7-10 下午01:53:24
 */
public class RoleMenuOperation {
	
	private Connection conn;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String orgid;
	private String setName = "set names utf8";
	
	public RoleMenuOperation(String orgid){
		this.orgid = orgid;
	}
	
	/**
	 * 获得所有的菜单项
	 * @author zhangqing
	 * @date 2013-7-10 下午01:59:44
	 * @param showHide 是否显示隐藏
	 * @return
	 */
	public List<Menu> getAllMenuOrder(boolean showHide){
		List<Menu> list = getAllmenu(showHide);
		return orderMenu(list);
	}
	/**
	 * 排序所有菜单
	 * @author zhangqing
	 * @date 2013-7-10 下午02:49:16
	 * @param list
	 */
	public List<Menu> orderMenu(List<Menu> list){
		Map<String,List<Menu>> map = new HashMap<String,List<Menu>>();
		Menu m = null;
		List<Menu> ml = null;
		for(Menu menu:list){
			if(m==null){
				m = menu;
				ml = new ArrayList<Menu>();
				ml.add(menu);
				continue;
			}
			if(m!=null&&m.getParentId().equals(menu.getParentId())){
				ml.add(menu);
				m = menu;
				continue;
			}
			if(m!=null&&!m.getParentId().equals(menu.getParentId())){
				map.put(m.getParentId(), ml);
				m = menu;
				ml = new ArrayList<Menu>();
				ml.add(menu);
				continue;
			}			
		}
		if(m!=null){
			map.put(m.getParentId(), ml);
		}
		List<Menu> listResult = new ArrayList<Menu>();
		List<Menu> top = map.get("0");
		for(Menu menu:top){
			listResult.add(menu);
			if(map.get(menu.getId())!=null&&map.get(menu.getId()).size()>0){
				listResult.addAll(map.get(menu.getId()));
			}
		}
		return listResult;
	}
	/**
	 * 获得所有菜单项
	 * @author zhangqing
	 * @date 2013-7-10 下午02:26:54
	 * @param showHide 是否显示隐藏
	 * @return
	 */
	public List<Menu> getAllmenu(boolean showHide){
		List<Menu> list = new ArrayList<Menu>();
		String sql = "select * from menu where flag = '"+(showHide?"0":"1")+"' order by parent_id asc,menu_index asc";
		SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取所有菜单sql:"+sql);
		conn = ConnMYSQL.getConnMYSQL();
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Menu menu = new Menu();
				menu.setId(rs.getString("id"));
				menu.setFlag(rs.getString("flag"));
				menu.setMenuCode(rs.getString("menu_code"));
				menu.setMenuDiscription(rs.getString("menu_discription"));
				menu.setMenuIndex(rs.getString("menu_index"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuUrl(rs.getString("menu_url"));
				menu.setParentId(rs.getString("parent_id"));
				list.add(menu);
			}
		}catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取所有菜单时出错"+e);
		}finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid);
		}
		return list;
	}
	
	/**
	 * 根据父ID获得菜单项
	 * @author zhangqing
	 * @date 2013-7-10 下午02:26:54
	 * @return
	 */
	public List<Menu> getMenuByParentid(String parentId){
		List<Menu> list = new ArrayList<Menu>();
		String sql = "select * from menu where flag = '0' and parent_id="+parentId+" order by parent_id asc,menu_index asc";
		SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取所有菜单sql:"+sql);
		conn = ConnMYSQL.getConnMYSQL();
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Menu menu = new Menu();
				menu.setId(rs.getString("id"));
				menu.setFlag(rs.getString("flag"));
				menu.setMenuCode(rs.getString("menu_code"));
				menu.setMenuDiscription(rs.getString("menu_discription"));
				menu.setMenuIndex(rs.getString("menu_index"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuUrl(rs.getString("menu_url"));
				menu.setParentId(rs.getString("parent_id"));
				list.add(menu);
			}
		}catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取所有菜单时出错"+e);
		}finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid);
		}
		return list;
	}
	
	/**
	 * 添加菜单项
	 * @author zhangqing
	 * @date 2013-7-10 下午02:50:19
	 * @param menu
	 * @return
	 */
	public boolean saveOrUpdate(Menu menu){
		if(menu.getId()==null||"".equals(menu.getId())){
			return addMenu(menu);
		}else{
			Menu menu1 = getById(menu.getId());
			menu1.setFlag(menu.getFlag());
			menu1.setId(menu.getId());
			menu1.setMenuCode(menu.getMenuCode());
			menu1.setMenuDiscription(menu.getMenuDiscription());
			menu1.setMenuIndex(menu.getMenuIndex());
			menu1.setMenuName(menu.getMenuName());
			menu1.setMenuUrl(menu.getMenuUrl());
			menu1.setParentId(menu.getParentId());
			return editMenu(menu);
		}
	}
	
	public Menu getById(String id){
		Menu menu = new Menu();
		String sql = "select * from menu where id = ?";
		SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取所有菜单sql:"+sql);
		conn = ConnMYSQL.getConnMYSQL();
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				menu.setId(rs.getString("id"));
				menu.setFlag(rs.getString("flag"));
				menu.setMenuCode(rs.getString("menu_code"));
				menu.setMenuDiscription(rs.getString("menu_discription"));
				menu.setMenuIndex(rs.getString("menu_index"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setMenuUrl(rs.getString("menu_url"));
				menu.setParentId(rs.getString("parent_id"));
				return menu;
			}
		}catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("获取所有菜单时出错"+e);
		}finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid);
		}
		return null;
	}
	
	/**
	 * 添加菜单项
	 * @author zhangqing
	 * @date 2013-7-10 下午02:50:19
	 * @param menu
	 * @return
	 */
	public boolean addMenu(Menu menu){
		String sql = "insert into menu(menu_name,menu_url,parent_id,menu_index,menu_code,menu_discription,flag) values(?,?,?,?,?,?,?)";
		conn = ConnMYSQL.getConnMYSQL();
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, menu.getMenuName());
			ps.setString(2, menu.getMenuUrl());
			ps.setString(3, menu.getParentId());
			ps.setString(4, menu.getMenuIndex()==null?"0":"0"+menu.getMenuIndex());
			ps.setString(5, menu.getMenuCode()==null?"0":"0"+menu.getMenuCode());
			ps.setString(6, menu.getMenuDiscription());
			ps.setString(7, menu.getFlag());
			ps.executeQuery(setName);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("添加菜单时出错"+e);
		}
		finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid);
		}
		return false;
	}
	
	/**
	 * 删除菜单项
	 * @author zhangqing
	 * @date 2013-7-10 下午02:58:39
	 * @param menuids
	 * @return
	 */
	public boolean delmenu(String menuids){
		String sql = "delete from menu where id in("+menuids+")";
		conn = ConnMYSQL.getConnMYSQL();
		try{
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("删除菜单时出错"+e);
		}finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid);
		}
		return false;
	}

	/**
	 * 编辑菜单项
	 * @author zhangqing
	 * @date 2013-7-10 下午02:50:19
	 * @param menu
	 * @return
	 */
	public boolean editMenu(Menu menu){
		String sql = "update menu set menu_name=?,menu_url=?,parent_id=?,menu_index=?,menu_code=?,menu_discription=?,flag=? where id=?";
		conn = ConnMYSQL.getConnMYSQL();
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, menu.getMenuName());
			ps.setString(2, menu.getMenuUrl());
			ps.setString(3, menu.getParentId());
			ps.setString(4, menu.getMenuIndex()==null?"0":"0"+menu.getMenuIndex());
			ps.setString(5, menu.getMenuCode()==null?"0":"0"+menu.getMenuCode());
			ps.setString(6, menu.getMenuDiscription());
			ps.setString(7, menu.getFlag());
			ps.setString(8, menu.getId());
			ps.executeQuery(setName);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {
			SeegleLog.getInstance().getLog(this.getClass(), orgid).error("更新菜单时出错"+e);
		}
		finally{
			ConnMYSQL.closeResources(rs, ps, conn, orgid);
		}
		return false;
	}
	
}
