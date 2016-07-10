package com.seegle.data;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.seegle.util.PropUtil;

/**
 * 下载操作类
 * @author zhangqing
 * @date 2013-5-13 下午04:49:42
 */
public class DownloadOperation {
	private String orgid ;
	public DownloadOperation(String orgid){
		this.orgid = orgid;
	}
	/**
	 * 获得下载地址的list串
	 * @author zhangqing
	 * @date 2013-5-13 下午05:49:19
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,String>> getDownLoadList() throws UnsupportedEncodingException{
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		com.seegle.util.OrderedProperties prop = PropUtil.getInstance().getProperties();
		Set<Object> set = prop.keySet();
		for(Object str:set){
			if(str.toString().startsWith("confdownload.")){
				System.out.println(str);
				String values[] = PropUtil.getInstance().getValue(str.toString()).split(",");
				Map<String,String> map = new HashMap<String, String>();
				map.put("name", values[0]);
				map.put("language", values[1]);
				map.put("size", values[2]);
				map.put("time", values[3]);
				map.put("url", values[4]);
				map.put("name_zh_tw", values[5]);
				map.put("language_zh_tw", values[6]);
				map.put("name_en", values[7]);
				map.put("language_en", values[8]);
				list.add(map);
			}
		}
		return list;
	}
	
	/**
	 * 获得下载地址的list串
	 * @author zhangqing
	 * @date 2013-5-13 下午05:49:19
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public List<Map<String,String>> getTopDownLoadList() throws UnsupportedEncodingException{
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		com.seegle.util.OrderedProperties prop = PropUtil.getInstance().getProperties();
		Set<Object> set = prop.keySet();
		for(Object str:set){
			if(str.toString().startsWith("topdownload.")){
				String values[] = PropUtil.getInstance().getValue(str.toString()).split(",");
				Map<String,String> map = new HashMap<String, String>();
				map.put("name", values[0]);
				map.put("language", values[1]);
				map.put("size", values[2]);
				map.put("time", values[3]);
				map.put("url", values[4]);
				map.put("name_zh_tw", values[5]);
				map.put("language_zh_tw", values[6]);
				map.put("name_en", values[7]);
				map.put("language_en", values[8]);
				list.add(map);
			}
		}
		return list;
	}
}

