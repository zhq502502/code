package com.packages.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.packages.util.FileUtil;
import com.packages.util.PathUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SystemInfo {
	private List<MenuItem> topMenu = new ArrayList<>();
	private List<MenuItem> allMenu = new ArrayList<>();
	private Map<String,Long> controllerAuth = new HashMap<>();
	private static SystemInfo systeminfo;
	public static SystemInfo getInstance(){
		return systeminfo==null?new SystemInfo():systeminfo;
	}
	private SystemInfo(){
		try {
			initMenu();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initMenu() throws FileNotFoundException{
		String path = PathUtil.getProjectPath();
		path+="/WEB-INF/classes";
		path = path.replace("\\", "/");
		
		JSONArray menuJson = JSONArray.fromObject(FileUtil.readFile(new FileInputStream(path+"/menu.json")));
		for(int i = 0;i<menuJson.size();i++){
			JSONObject obj = menuJson.getJSONObject(i);
			MenuItem item = new MenuItem();
			item.setAuthcode(Long.parseLong((1<<obj.getInt("id"))+""));
			item.setId(obj.getInt("id"));
			item.setBaseurl(obj.getString("baseurl"));
			item.setLevel(obj.getInt("level"));
			item.setName(obj.getString("name"));
			item.setPid(obj.getInt("pid"));
			item.setUrl(obj.getString("url"));
			item.setCanlink(obj.getInt("canlink"));
			//设置控制层权限代码
			controllerAuth.put(item.getBaseurl(), item.getAuthcode());
			//设置顶级栏目和二级栏目
			if(item.getLevel()==1){
				topMenu.add(item);
			}
			allMenu.add(item);
		}
		//System.out.println(menuJson);
	}
	/**
	 * 获取顶级菜单
	 * @return
	 */
	public List<MenuItem> getTopMenu(){
		return this.topMenu;
	}
	/**
	 * 获得所有二级菜单
	 * @return
	 */
	public List<MenuItem> getAllMenu(){
		return this.allMenu;
	}
	/**
	 * 获取顶级菜单
	 * @return
	 */
	public List<MenuItem> getTopMenu(long authcode){
		List<MenuItem> list = new ArrayList<>();
		for(MenuItem item:topMenu){
			if((item.getAuthcode()&authcode)==item.getAuthcode()){
				list.add(item);
			}
		}
		return list;
	}
	/**
	 * 获得所有二级菜单
	 * @return
	 */
	public List<MenuItem> getAllMenu(long authcode){
		List<MenuItem> list = new ArrayList<>();
		for(MenuItem item:allMenu){
			if((item.getAuthcode()&authcode)==item.getAuthcode()){
				list.add(item);
			}
		}
		return list;
	}
	public long getUrlAuth(String url){
		return controllerAuth.get(url);
	}
}
