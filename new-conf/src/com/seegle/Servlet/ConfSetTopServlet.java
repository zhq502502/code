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

public class ConfSetTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"

    public ConfSetTopServlet() {
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
		params.add(new BasicNameValuePair("accessKey",request.getSession().getAttribute("token").toString())); 
		params.add(new BasicNameValuePair("cid", request.getParameter("cid"))); 
		params.add(new BasicNameValuePair("topValue", request.getParameter("topValue"))); 
		
	 	HttpClient hc = new HttpClient(url,request.getSession().getAttribute("orgid").toString());
	 	JSONObject json = hc.getJObject("settop", params);
		String flag = "";	
		if(json!=null){
			flag = json.get("msg").toString();
		}
		out.print(flag);
	}

}
