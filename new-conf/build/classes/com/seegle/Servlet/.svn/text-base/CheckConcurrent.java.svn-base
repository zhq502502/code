package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.seegle.data.ConnMYSQL;
import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.MeetingOperation;
import com.seegle.data.SQLOperation;
import com.seegle.form.ConfRoomActionForm;
import com.seegle.util.SeegleLog;

public class CheckConcurrent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckConcurrent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(request.getSession().getAttribute("token")==null){
			response.sendRedirect(webSiteUrl+"/Login.jsp");
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		GetConfigFile gcf = new GetConfigFile();//读取配置
		String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
		Object orgid = request.getSession().getAttribute("orgid");
		Object token = request.getSession().getAttribute("token");
		HttpClient hc = new HttpClient(url,orgid.toString());
		int maxUser = 0; //同时段所有会议最大人数总和
		int cUser = Integer.parseInt(request.getParameter("maxconfuser")); //当前会议最大人数
		
		//获取会议最大并发人数
		 MeetingOperation mo = new MeetingOperation(orgid.toString());
		 Map<String, String> map =  new HashMap<String, String>();
		 map = mo.getConfMaxuser(orgid.toString(), token.toString());
		 int concurrent = Integer.parseInt(map.get("maxuser"));
		 String tip = map.get("tip");
		 System.out.println("concurrent:"+concurrent+";tip:"+tip);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String btime = request.getParameter("btime");
		String etime = request.getParameter("etime");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = df.parse(btime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			d2 = df.parse(etime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		params2.add(new BasicNameValuePair("orgid", orgid.toString())); 
		params2.add(new BasicNameValuePair("accessKey", token.toString()));
		params2.add(new BasicNameValuePair("cname", ""));
		JSONArray jsonarr = hc.getJArray("listall", params2);
        if (jsonarr!=null){
			if(jsonarr.size()>0){				
				for (int i=0; i<jsonarr.size(); ++i) {
					 JSONObject json1 = (JSONObject)jsonarr.get(i);
						String begintime = json1.get("btime").toString();
						String endtime = json1.get("etime").toString();
						Date bt = null;
						try {
							bt = df.parse(begintime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						Date et = null;
						try {
							et = df.parse(endtime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if((d1.getTime()>=bt.getTime()&&d1.getTime()<et.getTime())||(d2.getTime()>bt.getTime()&&d2.getTime()<=et.getTime())||(d1.getTime()<bt.getTime()&&d2.getTime()>et.getTime())){
							maxUser = maxUser + Integer.parseInt(json1.get("maxconfuser").toString());
						}
				}				
			}
        }

		int flag = 0;
		if((maxUser+cUser)>concurrent){
			flag = 1;
		}		

        JSONObject obj = new JSONObject();
        String json1 = "";
		obj.put("flag", flag);
		obj.put("tip", tip);
		json1 = obj.toJSONString();
		System.out.println(json1.toString());
		out.print(json1);
	}

}
