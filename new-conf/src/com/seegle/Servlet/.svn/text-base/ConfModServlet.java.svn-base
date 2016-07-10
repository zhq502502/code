package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;

public class ConfModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	
    public ConfModServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(request.getSession().getAttribute("token")==null){
			response.sendRedirect(webSiteUrl+"/Login.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	  	List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("orgid",request.getSession().getAttribute("orgid").toString())); 
		params.add(new BasicNameValuePair("accessKey", request.getSession().getAttribute("token").toString())); 
		params.add(new BasicNameValuePair("cid", request.getParameter("cid")));
		params.add(new BasicNameValuePair("type","set"));
		params.add(new BasicNameValuePair("confname", request.getParameter("confname"))); 
	 	params.add(new BasicNameValuePair("begintime", request.getParameter("btime"))); 
	 	params.add(new BasicNameValuePair("endtime", request.getParameter("etime"))); 
	 	params.add(new BasicNameValuePair("grouptype", request.getParameter("grouptype"))); 
	 	params.add(new BasicNameValuePair("max_conf_user", request.getParameter("maxconfuser"))); 
	 	params.add(new BasicNameValuePair("max_conf_tourist", request.getParameter("maxconftourist"))); 
	 	params.add(new BasicNameValuePair("max_conf_spokesman", request.getParameter("maxconfspokesman"))); 
	 	params.add(new BasicNameValuePair("conf_password_md5", request.getParameter("confpass"))); 
	 	params.add(new BasicNameValuePair("manage_password_md5", request.getParameter("managepass"))); 	
		params.add(new BasicNameValuePair("open_flag", request.getParameter("open_flag"))); 
	 	params.add(new BasicNameValuePair("lock_flag", request.getParameter("lock_flag"))); 
	 	params.add(new BasicNameValuePair("auto_clear_flag", request.getParameter("auto_clear_flag"))); 
	 	params.add(new BasicNameValuePair("all_can_visible", request.getParameter("all_can_visible"))); 
	 	params.add(new BasicNameValuePair("description", request.getParameter("description"))); 	 	
	 	params.add(new BasicNameValuePair("open_audit", request.getParameter("open_audit")));  
	 	params.add(new BasicNameValuePair("download_mode", request.getParameter("download_mode"))); 	 		 	
	 	String auto_record = request.getParameter("auto_record");
	 	String record = request.getParameter("record_module");
	 	long temp1 = 0;
        if(auto_record.equals("1")){
    	 	String a[] = record.split(":");
    	 	String b[] = a[1].split(",");
        	temp1 = 1;
        	temp1 = temp1<<31;
            for (int i=0;i<b.length;i++){
            	//System.out.println(b[i]);
            	temp1 = temp1+Integer.parseInt(b[i]);
            }
        }         
        String confsetting = request.getParameter("confsetting");
        System.out.println("############################替换前"+confsetting);
        String a[]=confsetting.split(";");
        String confsetting1 = confsetting.replace(a[2], "<AutoRecord>="+temp1);
        System.out.println("############################替换后"+confsetting1);
         	
        params.add(new BasicNameValuePair("confsetting", confsetting1)); 
	 	HttpClient hc = new HttpClient(url,request.getSession().getAttribute("orgid").toString());
	 	JSONObject json = hc.getJObject("update", params);
		String flag = "";	
		if(json!=null){
			flag = json.get("msg").toString();
		}
		out.print(flag);	 	
	}

}
