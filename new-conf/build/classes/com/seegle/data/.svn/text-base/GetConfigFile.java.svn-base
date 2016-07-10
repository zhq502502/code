package com.seegle.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.seegle.util.PropUtil;

/**
 * 读取配置文件
 */
public class GetConfigFile implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	//读取配置文件config.properties
    public String getConfig(String operation) {
    	return PropUtil.getInstance().getValue(operation);
        /*InputStream is = this.getClass().getResourceAsStream("/config.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            System.out.println("读取配置文件错误");
            e.printStackTrace();
        }
        return pro.getProperty(operation);*/
    }
    
    //读取语言文字配置文件
    /*public String getLanguage(String operation, String language) {
    	System.out.println(operation+"================="+language);
        InputStream is = null;
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
            is.close();
        } catch (IOException e) {
            System.out.println("读取配置文件错误");
            e.printStackTrace();
        }
        return pro.getProperty(operation);
    }*/
}
