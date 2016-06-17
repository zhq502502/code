package com.packages.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.packages.core.ListItem;


public class ListItemUtil {
	/**性别*/
	public static final String LIST_ITEM_SEX = "listitem.sex.";
	/**文化程度*/
	public static final String LIST_ITEM_DEGREE = "listitem.degree.";
	/**角色*/
	public static final String LIST_ITEM_ROLE = "listitem.role.";
	/**角色代码*/
	public static final String LIST_ITEM_ROLEAUTH = "listitem.roleauth.";
	/**商品类型*/
	public static final String LIST_ITEM_GOODSTYPE = "listitem.goodstype.";
	/**账号状态*/
	public static final String LIST_ITEM_ACCOUNTSTATE = "listitem.accountstate.";
	/**课程状态*/
	public static final String LIST_ITEM_COURSESTATE = "listitem.coursestate.";
	/**基础数据*/
	public static final String LIST_ITEM_BASEDATATYPE = "listitem.basedatatype.";
	/**处理状态*/
	public static final String LIST_ITEM_EXECUTESTATE = "listitem.executestate.";
	/**帐号类型*/
	public static final String LIST_ITEM_ACCOUNTTYPE = "listitem.accounttype.";
	
	public static List<ListItem> getItemList(String itemstart){
		List<String> keys = PropUtil.getInstance().getKeyStartWith(itemstart);
		List<ListItem> list = new ArrayList<ListItem>();
		for(String key:keys){
			String value = PropUtil.getInstance().getValue(key);
			ListItem item = new ListItem();
			item.setKey(Integer.parseInt(key.replace(itemstart, "")));
			item.setValue( value);
			list.add(item);
		}
		return list;
	}
}
