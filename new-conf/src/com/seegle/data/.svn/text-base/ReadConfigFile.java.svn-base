package com.seegle.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.seegle.util.PropUtil;


public class ReadConfigFile {
	private static String[] databaseInfo = new String[4];

	public ReadConfigFile() {
	}
	
	public String[] getDatabaseInfo() {
		databaseInfo[0] = PropUtil.getInstance().getValue("className");
		databaseInfo[1] = PropUtil.getInstance().getValue("url");
		databaseInfo[2] = PropUtil.getInstance().getValue("userName");
		databaseInfo[3] = PropUtil.getInstance().getValue("password");
        /*InputStream is = this.getClass().getResourceAsStream("/config.properties");
        Properties info = new Properties();
        try {
            info.load(is);
            databaseInfo[0] = info.getProperty("className");
            databaseInfo[1] = info.getProperty("url");
            databaseInfo[2] = info.getProperty("userName");
            databaseInfo[3] = info.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return databaseInfo;
    }
	//读取配置文件config.properties
    /*public String getConfig(String operation) {
        InputStream is = this.getClass().getResourceAsStream("/config.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            System.out.println("读取配置文件错误");
            e.printStackTrace();
        }
        return pro.getProperty(operation);
    }*/
	
}
