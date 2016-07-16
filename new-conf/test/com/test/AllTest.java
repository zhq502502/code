package com.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import com.seegle.data.HttpClient;

public class AllTest {
	@Test
	public void 获取MD5加密串(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		System.out.println("当前时间："+format.format(cal.getTime()));
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		int minute = cal.get(Calendar.MINUTE);
		if(minute>30){
			minute = 30;
		}else{
			minute=0;
		}
		cal.set(Calendar.MINUTE, minute);
		
		String key = "whzh!@#";
		String md5key = HttpClient.md5(cal.getTimeInMillis()+key);
		
		System.out.println("时间取半小时整："+format.format(cal.getTime()));
		
		System.out.println("用于加密的时间格式："+cal.getTimeInMillis());
		System.out.println("key:"+key);
		System.out.println("用于加密的字符串："+cal.getTimeInMillis()+key);
		System.out.println("最终加密key："+md5key);
		
		
	}
}
