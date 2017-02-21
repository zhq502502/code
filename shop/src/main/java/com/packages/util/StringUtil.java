package com.packages.util;

public class StringUtil {
	
   public static String removeLast(String str){
	   String s = "";
	   if((str!=null)&&(str.trim().length()>1)){
		   s = str.substring(0,str.length()-1);
	   }
	   return  s;
   }
   
   public static String getImgUrl(String s){
	   String url = "";
	   if((s==null)||(s.trim().equals(""))){
		   url = PropUtil.getInstance().getValue("defaultimg");
	   }else{
		   String id = removeLast(s);
		   url = String.format(PropUtil.getInstance().getValue("imageurl"),id,id);
	   }
	   return url;
   }
}
