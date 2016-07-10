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
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.util.SeegleLog;

public class ConfLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConfLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		GetConfigFile gcf = new GetConfigFile();//读取配置
		String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
		HttpSession session = request.getSession();
		Object token = session.getAttribute("token");
		String cid = request.getParameter("cid");
		HttpClient hc = new HttpClient(url,session.getAttribute("orgid").toString());
		List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		params2.add(new BasicNameValuePair("accessKey", token.toString())); 
		params2.add(new BasicNameValuePair("cid", cid));
		JSONObject json = hc.getJObject("login", params2); 

		SeegleLog.getInstance().getLog(this.getClass(), session.getAttribute("orgid").toString()).info("登录会议返回的信息:"+json);
		out.print(json);
	}

}
