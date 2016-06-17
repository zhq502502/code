package com.packages.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * http请求处理
 * @author Administrator
 *
 */
public class HttpUtil {
	/**
	 * 获得URL返回结果
	 * @param urladdress
	 * @return
	 */
	public static String getUrlResponse(String urladdress){
		try {
			URL url = new URL(urladdress);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("GET");
	        //conn.setRequestProperty("Content-type", "text/html");
	        //conn.setRequestProperty("contentType", "UTF-8");
	        conn.setConnectTimeout(120 * 1000);
	        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
	        String htmlSource = readInputStream(inStream);  
	        return htmlSource.replace("\n", "");

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String readInputStream(InputStream instream) throws Exception {
		StringBuilder sb1 = new StringBuilder();      
        byte[] bytes = new byte[4096];    
        int size = 0;    
          
        try {      
            while ((size = instream.read(bytes)) > 0) {    
                String str = new String(bytes, 0, size, "UTF-8");    
                sb1.append(str);    
            }    
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
            	instream.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb1.toString();   
    }
}
