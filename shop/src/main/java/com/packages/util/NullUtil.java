package com.packages.util;

public class NullUtil {
	/**
	 * 为空的数据
	 * @author zhangqing
	 * @date 2016年9月29日 下午2:24:35
	 * @param obj
	 * @return
	 */
	public static String null2defualt(Object obj){
		if(obj==null){
			return "0";
		}else if(obj.equals("")){
			return "0";
		}else{
			return obj+"";
		}
	}
	public String[] SlDjZj(String sl,String dj,String zj){
		String[] result = new String[3];
		double sl1 = 0;
		double dj1 = 0;
		double zj1 = 0;
		try{
			sl1 = Double.valueOf(sl);
			dj1 = Double.valueOf(dj);
			zj1 = Double.valueOf(zj);
			if(zj1!=0&&dj1==0&&sl1==0){
				sl1 = 1;
				dj1 = 1;
			}
			
			result[0] = sl1+"";
			result[1] = dj1+"";
			result[2] = zj1+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
