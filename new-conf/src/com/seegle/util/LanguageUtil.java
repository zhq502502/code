package com.seegle.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * language.properties文件操作工具类
 */
public class LanguageUtil {
	
    //读取语言文字配置文件
    public String getLanguage(String language,String key, String defaultValue) throws UnsupportedEncodingException{
    	return PropUtil.getInstance().getValue(key+"_"+language, defaultValue);
    	/*InputStream is = null;
        if ("zh_tw".equals(language)) {  //繁体中文
            is = this.getClass().getResourceAsStream("/Language_zh_tw.properties");
        } else if ("en".equals(language)) {  //英文
            is = this.getClass().getResourceAsStream("/Language_en.properties");
        } else {  //简体中文
            is = this.getClass().getResourceAsStream("/Language_zh_cn.properties");
        }
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            System.out.println("读取配置文件错误");
            e.printStackTrace();
        }
        String result = pro.getProperty(key, defaultValue);
        if(result!=null&&defaultValue.equals(result)){
    		return result;
    	}*/
		//return new String(result.getBytes("ISO-8859-1"),"UTF-8");
    }
    
}
