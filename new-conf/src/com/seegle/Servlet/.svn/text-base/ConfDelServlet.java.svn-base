package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.SQLOperation;

public class ConfDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	
    public ConfDelServlet() {
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
		HttpSession session = request.getSession();
		String orgid = session.getAttribute("orgid").toString();
		String token = session.getAttribute("token").toString();
		String action = request.getParameter("action");
		if("delall".equalsIgnoreCase(action)){
			String flag = "";
			String cids = request.getParameter("cids");	
			String cid[] = cids.split(",");
			for(int i=0;i<cid.length;i++){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("orgid",orgid)); 
				params.add(new BasicNameValuePair("accessKey", token)); 
				params.add(new BasicNameValuePair("cid", cid[i]));
			 	HttpClient hc = new HttpClient(url,request.getSession().getAttribute("orgid").toString());
			 	JSONObject json = hc.getJObject("remove", params);
			 	if(json!=null){
					flag = json.get("msg").toString();
				}
			}
			out.print(flag);
		}
		else{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("orgid",orgid)); 
			params.add(new BasicNameValuePair("accessKey", token)); 
			params.add(new BasicNameValuePair("cid", request.getParameter("cid")));
		 	HttpClient hc = new HttpClient(url,request.getSession().getAttribute("orgid").toString());
		 	JSONObject json = hc.getJObject("remove", params);
			String flag = "";	
			if(json!=null){
				flag = json.get("msg").toString();
			}
			out.print(flag);
		}	
	}

}
