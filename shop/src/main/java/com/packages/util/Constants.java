package com.packages.util;

public class Constants {
   public static final String CLIENTID = PropUtil.getInstance().getValue("clientid");
   
   public static final String CLIENTSECRECT = PropUtil.getInstance().getValue("clientsecret");
   
   public static final String RESPONSEURL = PropUtil.getInstance().getValue("responseurl");
   
   public static final int CURRENCY = 1;
   
   public static final int SPECIAL = 2;
   
   public static String getType(int type){
	   String s = "";
	   if(type==CURRENCY){
		   s = "通用";
	   }else{
		   s = "专用";
	   }
	   return s;
   }
   
   
   public static String INSCODE = PropUtil.getInstance().getValue("inscode");
   
   /*设备仪器中心*/
   public static int INSTRUCENTER = 1;
   
   /*大型设备*/
   public static int SCALEINSTRU = 2;
   
   /*服务单元*/
   public static int SERVICEUNIT = 3;
   
   /*单个设备*/
   public static int SINGLEINSTRU = 4;
   
   /*不可推送*/
   public static int PUSHDISABLED = 0;
   
   /*可推送*/
   public static int PUSHENABLE = 1;
   
   /*推送成功*/
   public static int PUSHSCUCESS = 2;
   
   /*推送失败*/
   public static int PUSHFAIL = 3;
}
