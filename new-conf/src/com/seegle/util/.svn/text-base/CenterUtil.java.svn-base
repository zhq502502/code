package com.seegle.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 中心接口操作类
 * @author zhangqing
 * @date 2013-5-24 下午01:46:38
 */
public class CenterUtil {
	
	/**未找到对应的错误信息时提示*/
	public static final String CODE_NOTFOND = "没有找到对应的错误信息";
	
	/**
	 * 根据错误代码获得错误信息
	 * @author zhangqing
	 * @date 2013-5-24 下午01:47:16
	 * @param nErrorCode 错误提示代码
	 * @return 错误信息
	 */
	public static String TransCenterError(int nErrorCode){
		String key = "errorcode."+nErrorCode;
		String result = CODE_NOTFOND;
		result = PropUtil.getInstance().getValue(key, CODE_NOTFOND+"["+key+"]");
		return result;
	}
	
	/**
	 * 获得错误列表
	 * @author zhangqing
	 * @date 2013-5-27 下午05:10:00
	 * @return
	 */
	public static List<Map<String,String>> getErrorList(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		com.seegle.util.OrderedProperties prop = PropUtil.getInstance().getProperties();
		Set<Object> set = prop.keySet();
		for(Object str:set){
			if(str.toString().startsWith("errorcode.")){
				String val = "";
				val = PropUtil.getInstance().getValue(str.toString());
				String key = str.toString().substring("errorcode.".length(),str.toString().length());
				Map<String,String> map = new HashMap<String, String>();
				map.put("key", key);
				map.put("value", val);
				list.add(map);
			}
		}
		return list;
	}
	
	public static List<Map<String,String>> getErrorList_zh_tw(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		com.seegle.util.OrderedProperties prop = PropUtil.getInstance().getProperties();
		Set<Object> set = prop.keySet();
		for(Object str:set){
			if(str.toString().startsWith("errorcode_zh_tw.")){
				String val = "";
				val = PropUtil.getInstance().getValue(str.toString());
				String key = str.toString().substring("errorcode_zh_tw.".length(),str.toString().length());
				Map<String,String> map = new HashMap<String, String>();
				map.put("key", key);
				map.put("value", val);
				list.add(map);
			}
		}
		return list;
	}
	
	public static List<Map<String,String>> getErrorList_en(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		com.seegle.util.OrderedProperties prop = PropUtil.getInstance().getProperties();
		Set<Object> set = prop.keySet();
		for(Object str:set){
			if(str.toString().startsWith("errorcode_en.")){
				String val = "";
				val = PropUtil.getInstance().getValue(str.toString());
				String key = str.toString().substring("errorcode_en.".length(),str.toString().length());
				Map<String,String> map = new HashMap<String, String>();
				map.put("key", key);
				map.put("value", val);
				list.add(map);
			}
		}
		return list;
	}
	
	public static void main(String []a ){
		System.out.println(CenterUtil.TransCenterError(-1));
		System.out.println(CenterUtil.TransCenterError(1001));
	}
}
