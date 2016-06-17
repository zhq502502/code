package com.packages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import org.junit.Test;

import com.packages.util.HttpUtil;
import com.packages.util.PropUtil;

import net.sf.json.JSONObject;

public class SkTest {
	private static long countroole(int ...codes){
		long result = 0;
		for(int i=0;i<codes.length;i++){
			result = result|(1<<codes[i]);
		}
		return result;
	}
	@Test
	public void UrlEncode(){
		String str = "http://weixin.chenksoft.com";
		System.out.println(URLEncoder.encode(str));
	}
	@Test
	public void createtime(){
		System.out.println(Calendar.getInstance().getTimeInMillis());
	}
	@Test
	public void cmdtest(){
		String javaExcute = "ping www.baidu.com"; // javaExcute为你要在CMD中执行的字符串

		Process p;
		try {
			// 执行CMD代码,返回一个Process
			p = Runtime.getRuntime().exec(javaExcute);
			InputStream is = p.getInputStream();
			// 得到相应的控制台输出信息
			InputStreamReader bi = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(bi);
			String message;
			message = br.readLine();
			while (message != null && !"".equals(message)) {
				// 将信息输出
				System.out.println(message);
				message = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
    }
	@Test
	public void cmd1test(){
		String commandStr = "cmd /c ping www.baidu.com";
		BufferedReader br = null;  
        try {  
            Process p = Runtime.getRuntime().exec(commandStr);  
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
            String line = null;  
            StringBuilder sb = new StringBuilder();  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }  
            System.out.println(sb.toString());
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
	}
}
