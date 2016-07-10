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

public class ConfUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ConfUserServlet() {
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
		GetConfigFile gcf = new GetConfigFile();//读取配置
		String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Object token = session.getAttribute("token");
		Object orgid = session.getAttribute("orgid");
		String action = request.getParameter("action");
		String cid = request.getParameter("cid");
		String account = request.getParameter("account");
		String operation = request.getParameter("operation");
		int flag = 1;
		if(action.equals("commonUser")){
			final HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("accessKey", token.toString())); 
			param.add(new BasicNameValuePair("cid", cid)); 
			param.add(new BasicNameValuePair("orgid",orgid.toString()));
			param.add(new BasicNameValuePair("commonList",account+","));
			param.add(new BasicNameValuePair("adminList",""));
			JSONObject json = hc.getJObject(operation, param);
			flag = Integer.parseInt(json.get("msg").toString());
		}
		else if(action.equals("confAdmin")){
			final HttpClient hc = new HttpClient(url,orgid.toString());
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("accessKey", token.toString())); 
			param.add(new BasicNameValuePair("cid", cid)); 
			param.add(new BasicNameValuePair("orgid",orgid.toString()));
			param.add(new BasicNameValuePair("commonList",""));
			param.add(new BasicNameValuePair("adminList",account+","));
			JSONObject json = hc.getJObject(operation, param);
			flag = Integer.parseInt(json.get("msg").toString());
		}
		out.print(flag);
	}

}
